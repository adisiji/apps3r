package nb.scode.a3rapps.modelrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neobyte on 2/8/2017.
 */

public class JneRealm extends RealmObject {

    @PrimaryKey
    private String jne;
    private String label;
    private String meta;

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

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}
