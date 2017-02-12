package nb.scode.a3rapps.modelretro;

import io.realm.RealmObject;

/**
 * Created by neobyte on 2/12/2017.
 */

public class SubMainPackage extends RealmObject {

    private int request_count;

    private int request_limit;

    private int time_limit;

    public int getRequest_count() {
        return request_count;
    }

    public void setRequest_count(int request_count) {
        this.request_count = request_count;
    }

    public int getRequest_limit() {
        return request_limit;
    }

    public void setRequest_limit(int request_limit) {
        this.request_limit = request_limit;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }
}
