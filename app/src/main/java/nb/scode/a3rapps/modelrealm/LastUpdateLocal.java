package nb.scode.a3rapps.modelrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neobyte on 2/11/2017.
 */

public class LastUpdateLocal extends RealmObject {

    @PrimaryKey
    private int id;

    private long lastDataStatis;

    private long lastDataJne;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLastDataStatis() {
        return lastDataStatis;
    }

    public void setLastDataStatis(long lastDataStatis) {
        this.lastDataStatis = lastDataStatis;
    }

    public long getLastDataJne() {
        return lastDataJne;
    }

    public void setLastDataJne(long lastDataJne) {
        this.lastDataJne = lastDataJne;
    }
}
