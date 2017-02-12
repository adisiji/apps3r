package nb.scode.a3rapps.modelrealm;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import nb.scode.a3rapps.modelretro.DetailMetaJne;

/**
 * Created by neobyte on 2/8/2017.
 */

public class JneRealm extends RealmObject {

    @PrimaryKey
    @SerializedName("jne")
    private String jne;

    @SerializedName("label")
    private String label;

    @SerializedName("meta")
    private RealmList<DetailMetaJne> metaList = null;

    public String getJne() {
        return jne;
    }

    public void setJne(String jne) {
        this.jne = jne;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<DetailMetaJne> getMetaList() {
        return metaList;
    }

    public void setMetaList(RealmList<DetailMetaJne> metaList) {
        this.metaList = metaList;
    }
}
