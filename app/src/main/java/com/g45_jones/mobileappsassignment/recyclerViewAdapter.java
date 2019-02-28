package com.g45_jones.mobileappsassignment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

//Creation of recycler view was adapted from the following video tutorial
//because the android documentation is not very helpful on recycler views.
//URL: https://www.youtube.com/watch?v=Vyqz_-sJGFk

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {


    private ArrayList<String> companyTitles = new ArrayList<>();
    private ArrayList<String> companyNumbers = new ArrayList<>();
    private ArrayList<String> comapnyItems = new ArrayList<>();
    private Context context;

    public recyclerViewAdapter(ArrayList<String> companyNumbers,ArrayList<String> companyTitles,
                               ArrayList<String>cItems,Context context) {
        this.companyTitles = companyTitles;
        this.companyNumbers = companyNumbers;
        this.comapnyItems = cItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Log.d("Hello", "onBindViewHolder: called");
        viewHolder.title.setText(companyTitles.get(i));

        viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Hello", "onClick: clicked on" + companyTitles.get(viewHolder.getAdapterPosition()));

                String title = companyTitles.get(viewHolder.getAdapterPosition());
                String companyNumber = companyNumbers.get(viewHolder.getAdapterPosition());
                String cItems = comapnyItems.get(viewHolder.getAdapterPosition());
                Log.d("Hello", "items for clicked=" + comapnyItems.get(viewHolder.getAdapterPosition()));
                //Get the related data for the title selected, then start the intent for the node diagram.
                if(context instanceof searchAndResults){
                    ((searchAndResults)context).getRelatedData(title ,companyNumber, cItems);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return companyTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.companyTitle);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
