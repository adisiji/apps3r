package nb.scode.a3rapps.home;

import nb.scode.a3rapps.BaseModel;
import nb.scode.a3rapps.BasePresenter;
import nb.scode.a3rapps.BaseView;

/**
 * Created by neobyte on 2/8/2017.
 */

public interface HomeContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

        String URL_GUDANG = "http://tigaer.id/gudang";

        void showLoading();

        void goLogin();

    }

}
