package com.example.impdates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.impdates.MyListData;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
   private List<MyListData> plist;

    // RecyclerView recyclerView;


    public MyListAdapter(List<MyListData> plist) {
        this.plist = plist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.content_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData myListData = plist.get(position);
        holder.textView1.setText(myListData.getName());
        holder.textView2.setText(myListData.getDob());
        holder.textView3.setText(myListData.getFrequency());
        holder.textView4.setText(myListData.getOccasion());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return plist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.textview1);
            this.textView2 = (TextView) itemView.findViewById(R.id.textview2);
            this.textView3 = (TextView) itemView.findViewById(R.id.textview3);
            this.textView4 = (TextView) itemView.findViewById(R.id.textview4);
            this.relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rl1);
        }
    }
}
