package nb.scode.a3rapps.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import biz.laenger.android.together.BasePresenterActivity;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.ui.login.LoginActivity;
import nb.scode.a3rapps.ui.pager.home.HomeFragment;

/**
 * Created by neobyte on 3/2/2017.
 */

public class MainHomeActivity extends BasePresenterActivity<MainView, MainPresenter, MainComponent> implements MainView, HomeFragment.Callback {

    private final String TAG = MainHomeActivity.class.getSimpleName();

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindString(R.string.link_gudang)
    String linkGudang;

    private CallbackCart mCallbackCart;

    public void setCallbackCart(CallbackCart c){
        mCallbackCart = c;
        Log.d(TAG, "setCallbackCart: finish");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        final MainPagerAdapter sectionsPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(sectionsPagerAdapter);
        mTabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getPresenter().onPagerItemSelected(position);

            }
        });
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null)
                tab.setCustomView(sectionsPagerAdapter.getTabView(i));
        }
    }

    @Override
    public void showPagerItem(int position) {
        viewPager.setCurrentItem(position, false);
    }

    @Override
    protected void inject(MainComponent component) {
        component.inject(this); // no-op for this activity
    }

    @Override
    protected MainComponent createComponent() {

        return App.getAppComponent().mainComponent(new MainModule());
    }

    @Override
    public Class<? extends MainPresenter> getTypeClazz() {
        return MainPresenter.class;
    }

    @Override
    public void login(){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void cart() {

        try{
            mTabLayout.getTabAt(1).select();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @Override
    public void gudang() {
        Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
        i.putExtra("title","Gudang");
        i.putExtra("link",linkGudang);
        startActivity(i);
    }

    @Override
    public void finishGetData() {
        mCallbackCart.finishGetData();
    }

    public interface CallbackCart{
        void finishGetData();
    }
}
