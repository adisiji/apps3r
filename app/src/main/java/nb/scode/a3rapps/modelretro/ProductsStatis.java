package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by neobyte on 2/14/2017.
 */

public class ProductsStatis extends RealmObject{

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
