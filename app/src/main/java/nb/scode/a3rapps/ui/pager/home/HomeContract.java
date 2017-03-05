package nb.scode.a3rapps.ui.pager.home;

/**
 * Created by neobyte on 2/8/2017.
 */

public interface HomeContract {

    interface Presenter {

        void start();

        void getFirstData();

        void getDataJne();

        void getDataStatis();

        void getStamp();

    }

    interface View {

        String URL_GUDANG = "http://tigaer.id/gudang";

        void finishGetData();

        void hideLoadCart();

        void showFirstUpdate(String message);

        void showUpdateJneStatis(String message);

        void showUpdateJne(String message);

        void showUpdateStatis(String message);

        void showSuccessUpdate(String message);

        void showFailedUpdate(String message);

        void goLogin();

    }

}
