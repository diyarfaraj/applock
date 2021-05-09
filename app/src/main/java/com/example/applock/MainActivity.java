package com.example.applock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.example.applock.adapters.AppListAdapter;
import com.example.applock.model.AppInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView app_list;
    FloatingActionButton ok_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app_list = findViewById(R.id.app_list);
        app_list.setLayoutManager(new LinearLayoutManager(this));
        ok_btn = findViewById(R.id.ok_btn);
        loadAppList();
    }

    public  void loadAppList(){
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> homeApps = packageManager.queryIntentActivities(intent,0);
        List<AppInfo> apps = new ArrayList<>();
        for (ResolveInfo info: homeApps){
            AppInfo appInfo = new AppInfo();
            appInfo.setAppLogo(info.activityInfo.loadIcon(packageManager));
            appInfo.setPackageName(info.activityInfo.packageName);
            appInfo.setAppName((String) info.activityInfo.loadLabel(packageManager));
            apps.add(appInfo);
        }

        AppListAdapter adapter = new AppListAdapter(apps);
        app_list.setAdapter(adapter);
    }
}