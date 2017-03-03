package nb.scode.a3rapps.ui.pager.cart;

import io.realm.RealmResults;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.Products;

/**
 * Created by neobyte on 2/11/2017.
 */

public interface CartContract {

    interface Presenter {

        RealmResults<DetailPackage> getRealmResultDetailPackage();

        String getName();

        String getProductName(String id);

        String getProductColor(String id);

        String getProductSize(String id);

        long getSaldo();

        int getReqLimit();

        int getReqCount();

        int getTimeLimit();

        int getReqPrduct(String id);

        int getAvailProduct(String id);

        RealmResults<Products> getRealmResultsProduct(String id);

    }

    interface View {

        void setView();

    }

}
