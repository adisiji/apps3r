package nb.scode.a3rapps.ui.pager.profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.BasePagerFragment;
import nb.scode.a3rapps.R;

/**
 * Created by neobyte on 3/3/2017.
 */

public class ProfileFragment extends BasePagerFragment<ProfileView, ProfilePresenter, ProfileComponent> implements ProfileView {

    @BindView(R.id.wv_profile)
    WebView mWebView;
    @BindView(R.id.progbar_profile)
    ProgressBar mProgressBar;
    @BindString(R.string.link_profiles)
    String linkProfile;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this,root);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.bringToFront();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        mWebView.loadUrl(linkProfile);
        return root;
    }

    @Override
    public Class<? extends ProfilePresenter> getTypeClazz() {

        return ProfilePresenter.class;
    }

    @Override
    protected ProfileComponent createComponent() {

        return App.getPagerComponent().mProfileComponent(new ProfileModule());
    }
}
