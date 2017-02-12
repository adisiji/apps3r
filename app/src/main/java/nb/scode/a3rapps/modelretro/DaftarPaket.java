package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by neobyte on 2/11/2017.
 */

public class DaftarPaket {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("debug")
    @Expose
    private List<Object> debug = null;
    @SerializedName("request_count")
    @Expose
    private String requestCount;
    @SerializedName("request_limit")
    @Expose
    private Integer requestLimit;
    @SerializedName("time_limit")
    @Expose
    private Integer timeLimit;

    @SerializedName("packages")
    @Expose
    private MainPackages packages;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Object> getDebug() {
        return debug;
    }

    public void setDebug(List<Object> debug) {
        this.debug = debug;
    }

    public String getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(String requestCount) {
        this.requestCount = requestCount;
    }

    public Integer getRequestLimit() {
        return requestLimit;
    }

    public void setRequestLimit(Integer requestLimit) {
        this.requestLimit = requestLimit;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public MainPackages getPackages() {
        return packages;
    }

    public void setPackages(MainPackages packages) {
        this.packages = packages;
    }
}
