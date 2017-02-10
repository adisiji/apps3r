package nb.scode.a3rapps.localdata;

/**
 * Created by neobyte on 2/9/2017.
 */

public interface LocalDataTask {

    boolean getMeta();

    void setMeta(int uid, String key, LoadTaskCallback callback);

    void updateDataStatis();

    void getDataJne();

    void getDaftarPaket();

    void emptyPaket();

    void getStamp(LoadTaskCallback callback);

    void setStamp();

    interface LoadTaskCallback{

        void success();

        void failed(String message);
    }

}
