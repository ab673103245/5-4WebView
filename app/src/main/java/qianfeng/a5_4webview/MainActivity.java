package qianfeng.a5_4webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        // 得到webView里面的设置管理对象
        WebSettings ws = webView.getSettings();
        // 允许javaScript执行
        ws.setJavaScriptEnabled(true);

        webView.loadUrl("http://www.baidu.com");

        // 加载本地网页
//        webView.loadUrl("file:///android_asset/jm/index.html");

        // 我希望我的网页在当前的我的应用中打开，而不是调用系统的浏览器来打开网页
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);  // 我希望我的网页在当前的我的应用中打开，而不是调用系统的浏览器来打开网页
                return true; // true表示事件处理完
            }
            //开始加载网页时回调，如果是在安卓中显示进度条的话，建议让进度条在javaScript代码中显示出来，而不是在java代码显示，java代码难弄，因为不知道要加载多少
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });


        // 获取网页标题
        webView.setWebChromeClient(new WebChromeClient(){
            // 获取网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.d("google-my:", "onReceivedTitle: " + title);
                Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        //如果网页可以后退，则网页后退
        if (webView.canGoBack()) {// 调用JS里的后退代码
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
