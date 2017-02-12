package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by neobyte on 2/11/2017.
 */

public class RecipientDetail extends RealmObject{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private RealmList<RealmString> address;

    @SerializedName("jnelabel")
    @Expose
    private String jnelabel;
    @SerializedName("postalcode")
    @Expose
    private String postalcode;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("jne")
    @Expose
    private Boolean jne;
    @SerializedName("jneservice")
    @Expose
    private String jneservice;
    @SerializedName("notes")
    @Expose
    private String notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RealmList<RealmString> getAddress() {
        return address;
    }

    public void setAddress(RealmList<RealmString> address) {
        this.address = address;
    }

    public String getJnelabel() {
        return jnelabel;
    }

    public void setJnelabel(String jnelabel) {
        this.jnelabel = jnelabel;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Boolean getJne() {
        return jne;
    }

    public void setJne(Boolean jne) {
        this.jne = jne;
    }

    public String getJneservice() {
        return jneservice;
    }

    public void setJneservice(String jneservice) {
        this.jneservice = jneservice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
