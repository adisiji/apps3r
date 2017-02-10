package nb.scode.a3rapps.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.localdata.DaggerLocalDataComponent;
import nb.scode.a3rapps.localdata.LocalDataModule;
import nb.scode.a3rapps.login.LoginActivity;
import nb.scode.a3rapps.util.ActivityUtils;

public class HomeActivity extends AppCompatActivity implements HomeFragment.Callback {

    @Inject
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(homeFragment==null){
            homeFragment = HomeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), homeFragment, R.id.contentFrame);
        }
        DaggerHomeComponent.builder().localDataComponent(DaggerLocalDataComponent.builder()
                .localDataModule(new LocalDataModule()).appComponent(App.getAppComponent()).build())
                .homePresenterModule(new HomePresenterModule(homeFragment))
                .build().inject(this);
    }

    @Override
    public void login(){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

}
