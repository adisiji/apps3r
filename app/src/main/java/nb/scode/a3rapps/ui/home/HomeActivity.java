package nb.scode.a3rapps.ui.home;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.ButterKnife;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.ui.WebViewActivity;
import nb.scode.a3rapps.ui.cart.CartActivity;
import nb.scode.a3rapps.ui.login.LoginActivity;
import nb.scode.a3rapps.util.ActivityUtils;

public class HomeActivity extends AppCompatActivity implements HomeFragment.Callback {

    @Inject
    HomePresenter homePresenter;
    @BindString(R.string.link_gudang)
    String linkGudang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(homeFragment==null){
            homeFragment = HomeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), homeFragment, R.id.contentFrame);
        }
        DaggerHomeComponent.builder().localDataComponent(App.getDataComponent())
                .homePresenterModule(new HomePresenterModule(homeFragment))
                .build().inject(this);
        if(Build.VERSION.SDK_INT >= 21){
            setTransition();
        }
    }

    @TargetApi(21)
    private void setTransition(){
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }

    @Override
    public void login(){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void cart() {
        Intent i = new Intent(getApplicationContext(), CartActivity.class);
        startActivity(i);
    }

    @Override
    public void gudang() {
        Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
        i.putExtra("title","Gudang");
        i.putExtra("link",linkGudang);
        startActivity(i);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

    }
}