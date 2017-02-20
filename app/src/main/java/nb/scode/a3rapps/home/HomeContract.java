package nb.scode.a3rapps.home;

import nb.scode.a3rapps.BasePresenter;
import nb.scode.a3rapps.BaseView;

/**
 * Created by neobyte on 2/8/2017.
 */

public interface HomeContract {

    interface Presenter extends BasePresenter {

        void getFirstData();

        void getDataJne();

        void getDataStatis();

        void getStamp();

    }

    interface View extends BaseView<Presenter> {

        String URL_GUDANG = "http://tigaer.id/gudang";

        void showLoading();

        void showFirstUpdate(String message);

        void showUpdateJne(String message);

        void showUpdateStatis(String message);

        void showSuccessUpdate(String message);

        void showFailedUpdate(String message);

        void goLogin();

    }

}
