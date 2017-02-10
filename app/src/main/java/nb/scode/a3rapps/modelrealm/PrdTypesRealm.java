package nb.scode.a3rapps.modelrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neobyte on 2/8/2017.
 */

public class PrdTypesRealm extends RealmObject {

    @PrimaryKey
    private String type;
    private String readable;
    private int patternend;
    private String sizes;
    private int sorter;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public int getPatternend() {
        return patternend;
    }

    public void setPatternend(int patternend) {
        this.patternend = patternend;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public int getSorter() {
        return sorter;
    }

    public void setSorter(int sorter) {
        this.sorter = sorter;
    }
}
