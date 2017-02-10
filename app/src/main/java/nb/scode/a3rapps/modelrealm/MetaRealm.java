package nb.scode.a3rapps.modelrealm;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neobyte on 2/8/2017.
 */

public class MetaRealm extends RealmObject {

    @PrimaryKey
    @SerializedName("uid")
    private int id;

    private String key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
