package nb.scode.a3rapps.localdata;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.modelrealm.JneRealm;
import nb.scode.a3rapps.modelrealm.LastUpdateLocal;
import nb.scode.a3rapps.modelrealm.MetaRealm;
import nb.scode.a3rapps.modelretro.DataJne;
import nb.scode.a3rapps.modelretro.DataStatis;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.MainPackages;
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

    private MetaRealm metaRealm;

    private ApiService apiService;
    private Gson gson;
    private Realm realm;
    private NetworkManager networkManager;
    private final String TAG = LocalDataRepo.class.getSimpleName();
    private static final String no_inet_connet = "No Internet Connection";

    @Inject
    public LocalDataRepo(ApiService apiService, Realm realm, NetworkManager networkManager){
        this.apiService = apiService;
        this.realm = realm;
        this.networkManager = networkManager;
    }

    @Override
    public boolean getMeta() {
        metaRealm = realm.where(MetaRealm.class).findFirst();
        if(metaRealm!=null){
            return true;
        }
        else {
            //setDummy();
            return false;
        }
    }

    @Override
    public void setMeta(int uid, String key, String nama, int saldo,
                        String prodTersimpan, int prodTersimpanMaks, LoadTaskCallback callback) {
        MetaRealm metaRealm = new MetaRealm();
        metaRealm.setId(uid);
        metaRealm.setKey(key);
        metaRealm.setNama(nama);
        metaRealm.setSaldo(saldo);
        metaRealm.setProdukTersimpan(prodTersimpan);
        metaRealm.setProdukTersimpanMaks(prodTersimpanMaks);
        realm.beginTransaction();
        realm.insertOrUpdate(metaRealm);
        realm.commitTransaction();
        this.metaRealm = metaRealm;
        callback.success();
    }

    @Override
    public String getName() {
        return metaRealm.getNama();
    }

    @Override
    public boolean isEmptyLocalStamp() {
        if(realm.where(LastUpdateLocal.class).findFirst() == null)
            return true;
        else
            return false;
    }

    @Override
    public long getLocalStampJne() {
        LastUpdateLocal local = realm.where(LastUpdateLocal.class).findFirst();
        if(local != null){
            return local.getLastDataJne();
        }
        else {
            return 0;
        }
    }

    @Override
    public long getLocalStampStatis() {
        LastUpdateLocal local = realm.where(LastUpdateLocal.class).findFirst();
        if(local != null){
            return local.getLastDataStatis();
        }
        else {
            return 0;
        }
    }

    @Override
    public void compareCacheStamp() {

    }

    @Override
    public void getDataStatis(final LoadTaskCallback callback) {
        if(networkManager.isConnected()){
            apiService.ambilDataStatis(metaRealm.getId(),metaRealm.getKey())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceObserver<DataStatis>() {
                        @Override
                        public void onNext(DataStatis value) {
                            if(value.getStatus() > 0){
                                Log.d(TAG,"next->"+value.getMainTypes().keySet());
                                List<SubMainTypes> typesList = new ArrayList<>();
                                for(String key: value.getMainTypes().keySet()){
                                    typesList.add(value.getMainTypes().get(key));
                                }
                                realm.beginTransaction();
                                realm.insertOrUpdate(typesList);
                                realm.commitTransaction();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG,"getDataStatis Error -> "+e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            callback.success();
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
            apiService.ambilDataJne(metaRealm.getId(),metaRealm.getKey())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceObserver<DataJne>() {
                        @Override
                        public void onNext(DataJne value) {
                            if(value.getStatus() > 0){
                                realm.beginTransaction();
                                List<JneRealm> jneRealmList = value.getListJne();
                                realm.insertOrUpdate(jneRealmList);
                                realm.commitTransaction();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG,"getDataJne Error -> "+e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            callback.success();
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
            apiService.ambilDaftarPaket(metaRealm.getId(),metaRealm.getKey())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceObserver<MainPackages>() {
                        @Override
                        public void onNext(MainPackages value) {
                            if(value.getStatus()>0){
                                List<DetailPackage> typesList = new ArrayList<>();
                                for(Integer key: value.getPackageMap().keySet()){
                                    typesList.add(value.getPackageMap().get(key));
                                }
                                SubMainPackage subMainPackage = new SubMainPackage();
                                subMainPackage.setRequest_count(value.getReq_count());
                                subMainPackage.setRequest_limit(value.getReq_limit());
                                subMainPackage.setTime_limit(value.getTime_limit());
                                realm.beginTransaction();
                                realm.insertOrUpdate(typesList);
                                realm.insertOrUpdate(subMainPackage);
                                realm.commitTransaction();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG,"getDaftarPaket Error -> "+e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "getDaftarPaket => Completed");
                            callback.success();
                        }
                    });
        }
        else {
            callback.failed(no_inet_connet);
        }
    }

    @Override
    public void emptyPaket() {

    }

    @Override
    public void getStamp(final UpdateCacheCallback callback) {
        Log.d(TAG,"uid : "+String.valueOf(metaRealm.getId())
                +" key : "+String.valueOf(metaRealm.getKey()));
        if(networkManager.isConnected()){
            apiService.ambilMeta(metaRealm.getId(),metaRealm.getKey())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceObserver<StampsRetro>() {
                        @Override
                        public void onNext(StampsRetro value) {
                            if(value.getStatus()>0){
                                Stamps stampx = value.getStamps();
                                if(stampx!=null){
                                    if(getLocalStampStatis() < stampx.getAmbilDataStatis() &&
                                            getLocalStampJne() < stampx.getAmbilDataJne()){
                                        clearStamps();
                                        realm.beginTransaction();
                                        LastUpdateLocal local = new LastUpdateLocal();
                                        local.setLastDataJne(stampx.getAmbilDataJne());
                                        local.setLastDataStatis(stampx.getAmbilDataStatis());
                                        realm.insertOrUpdate(local);
                                        realm.commitTransaction();
                                    }
                                    else if(getLocalStampStatis() < stampx.getAmbilDataStatis()){
                                        realm.beginTransaction();
                                        LastUpdateLocal stamps = realm.where(LastUpdateLocal.class)
                                                .equalTo("lastDataStatis",getLocalStampStatis()).findFirst();
                                        stamps.setLastDataStatis(stampx.getAmbilDataStatis());
                                        realm.commitTransaction();
                                        callback.updateStatis();
                                    }
                                    else if(getLocalStampJne() < stampx.getAmbilDataJne()){
                                        realm.beginTransaction();
                                        LastUpdateLocal stamps = realm.where(LastUpdateLocal.class)
                                                .equalTo("lastDataJne",getLocalStampJne()).findFirst();
                                        stamps.setLastDataJne(stampx.getAmbilDataJne());
                                        realm.commitTransaction();
                                        callback.updateJne();
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
        try {
            realm.delete(MetaRealm.class);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearStamps() {
        if(!isEmptyLocalStamp()){
            try {
                realm.delete(Stamps.class);
            }
            catch (IllegalStateException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public RealmResults<DetailPackage> getRealmResultDetailPackage() {
        realm.beginTransaction();
        RealmResults<DetailPackage> realmResults = realm.where(DetailPackage.class).findAll();
        realm.commitTransaction();
        return realmResults;
    }
}
