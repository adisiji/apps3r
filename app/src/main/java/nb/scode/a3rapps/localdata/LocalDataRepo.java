package nb.scode.a3rapps.localdata;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import nb.scode.a3rapps.modelrealm.MetaRealm;
import nb.scode.a3rapps.modelretro.Stamps;
import nb.scode.a3rapps.modelretro.StampsRetro;
import nb.scode.a3rapps.network.ApiService;
import nb.scode.a3rapps.util.NetworkManager;

/**
 * Created by neobyte on 2/9/2017.
 */

public class LocalDataRepo implements LocalDataTask {

    MetaRealm metaRealm;

    private ApiService apiService;
    private Realm realm;
    private NetworkManager networkManager;
    private final String TAG = LocalDataRepo.class.getSimpleName();
    private Long stampjne, stampstatis;

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
    public void setMeta(final int uid, final String key, LoadTaskCallback callback) {
        MetaRealm metaRealm = new MetaRealm();
        metaRealm.setId(uid);
        metaRealm.setKey(key);
        realm.beginTransaction();
        realm.insertOrUpdate(metaRealm);
        realm.commitTransaction();
        this.metaRealm = metaRealm;
        callback.success();
    }

    @Override
    public void updateDataStatis() {

    }

    @Override
    public void getDataJne() {

    }

    @Override
    public void getDaftarPaket() {

    }

    @Override
    public void emptyPaket() {

    }

    @Override
    public void getStamp(final LoadTaskCallback callback) {
        Log.d(TAG,"uid : "+String.valueOf(metaRealm.getId())
                +" key : "+String.valueOf(metaRealm.getKey()));
        apiService.ambilMeta(metaRealm.getId(),metaRealm.getKey())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceObserver<StampsRetro>() {
                    @Override
                    public void onNext(StampsRetro value) {
                        if(value.getStatus()>0){
                            Stamps stampx = value.getStamps();
                            if(stampx!=null){
                                stampjne = stampx.getAmbilDataJne();
                                stampstatis = stampx.getAmbilDataStatis();
                                callback.success();
                                Log.d(TAG, "getStamp => " +String.valueOf(stampjne));
                            }
                            else{
                                Log.d(TAG, "getStamp => null stampx");
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
                        Log.d(TAG, "getStamp => Completed");
                    }
                });
    }

    @Override
    public void setStamp() {

    }
}
