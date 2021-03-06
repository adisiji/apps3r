package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neobyte on 2/11/2017.
 */

public class SubMainTypes extends RealmObject{

    @PrimaryKey
    @SerializedName("type")
    private String type;

    @SerializedName("readable")
    private boolean readable;

    @SerializedName("patterned")
    private int patterned;

    @SerializedName("sorter")
    private int sorter;

    @SerializedName("sizes")
    private RealmList<RealmString> sizes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public int getPatterned() {
        return patterned;
    }

    public void setPatterned(int patterned) {
        this.patterned = patterned;
    }

    public int getSorter() {
        return sorter;
    }

    public void setSorter(int sorter) {
        this.sorter = sorter;
    }

    public RealmList<RealmString> getSizes() {
        return sizes;
    }

    public void setSizes(RealmList<RealmString> sizes) {
        this.sizes = sizes;
    }
}
