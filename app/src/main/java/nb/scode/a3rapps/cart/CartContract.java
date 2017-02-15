package nb.scode.a3rapps.cart;

import java.util.List;

import io.realm.RealmResults;
import nb.scode.a3rapps.BasePresenter;
import nb.scode.a3rapps.BaseView;
import nb.scode.a3rapps.modelretro.DetailPackage;

/**
 * Created by neobyte on 2/11/2017.
 */

public interface CartContract {

    interface Presenter extends BasePresenter {

        RealmResults<DetailPackage> getRealmResultDetailPackage();

        String getName();

        long getSaldo();

        int getReqLimit();

        int getReqCount();

        int getTimeLimit();

    }

    interface View extends BaseView<Presenter>{

    }

}
