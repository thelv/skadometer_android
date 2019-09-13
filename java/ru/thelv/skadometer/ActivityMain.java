package ru.thelv.skadometer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class ActivityMain extends AppCompatActivity
{
    public static AssetManager assets;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar=getActionBar();
        if(actionBar==null)
        {
            android.support.v7.app.ActionBar actionBar_=getSupportActionBar();
            if(actionBar_!=null)
            {
                actionBar_.hide();
            }
        }
        else
        {
            actionBar.hide();
        }

        assets=getAssets();

        setContentView(R.layout.activity_main);
    }
}
