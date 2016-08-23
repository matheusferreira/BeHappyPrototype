package com.example.behappy.behappyapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends Activity {
    private WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Bundle b = getIntent().getExtras();
        String mURL = b.getString("mURL");
        Toast.makeText(WebActivity.this, "Abrindo link: " + mURL, Toast.LENGTH_SHORT).show();

        webview = (WebView) findViewById(R.id.webView);
        //webview.getSettings().setAppCacheEnabled(false);
        webview.getSettings().setBuiltInZoomControls(true);
        //webview.getSettings().setJavaScriptEnabled(true);

        //final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        //webview.setInitialScale(1);
        webview.loadUrl(mURL);

    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            webview.clearCache(true);
            webview.clearHistory();
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
