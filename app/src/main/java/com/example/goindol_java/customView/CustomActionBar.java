package com.example.goindol_java.customView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.goindol_java.R;

public class CustomActionBar {

    private Activity activity;
    private ActionBar actionBar;

    public CustomActionBar(Activity activity, ActionBar actionBar) {
        this.activity = activity;
        this.actionBar = actionBar;
    }

    public void setActionBar(){
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setElevation(0);
        View mCustomView = LayoutInflater.from(activity).inflate(R.layout.custom_actionbar,null);
        actionBar.setCustomView(mCustomView);
        Toolbar toolbar  = (Toolbar)mCustomView.getParent();
        toolbar.setContentInsetsAbsolute(0,0);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView,params);
    }
}
