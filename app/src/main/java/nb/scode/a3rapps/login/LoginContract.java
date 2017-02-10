package nb.scode.a3rapps.login;

import nb.scode.a3rapps.BasePresenter;
import nb.scode.a3rapps.BaseView;

/**
 * Created by neobyte on 2/8/2017.
 */

public interface LoginContract {

    interface Presenter extends BasePresenter {

        void saveMeta(int uid, String key);

    }

    interface View extends BaseView<Presenter>{

        String URL_LOGIN = "https://dev.tigaer.id/login?app";

        void goHome();

    }
}
