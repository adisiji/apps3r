package nb.scode.a3rapps.modelrealm;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by neobyte on 2/8/2017.
 */

public class MetaRealm extends RealmObject {

    @PrimaryKey
    @SerializedName("uid")
    private int id;

    @SerializedName("key")
    private String key;

    @SerializedName("nama")
    private String nama;

    @SerializedName("saldo")
    private int saldo;

    @SerializedName("produk_tersimpan")
    private String produkTersimpan;

    @SerializedName("produk_tersimpan_maks")
    private int produkTersimpanMaks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getProdukTersimpan() {
        return produkTersimpan;
    }

    public void setProdukTersimpan(String produkTersimpan) {
        this.produkTersimpan = produkTersimpan;
    }

    public int getProdukTersimpanMaks() {
        return produkTersimpanMaks;
    }

    public void setProdukTersimpanMaks(int produkTersimpanMaks) {
        this.produkTersimpanMaks = produkTersimpanMaks;
    }
}
