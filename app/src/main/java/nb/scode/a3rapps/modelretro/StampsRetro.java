package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by neobyte on 2/10/2017.
 */

public class StampsRetro {

    @SerializedName("status")
    private int status;

    @SerializedName("stamps")
    private Stamps stamps;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Stamps getStamps() {
        return stamps;
    }

    public void setStamps(Stamps stamps) {
        this.stamps = stamps;
    }
}
