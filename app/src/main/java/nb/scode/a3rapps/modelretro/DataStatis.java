package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neobyte on 2/11/2017.
 */

public class DataStatis {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("debug")
    private Object debug;

    @SerializedName("types")
    private HashMap<String, SubMainTypes> mainTypes;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getDebug() {
        return debug;
    }

    public void setDebug(Object debug) {
        this.debug = debug;
    }

    public HashMap<String, SubMainTypes> getMainTypes() {
        return mainTypes;
    }

    public void setMainTypes(HashMap<String, SubMainTypes> mainTypes) {
        this.mainTypes = mainTypes;
    }
}
