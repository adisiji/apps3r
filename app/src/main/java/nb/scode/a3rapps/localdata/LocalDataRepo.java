package nb.scode.a3rapps.localdata;

import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import nb.scode.a3rapps.modelrealm.LastUpdateLocal;
import nb.scode.a3rapps.modelrealm.MetaRealm;
import nb.scode.a3rapps.modelretro.Colors;
import nb.scode.a3rapps.modelretro.DataJne;
import nb.scode.a3rapps.modelretro.DataStatis;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.MainPackages;
import nb.scode.a3rapps.modelretro.Products;
import nb.scode.a3rapps.modelretro.Sizes;
import nb.scode.a3rapps.modelretro.Stamps;
import nb.scode.a3rapps.modelretro.StampsRetro;
import nb.scode.a3rapps.modelretro.SubMainPackage;
import nb.scode.a3rapps.modelretro.SubMainTypes;
import nb.scode.a3rapps.network.ApiService;
import nb.scode.a3rapps.util.NetworkManager;

/**
 * Created by neobyte on 2/9/2017.
 */

public class LocalDataRepo implements LocalDataTask {

    private ApiService apiService;
    private Gson gson;
    private Realm realm;
    private NetworkManager networkManager;
    private static final String TAG = LocalDataRepo.class.getSimpleName();
    private static final String no_inet_connet = "No Internet Connection";
    private int req=0, avail = 0;

    @Inject
    public LocalDataRepo(ApiService apiService, Realm realm, NetworkManager networkManager){
        this.apiService = apiService;
        this.realm = realm;
        this.networkManager = networkManager;
    }

    @Override
    public boolean getMeta() {
        return realm.where(MetaRealm.class).findFirst() != null;
    }

