package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by neobyte on 2/11/2017.
 */

public class DetailMetaJne extends RealmObject {

    @SerializedName("service_display")
    @Expose
    private String serviceDisplay;
    @SerializedName("service_code")
    @Expose
    private String serviceCode;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("etd_from")
    @Expose
    private String etdFrom;
    @SerializedName("etd_thru")
    @Expose
    private String etdThru;

    public String getServiceDisplay() {
        return serviceDisplay;
    }

    public void setServiceDisplay(String serviceDisplay) {
        this.serviceDisplay = serviceDisplay;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEtdFrom() {
        return etdFrom;
    }

    public void setEtdFrom(String etdFrom) {
        this.etdFrom = etdFrom;
    }

    public String getEtdThru() {
        return etdThru;
    }

    public void setEtdThru(String etdThru) {
        this.etdThru = etdThru;
    }
}
