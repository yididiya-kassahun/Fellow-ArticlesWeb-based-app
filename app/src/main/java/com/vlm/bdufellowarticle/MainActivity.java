package com.vlm.bdufellowarticle;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar webProgressBar;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(this);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        webView = findViewById(R.id.webId);
        webProgressBar = findViewById(R.id.myProgressBar);

        webProgressBar.setMax(100);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {


            webView.setWebViewClient(new WebViewClient());

            webView.loadUrl("https://bdufellowshipvlm.blogspot.com/");

            WebSettings webSettings = webView.getSettings();
            //   webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            webSettings.setJavaScriptEnabled(true);

            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    webProgressBar.setProgress(newProgress);
                }
            });

        } else {
            dialog.setContentView(R.layout.error_dialog);
            dialog.setTitle("Oops Connection Error :(");
            Button refreshBtn = dialog.findViewById(R.id.refresh);
            Button exitBtn = dialog.findViewById(R.id.exit);

            refreshBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConnectivityManager cm =
                            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo netInfo = cm.getActiveNetworkInfo();

                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        webView.setWebViewClient(new WebViewClient());
                        webView.setWebChromeClient(new WebChromeClient());
                        webView.loadUrl("https://bdufellowshipvlm.blogspot.com/");
                        WebSettings webSettings = webView.getSettings();
                        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                        webSettings.setJavaScriptEnabled(true);
                        webProgressBar.setMax(100);
                        webView.setWebChromeClient(new WebChromeClient() {
                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                super.onProgressChanged(view, newProgress);
                                webProgressBar.setProgress(newProgress);
                            }
                        });

                        dialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                        dialog.show();
                    }
                }
            });
            exitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    System.exit(0);
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_id:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String share_body = "Join Our Telegram Channel https://t.me/bdu_fellowship Stay Blessed!";
                intent.putExtra(Intent.EXTRA_SUBJECT, share_body);
                startActivity(Intent.createChooser(intent, "share type"));
                return true;
            case R.id.about_id:
                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent1);
                return true;
            case R.id.refresh_id:
                webView = findViewById(R.id.webId);
                webView.setWebViewClient(new WebViewClient());
                webView.setWebChromeClient(new WebChromeClient());
                webView.loadUrl("https://bdufellowshipvlm.blogspot.com/");
                WebSettings webSettings = webView.getSettings();
                webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                webSettings.setJavaScriptEnabled(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}
