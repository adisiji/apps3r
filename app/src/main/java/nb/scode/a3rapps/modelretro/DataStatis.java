package nb.scode.a3rapps.modelretro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import io.realm.RealmList;

/**
 * Created by neobyte on 2/11/2017.
 */

public class DataStatis {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("debug")
    private Object debug;

    @SerializedName("types")
    private HashMap<String, SubMainTypes> mainTypes;

    @SerializedName("sizes")
    private RealmList<Sizes> sizesRealmList;

    @SerializedName("colors")
    private RealmList<Colors> colorsRealmList;

    @SerializedName("products")
    private RealmList<ProductsStatis> productsStatisRealmList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getDebug() {
        return debug;
    }

    public void setDebug(Object debug) {
        this.debug = debug;
    }

    public HashMap<String, SubMainTypes> getMainTypes() {
        return mainTypes;
    }

    public void setMainTypes(HashMap<String, SubMainTypes> mainTypes) {
        this.mainTypes = mainTypes;
    }

    public RealmList<Sizes> getSizesRealmList() {
        return sizesRealmList;
    }

    public void setSizesRealmList(RealmList<Sizes> sizesRealmList) {
        this.sizesRealmList = sizesRealmList;
    }

    public RealmList<Colors> getColorsRealmList() {
        return colorsRealmList;
    }

    public void setColorsRealmList(RealmList<Colors> colorsRealmList) {
        this.colorsRealmList = colorsRealmList;
    }

    public RealmList<ProductsStatis> getProductsStatisRealmList() {
        return productsStatisRealmList;
    }

    public void setProductsStatisRealmList(RealmList<ProductsStatis> productsStatisRealmList) {
        this.productsStatisRealmList = productsStatisRealmList;
    }
}
