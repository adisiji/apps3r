package nb.scode.a3rapps.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.home.HomeFragment;
import nb.scode.a3rapps.util.CustomJsInterface;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by neobyte on 2/8/2017.
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    private Unbinder unbinder;
    @BindView(R.id.wv_login)
    WebView wvLogin;
    @BindView(R.id.progbar_login)
    ProgressBar progressBar;
    private Callback callback;

    private final String TAG = LoginFragment.class.getSimpleName();

    public LoginFragment(){

    }

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter){
        mPresenter = checkNotNull(presenter,"Presenter cannot be null");
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this,root);
        WebSettings webSettings = wvLogin.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setSupportZoom(true);
        wvLogin.addJavascriptInterface(new CustomJsInterface(getActivity()),"HTMLOUT");
        wvLogin.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.bringToFront();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                view.loadUrl("javascript:window.HTMLOUT.showHTML(document.getElementsByTagName('body')[0].innerHTML);");
                super.onPageFinished(view, url);
            }
        });
        wvLogin.loadUrl(URL_LOGIN);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface Callback{
        void goHome();
    }

    @Override
    public void goHome() {
        callback.goHome();
    }

}