    @Override
    public void setMeta(int uid, String key, String nama, int saldo,
                        String prodTersimpan, int prodTersimpanMaks,
                        final LoadTaskCallback callback) {
        final MetaRealm metaRealm = new MetaRealm();
        metaRealm.setId(uid);
        metaRealm.setKey(key);
        metaRealm.setNama(nama);
        metaRealm.setSaldo(saldo);
        metaRealm.setProdukTersimpan(prodTersimpan);
        metaRealm.setProdukTersimpanMaks(prodTersimpanMaks);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(metaRealm);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callback.success();
            }
        });
    }

    @Override
    public String getName() {
        try {
            MetaRealm metaRealm = realm.where(MetaRealm.class).findFirst();
            return metaRealm.getNama();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    public int getSaldo() {
        try {
            MetaRealm metaRealm = realm.where(MetaRealm.class).findFirst();
            return metaRealm.getSaldo();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getTimeLimit() {
        try{
            SubMainPackage packet = realm.where(SubMainPackage.class).findFirst();
            return packet.getTime_limit();
        }
        catch (NullPointerException e){
            Crashlytics.getInstance().core.log("Time Limit is empty");
            return 0;
        }
    }

    @Override
    public int getReqLimit() {
        try {
            SubMainPackage packet = realm.where(SubMainPackage.class).findFirst();
            return packet.getRequest_limit();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getReqCount() {
        try {
            SubMainPackage packet = realm.where(SubMainPackage.class).findFirst();
            return packet.getRequest_count();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean isEmptyLocalStamp() {
        return realm.where(LastUpdateLocal.class).findFirst() == null;
    }

    @Override
    public long getLocalStampJne() {
        try {
            LastUpdateLocal local = realm.where(LastUpdateLocal.class).findFirst();
            return local.getLastDataJne();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public long getLocalStampStatis() {
        try {
            LastUpdateLocal local = realm.where(LastUpdateLocal.class).findFirst();
            return local.getLastDataStatis();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void compareCacheStamp() {

    }

    private int getUid(){
        try {
            MetaRealm metaRealm = realm.where(MetaRealm.class).findFirst();
            return metaRealm.getId();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return 0;
        }
    }

    private String getKey(){
        realm.beginTransaction();
        MetaRealm metaRealm = realm.where(MetaRealm.class).findFirst();
        realm.commitTransaction();
        try {
            return metaRealm.getKey();
        }
        catch (NullPointerException e){
            return "Empty";
        }
    }

    @Override
    public void getDataStatis(final LoadTaskCallback callback) {
        if(networkManager.isConnected()){
            apiService.ambilDataStatis(getUid(),getKey())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceObserver<DataStatis>() {
                        @Override
                        public void onNext(final DataStatis value) {
                            if(value.getStatus() > 0){
                                Log.d(TAG,"next->"+value.getMainTypes().keySet());
                                final List<SubMainTypes> typesList = new ArrayList<>();
                                for(String key: value.getMainTypes().keySet()){
                                    typesList.add(value.getMainTypes().get(key));
                                }
                                realm.executeTransactionAsync(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        realm.copyToRealm(value.getColorsRealmList());
                                        realm.copyToRealm(value.getProductsStatisRealmList());
                                        realm.copyToRealm(value.getSizesRealmList());
                                        realm.copyToRealm(typesList);
                                    }
                                }, new Realm.Transaction.OnSuccess(){
                                    @Override
                                    public void onSuccess() {
                                        callback.success();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG,"getDataStatis Error -> "+e.getMessage());
                            callback.failed(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        else {
            callback.failed(no_inet_connet);
        }
    }

    @Override
    public void getDataJne(final LoadTaskCallback callback) {
        if(networkManager.isConnected()){
            apiService.ambilDataJne(getUid(),getKey())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceObserver<DataJne>() {
                        @Override
                        public void onNext(final DataJne value) {
                            if(value.getStatus() > 0){
                                realm.executeTransactionAsync(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        realm.copyToRealm(value.getListJne());
                                    }
                                }, new Realm.Transaction.OnSuccess(){
                                    @Override
                                    public void onSuccess() {
                                        callback.success();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG,"getDataJne Error -> "+e.getMessage());
                            callback.failed(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        else {
            callback.failed(no_inet_connet);
        }

    }

    @Override
    public void getDaftarPaket(final LoadTaskCallback callback) {
        if(networkManager.isConnected()){
            apiService.ambilDaftarPaket(getUid(),getKey())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceObserver<MainPackages>() {
                        @Override
                        public void onNext(MainPackages value) {
                            if(value.getStatus()>0){
                                if(clearDaftarPaket()){
                                    final List<DetailPackage> typesList = new ArrayList<>();
                                    for(Integer key: value.getPackageMap().keySet()){
                                        typesList.add(value.getPackageMap().get(key));
                                    }
                                    final SubMainPackage subMainPackage = new SubMainPackage();
                                    subMainPackage.setRequest_count(value.getReq_count());
                                    subMainPackage.setRequest_limit(value.getReq_limit());
                                    subMainPackage.setTime_limit(value.getTime_limit());
                                    realm.executeTransactionAsync(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            realm.copyToRealm(typesList);
                                            realm.copyToRealm(subMainPackage);
                                        }
                                    }, new Realm.Transaction.OnSuccess() {
                                        @Override
                                        public void onSuccess() {
                                            Log.d(TAG, "getDaftarPaket => Completed");
                                            callback.success();
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG,"getDaftarPaket Error -> "+e.getMessage());
                            callback.failed(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        else {
            callback.failed(no_inet_connet);
        }
    }

    private boolean clearDaftarPaket(){
        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<DetailPackage> detailPackages = realm.where(DetailPackage.class).findAll();
                    RealmResults<SubMainPackage> subMainPack = realm.where(SubMainPackage.class).findAll();
                    subMainPack.deleteAllFromRealm();
                    for(DetailPackage i: detailPackages){
                        if(i!=null){
                            if(i.getProducts()!=null){
                                i.getProducts().deleteAllFromRealm();
                            }
                            i.deleteFromRealm();
                        }
                    }
                }
            });
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void emptyPaket() {

    }

    @Override
    public void getStamp(final UpdateCacheCallback callback) {
        Log.d(TAG,"uid : "+String.valueOf(getUid())
                +" key : "+String.valueOf(getKey()));
        if(networkManager.isConnected()){
            apiService.ambilMeta(getUid(),getKey())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceObserver<StampsRetro>() {
                        @Override
                        public void onNext(StampsRetro value) {
                            if(value.getStatus()>0){
                                final Stamps stampx = value.getStamps();
                                if(stampx!=null){
                                    if(getLocalStampStatis() == 0 && getLocalStampJne() ==0){
                                        realm.executeTransactionAsync(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                LastUpdateLocal local = new LastUpdateLocal();
                                                local.setLastDataJne(stampx.getAmbilDataJne());
                                                local.setLastDataStatis(stampx.getAmbilDataStatis());
                                                realm.copyToRealm(local);
                                            }
                                        });
                                    }
                                    else if(getLocalStampStatis() < stampx.getAmbilDataStatis() &&
                                            getLocalStampJne() < stampx.getAmbilDataJne()){
                                        clearStamps();
                                        realm.executeTransactionAsync(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                LastUpdateLocal local = new LastUpdateLocal();
                                                local.setLastDataJne(stampx.getAmbilDataJne());
                                                local.setLastDataStatis(stampx.getAmbilDataStatis());
                                                realm.insertOrUpdate(local);
                                            }
                                        }, new Realm.Transaction.OnSuccess() {
                                            @Override
                                            public void onSuccess() {
                                                callback.updateJneStatis();
                                            }
                                        });
                                    }
                                    else if(getLocalStampStatis() < stampx.getAmbilDataStatis()){
                                        realm.executeTransactionAsync(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                LastUpdateLocal stamps = realm.where(LastUpdateLocal.class)
                                                        .equalTo("lastDataStatis", getLocalStampStatis()).findFirst();
                                                stamps.setLastDataStatis(stampx.getAmbilDataStatis());
                                            }
                                        }, new Realm.Transaction.OnSuccess() {
                                            @Override
                                            public void onSuccess() {
                                                callback.updateStatis();
                                            }
                                        });
                                    }
                                    else if(getLocalStampJne() < stampx.getAmbilDataJne()){
                                        realm.executeTransactionAsync(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                LastUpdateLocal stamps = realm.where(LastUpdateLocal.class)
                                                        .equalTo("lastDataJne", getLocalStampJne()).findFirst();
                                                stamps.setLastDataJne(stampx.getAmbilDataJne());
                                            }
                                        }, new Realm.Transaction.OnSuccess() {
                                            @Override
                                            public void onSuccess() {
                                                callback.updateJne();
                                            }
                                        });
                                    }
                                    else {
                                        Log.d(TAG, "Stamps is uptodate");
                                    }
                                }
                                else{
                                    Log.d(TAG, "getStamp => null stampx");
                                    callback.failed("Null Stamps");
                                }
                            }
                            else {
                                callback.failed("go login");
                                Log.d(TAG, "getStamp => error result stampx");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            callback.failed(e.getMessage());
                            Log.d(TAG, "getStamp Error => " +e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            callback.success();
                            Log.d(TAG, "getStamp => Completed");
                        }
                    });
        }
        else {
            callback.failed(no_inet_connet);
        }
    }

    @Override
    public void setStamp() {

    }

    @Override
    public void clearMeta() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(MetaRealm.class);
            }
        });
    }

    @Override
    public void clearStamps() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Stamps.class);
            }
        });
    }

    @Override
    public RealmResults<DetailPackage> getRealmResultDetailPackage() {
        RealmResults<DetailPackage> realmResults = realm.where(DetailPackage.class).findAll();
        return realmResults;
    }

    @Override
    public RealmResults<Products> getRealmResultProducts(String id) {
        DetailPackage detailPackage = realm.where(DetailPackage.class).contains("packaged",id).findFirst();
        RealmResults<Products> realmResults = detailPackage.getProducts().where().findAll();
        Log.d(TAG,String.valueOf(realmResults.size()));
        return realmResults;
    }

    @Override
    public List<Colors> getListColors() {
        RealmResults<Colors> colorsList = realm.where(Colors.class).findAll();
        return colorsList;
    }

    @Override
    public List<Sizes> getListSizes() {
        RealmResults<Sizes> sizesList = realm.where(Sizes.class).findAll();
        return sizesList;
    }

    @Override
    public Products getProducts(String id) {
        Products products = realm.where(Products.class).contains("id",id).findFirst();
        return products;
    }

    @Override
    public String getProductName(String id) {
        SubMainTypes types = realm.where(SubMainTypes.class).contains("type",id).findFirst();
        return types.getReadable();
    }

    @Override
    public String getProductSize(String id) {
        Sizes sizes = realm.where(Sizes.class).contains("size",id).findFirst();
        return sizes.getName();
    }

    @Override
    public String getProductColor(String id) {
        Colors colors = realm.where(Colors.class).contains("code",id).findFirst();
        return colors.getName();
    }

    @Override
    public int getReqProduct(final String id) {
        req = 0;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DetailPackage detailPackages = realm.where(DetailPackage.class).contains("packaged", id).findFirst();
                List<Products> products = detailPackages.getProducts();
                for (Products p : products) {
                    req += p.getRequest();
                }
            }
        });
        return req;
    }

    @Override
    public int getAvailProduct(final String id) {
        avail = 0;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DetailPackage detailPackages = realm.where(DetailPackage.class).contains("packaged",id).findFirst();
                List<Products>  products = detailPackages.getProducts();
                for(Products p : products) {
                        avail += Integer.parseInt(p.getAvailable());
                }
            }
        });
        return avail;
    }

    @Override
    public DetailPackage getDetailPackage(String id) {
        DetailPackage products = realm.where(DetailPackage.class).contains("packaged",id).findFirst();
        return products;
    }
}
