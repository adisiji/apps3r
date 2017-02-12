package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import nb.scode.a3rapps.modelrealm.JneRealm;

/**
 * Created by neobyte on 2/11/2017.
 */

public class DataJne {

    @SerializedName("status")
    private int status;

    @SerializedName("jne")
    private List<JneRealm> listJne;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<JneRealm> getListJne() {
        return listJne;
    }

    public void setListJne(List<JneRealm> listJne) {
        this.listJne = listJne;
    }


}
