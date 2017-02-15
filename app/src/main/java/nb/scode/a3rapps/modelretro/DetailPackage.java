package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neobyte on 2/11/2017.
 */

public class DetailPackage extends RealmObject {

    @PrimaryKey
    @SerializedName("package")
    @Expose
    private String packaged;

    @SerializedName("keranjang")
    @Expose
    private String keranjang;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("value")
    @Expose
    private Integer value;

    @SerializedName("is_confirmable")
    @Expose
    private Boolean isConfirmable;

    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("recipient")
    @Expose
    private int recipient;
    @SerializedName("include_details")
    @Expose
    private Boolean includeDetails;
    @SerializedName("packagenote")
    @Expose
    private String packagenote;
    @SerializedName("batas_waktu")
    @Expose
    private Integer batasWaktu;

    @SerializedName("products")
    @Expose
    private RealmList<Products> products;

    @SerializedName("recipient_detail")
    @Expose
    private RecipientDetail recipientDetailList;

    public String getPackaged() {
        return packaged;
    }

    public void setPackaged(String packaged) {
        this.packaged = packaged;
    }

    public String getKeranjang() {
        return keranjang;
    }

    public void setKeranjang(String keranjang) {
        this.keranjang = keranjang;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getConfirmable() {
        return isConfirmable;
    }

    public void setConfirmable(Boolean confirmable) {
        isConfirmable = confirmable;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public Boolean getIncludeDetails() {
        return includeDetails;
    }

    public void setIncludeDetails(Boolean includeDetails) {
        this.includeDetails = includeDetails;
    }

    public String getPackagenote() {
        return packagenote;
    }

    public void setPackagenote(String packagenote) {
        this.packagenote = packagenote;
    }

    public Integer getBatasWaktu() {
        return batasWaktu;
    }

    public void setBatasWaktu(Integer batasWaktu) {
        this.batasWaktu = batasWaktu;
    }

    public RecipientDetail getRecipientDetailList() {
        return recipientDetailList;
    }

    public void setRecipientDetailList(RecipientDetail recipientDetailList) {
        this.recipientDetailList = recipientDetailList;
    }

    public RealmList<Products> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Products> products) {
        this.products = products;
    }
}
