package com.example.admin.av;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 2/2/2017.
 */

public class VersionData extends RecyclerView.Adapter<VersionData.ViewHolder> {
    Context c;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> apis = new ArrayList<>();
    Integer[] ar = {R.mipmap.ic_launcher,R.drawable.beta,R.drawable.cupcake,R.drawable.donald,R.drawable.donald,R.drawable.eclair,R.drawable.froyo,R.drawable.gingerbread,R.drawable.honeycomb,R.drawable.icecream_sandwich,R.drawable.jelly_bean,R.drawable.kitkat,R.drawable.lollypop,R.drawable.marshmallow,R.drawable.nougat};

    public VersionData(Context c, ArrayList<String> names, ArrayList<String> apis) {
        this.c = c;
        this.names = names;
        this.apis = apis;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_versions, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvVersionname.setText(names.get(position));
        holder.tvVersionapi.setText(apis.get(position));
        holder.ivicon.setImageResource(ar[position]);
    }

    @Override
    public int getItemCount() {

        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVersionname;
        TextView tvVersionapi;
        ImageView ivicon;

        public ViewHolder(View itemView) {
            super(itemView);

            tvVersionname = (TextView) itemView.findViewById(R.id.tvVersionname);
            tvVersionapi = (TextView) itemView.findViewById(R.id.tvVersionapi);
            ivicon = (ImageView) itemView.findViewById(R.id.ivicon);

        }
    }
}
