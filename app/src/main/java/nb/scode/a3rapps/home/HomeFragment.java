package nb.scode.a3rapps.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.login.LoginActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by neobyte on 2/8/2017.
 */

public class HomeFragment extends Fragment implements HomeContract.View {

    private Unbinder unbinder;
    private HomeContract.Presenter mPresenter;
    private Callback callback;

    public HomeFragment(){

    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void setPresenter(@NonNull HomeContract.Presenter presenter){
        mPresenter = checkNotNull(presenter,"Presenter cannot be null");
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.destroy();
        unbinder.unbind();
    }

    @Override
    public void showLoading(){

    }

    @OnClick(R.id.btn_webview_gudang)
    void gudang(){

    }

    @OnClick(R.id.btn_cart)
    void cart(){

    }

    @Override
    public void goLogin(){
        callback.login();
    }

    public interface Callback{
        void login();
    }

}
