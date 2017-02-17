package nb.scode.a3rapps.catat;

import java.util.List;

import io.realm.RealmResults;
import nb.scode.a3rapps.BasePresenter;
import nb.scode.a3rapps.BaseView;
import nb.scode.a3rapps.modelretro.Colors;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.Products;
import nb.scode.a3rapps.modelretro.Sizes;

/**
 * Created by neobyte on 2/13/2017.
 */

public interface CatatContract {

    interface Presenter extends BasePresenter {

        List<Colors> getColorList();

        List<Sizes> getSizesList();

        DetailPackage getDetailPackage(String id);

        RealmResults<Products> getRealmResultsProduct(String id);

        String getName();

        int getSaldo();

    }

    interface View extends BaseView<Presenter> {

        String getCatatan(String id);

    }
}
