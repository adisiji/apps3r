package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neobyte on 2/14/2017.
 */

public class Sizes extends RealmObject{

    @PrimaryKey
    @SerializedName("size")
    private String size;

    @SerializedName("name")
    private String name;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
