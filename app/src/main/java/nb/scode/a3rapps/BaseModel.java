package nb.scode.a3rapps;

import java.util.Date;
import java.util.List;

/**
 * Created by neobyte on 2/8/2017.
 */

public interface BaseModel {

    void cache();

    void clearCache();

    interface OnDataLoaded<D>{
        void onSuccess(D data);
        void onFail(String error);
    }

    boolean isExistMeta();

    void checkStamps(long value);

    void saveStamps(long value);

}
