package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by neobyte on 2/10/2017.
 */

public class Stamps extends RealmObject{

    @SerializedName("ambilDataStatis")
    private long ambilDataStatis;
    @SerializedName("ambilDataJne")
    private long ambilDataJne;

    public long getAmbilDataStatis() {
        return ambilDataStatis;
    }

    public void setAmbilDataStatis(long ambilDataStatis) {
        this.ambilDataStatis = ambilDataStatis;
    }

    public long getAmbilDataJne() {
        return ambilDataJne;
    }

    public void setAmbilDataJne(long ambilDataJne) {
        this.ambilDataJne = ambilDataJne;
    }
}
