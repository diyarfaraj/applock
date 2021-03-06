package com.example.applock.adapters;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.media.TimedText;
import android.service.controls.Control;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applock.R;
import com.example.applock.model.AppInfo;

import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {
    List<AppInfo> appList;
    Context context;
    public AppListAdapter(List<AppInfo> appInfos){
    this.appList = appInfos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.app_card, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.appLogo.setImageDrawable(appList.get(position).getAppLogo());
        holder.appName.setText(appList.get(position).getAppName());
        if(appList.get(position).isAppStatus()){
            holder.appStatus.setImageResource(R.drawable.ic_baseline_lock_24);
        } else {
            holder.appStatus.setImageResource(R.drawable.ic_baseline_lock_open_24);
        }

        holder.appStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(appList.get(position).isAppStatus()){
                    appList.get(position).setAppStatus(false);
                    holder.appStatus.setImageResource(R.drawable.ic_baseline_lock_open_24);
                } else {
                    appList.get(position).setAppStatus(true);
                    holder.appStatus.setImageResource(R.drawable.ic_baseline_lock_24);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView appLogo, appStatus;
        TextView appName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.app_name);
            appLogo = itemView.findViewById(R.id.app_logo);
            appStatus = itemView.findViewById(R.id.app_status);
        }
    }
}
