package nb.scode.a3rapps.localdata;

import java.util.List;

import io.realm.RealmResults;
import nb.scode.a3rapps.modelretro.Colors;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.Products;
import nb.scode.a3rapps.modelretro.Sizes;

/**
 * Created by neobyte on 2/9/2017.
 */

public interface LocalDataTask {

    boolean getMeta();

    void setMeta(int uid, String key, String nama ,
                 int saldo, String prodTersimpan,
                 int prodTersimpanMaks, LoadTaskCallback callback);

    String getName();

    int getSaldo();

    void getDataStatis(LoadTaskCallback callback);

    void getDataJne(LoadTaskCallback callback);

    void getDaftarPaket(LoadTaskCallback callback);

    void emptyPaket();

    void getStamp(UpdateCacheCallback cacheCallback);

    boolean isEmptyLocalStamp();

    long getLocalStampStatis();

    long getLocalStampJne();

    int getReqCount();

    int getReqLimit();

    int getTimeLimit();

    void compareCacheStamp();

    RealmResults<DetailPackage> getRealmResultDetailPackage();

    RealmResults<Products> getRealmResultProducts(String id);

    Products getProducts(String id);

    int getAvailProduct(String id);

    int getReqProduct(String id);

    DetailPackage getDetailPackage(String id);

    String getProductName(String id);

    String getProductColor(String id);

    String getProductSize(String id);

    List<Sizes> getListSizes();

    List<Colors> getListColors();

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

        void updateJneStatis();

        void updateJne();

        void updateStatis();

        void success();

        void failed(String message);

    }

}
