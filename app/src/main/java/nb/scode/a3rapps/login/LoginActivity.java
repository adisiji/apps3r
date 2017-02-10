package nb.scode.a3rapps.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.home.HomeActivity;
import nb.scode.a3rapps.localdata.DaggerLocalDataComponent;
import nb.scode.a3rapps.localdata.LocalDataModule;
import nb.scode.a3rapps.util.ActivityUtils;
import nb.scode.a3rapps.util.CustomJsInterface;

public class LoginActivity extends AppCompatActivity implements LoginFragment.Callback,
        CustomJsInterface.CallbackJs {

    @Inject
    LoginPresenter mPresenter;

    @Override
    public void sendMeta(final int uid, final String key){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPresenter.saveMeta(uid, key);
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
        DaggerLoginComponent.builder().localDataComponent(DaggerLocalDataComponent.builder()
                .appComponent(App.getAppComponent()).localDataModule(new LocalDataModule()).build())
                .loginPresenterModule(new LoginPresenterModule(loginFragment))
                .build().inject(this);
    }

    @Override
    public void goHome() {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i);
        finish();
    }
}
