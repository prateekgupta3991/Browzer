package com.application.news.newsstand;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by prtkgpt on 15/4/16.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {

    WebView bro;
    EditText ur;
    Button bgo,bref,bbk,bfd,bhy;

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch(view.getId()){
            case R.id.bgo:
                String str=ur.getText().toString();
                bro.loadUrl(str);
                //hiding the keyboard from screen after using ET at site load
                InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ur.getWindowToken(), 0);
                if(str.startsWith("http://")) {
                    bro.loadUrl(str);
                } else if(str.startsWith("1") || str.startsWith("www")) {
                    bro.loadUrl("http://"+str);
                } else {
                    bro.loadUrl("http://www."+str);
                }
                break;
            case R.id.brefresh:
                bro.reload();
                break;
            case R.id.bgback:
                if(bro.canGoBack()){
                    bro.goBack();
                    ur.setText(bro.getUrl());
                }
                break;
            case R.id.bgforw:
                if(bro.canGoForward()) {
                    bro.goForward();
                    ur.setText(bro.getUrl());
                }
                break;
            case R.id.bclearhis:
                bro.clearHistory();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_browser);

        bro=(WebView) findViewById(R.id.webView);

		/*
		 * setting some settings of the obtained URL like javascript,
		 * site zoom ratio ,site view as desktop site
		 */
        bro.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        bro.getSettings().setLoadWithOverviewMode(true);
        bro.getSettings().setUseWideViewPort(true);

		/*
		 * ViewClient helps to customise the acquired URL of the clicked
		 * item in the currently opened site to be open in the WebView
		 * rather than in android's default browser
		 */
        bro.setWebViewClient(new ViewClient());
        try{
            bro.loadUrl("http://www.facebook.com");
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        ur=(EditText) findViewById(R.id.eturl);
        bgo=(Button) findViewById(R.id.bgo);
        bref=(Button) findViewById(R.id.brefresh);
        bbk=(Button) findViewById(R.id.bgback);
        bfd=(Button) findViewById(R.id.bgforw);
        bhy=(Button) findViewById(R.id.bclearhis);
        bgo.setOnClickListener(this);
        bref.setOnClickListener(this);
        bbk.setOnClickListener(this);
        bfd.setOnClickListener(this);
        bhy.setOnClickListener(this);
        }
}
