package nb.scode.a3rapps.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import nb.scode.a3rapps.R;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.progbar_webview)
    ProgressBar progressBar;
    @BindView(R.id.wv_general)
    WebView webView;
    @BindView(R.id.frameWV)
    FrameLayout frameLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        Intent i = getIntent();
        String link = i.getStringExtra("link");
        String title = i.getStringExtra("title");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        WebSettings webSettings = webView.getSettings();
        webSettings.setAppCacheEnabled(false);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                frameLayout.setVisibility(View.VISIBLE);
                progressBar.bringToFront();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                frameLayout.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl(link);
    }

}
