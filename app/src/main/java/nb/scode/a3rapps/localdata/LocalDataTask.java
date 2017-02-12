package nb.scode.a3rapps.localdata;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;
import nb.scode.a3rapps.modelretro.DetailPackage;

/**
 * Created by neobyte on 2/9/2017.
 */

public interface LocalDataTask {

    boolean getMeta();

    void setMeta(int uid, String key, String nama ,
                 int saldo, String prodTersimpan,
                 int prodTersimpanMaks, LoadTaskCallback callback);

    String getName();

    void getDataStatis(LoadTaskCallback callback);

    void getDataJne(LoadTaskCallback callback);

    void getDaftarPaket(LoadTaskCallback callback);

    void emptyPaket();

    void getStamp(UpdateCacheCallback cacheCallback);

    boolean isEmptyLocalStamp();

    long getLocalStampStatis();

    long getLocalStampJne();

    void compareCacheStamp();

    RealmResults<DetailPackage> getRealmResultDetailPackage();

    void setStamp();

    void clearMeta();

    void clearStamps();

    interface LoadTaskCallback{

        void success();

        void failed(String message);
    }

    interface UpdteFirstTime{

        void updateFirstTime();

    }

    interface UpdateCacheCallback{

        void updateJne();

        void updateStatis();

        void success();

        void failed(String message);

    }

}
