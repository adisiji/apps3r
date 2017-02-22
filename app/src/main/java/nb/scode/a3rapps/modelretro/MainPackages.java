package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by neobyte on 2/11/2017.
 */

public class MainPackages {

    @SerializedName("packages")
    @Expose
    private HashMap<Integer, DetailPackage> packageMap = new HashMap<>();

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("request_count")
    @Expose
    private int req_count;

    @SerializedName("request_limit")
    @Expose
    private int req_limit;

    @SerializedName("time_limit")
    @Expose
    private int time_limit;

    public HashMap<Integer, DetailPackage> getPackageMap() {
        return packageMap;
    }

    public void setPackageMap(HashMap<Integer, DetailPackage> packageMap) {
        this.packageMap = packageMap;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReq_count() {
        return req_count;
    }

    public void setReq_count(int req_count) {
        this.req_count = req_count;
    }

    public int getReq_limit() {
        return req_limit;
    }

    public void setReq_limit(int req_limit) {
        this.req_limit = req_limit;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }
}
