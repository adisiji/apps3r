package nb.scode.a3rapps.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.ui.main.MainHomeActivity;
import nb.scode.a3rapps.util.ActivityUtils;
import nb.scode.a3rapps.util.CustomJsInterface;

public class LoginActivity extends AppCompatActivity implements LoginFragment.Callback,
        CustomJsInterface.CallbackJs {

    @Inject
    LoginPresenter mPresenter;

    @Override
    public void sendMeta(final int uid, final String key, final String nama,
                         final int saldo, final String prodTersimpan, final int prodTersimpanMaks) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPresenter.saveMeta(uid, key, nama, saldo, prodTersimpan, prodTersimpanMaks);
            }
        });
    }

    @Override
    public void hideWeb() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPresenter.hideWeb();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginFragment loginFragment = (LoginFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(loginFragment==null){
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),loginFragment,R.id.contentFrame);
        }
        DaggerLoginComponent.builder().localDataComponent(App.getDataComponent())
                .loginPresenterModule(new LoginPresenterModule(loginFragment))
                .build().inject(this);
    }

    @Override
    public void goHome() {
        Intent i = new Intent(getApplicationContext(), MainHomeActivity.class);
        startActivity(i);
        finish();
    }
}
