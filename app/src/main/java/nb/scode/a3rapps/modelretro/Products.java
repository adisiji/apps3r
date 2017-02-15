package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by neobyte on 2/13/2017.
 */

public class Products extends RealmObject{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("request")
    @Expose
    private int request;

    @SerializedName("available")
    @Expose
    private String available;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("expire_icon")
    @Expose
    private int expireIcon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getExpireIcon() {
        return expireIcon;
    }

    public void setExpireIcon(int expireIcon) {
        this.expireIcon = expireIcon;
    }
}
