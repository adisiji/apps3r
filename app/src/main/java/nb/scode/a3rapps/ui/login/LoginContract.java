package nb.scode.a3rapps.ui.login;

/**
 * Created by neobyte on 2/8/2017.
 */

public interface LoginContract {

    interface Presenter {

        void saveMeta(int uid, String key, String nama ,
                      int saldo, String prodTersimpan,
                      int prodTersimpanMaks);

        void hideWeb();

    }

    interface View {

        String URL_LOGIN = "https://tigaer.id/login?app";

        void setPresenter(Presenter presenter);

        void goHome();

        void hideWeb();

    }
}
