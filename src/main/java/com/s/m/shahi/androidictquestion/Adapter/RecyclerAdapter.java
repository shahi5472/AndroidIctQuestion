package com.s.m.shahi.androidictquestion.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.s.m.shahi.androidictquestion.DatabaseHelper;
import com.s.m.shahi.androidictquestion.Model.Model;
import com.s.m.shahi.androidictquestion.R;
import com.s.m.shahi.androidictquestion.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> implements Filterable {

    private CustomFilter customFilter;
    private List<Model> listData, tempArrayList;
    private Context context;
    private DatabaseHelper databaseHelper;

    public RecyclerAdapter(List<Model> listData, Context context) {
        this.listData = listData;
        this.context = context;
        this.tempArrayList = listData;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textViewQuestion.setText(listData.get(i).getUndefined() + " Question : " + listData.get(i).getQUESTION());
        viewHolder.textViewAnswer.setText("Answer : " + listData.get(i).getCORRECT());

        viewHolder.textViewOPTION1.setText("Option A : " + listData.get(i).getOPTION1());
        viewHolder.textViewOPTION2.setText("Option B : " + listData.get(i).getOPTION2());
        viewHolder.textViewOPTION3.setText("Option C : " + listData.get(i).getOPTION3());
        viewHolder.textViewOPTION4.setText("Option D : " + listData.get(i).getOPTION4());

        boolean check = databaseHelper.check(listData.get(i).getQUESTION());
        if (check) {

        } else {
            databaseHelper.saveData(listData.get(i).getQUESTION(),
                    listData.get(i).getCORRECT(), listData.get(i).getOPTION1(),
                    listData.get(i).getOPTION2(), listData.get(i).getOPTION3(),
                    listData.get(i).getOPTION4(), listData.get(i).getUndefined());
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public Filter getFilter() {
        if (customFilter == null) {
            customFilter = new CustomFilter();
        }

        return customFilter;
    }

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();

            if (charSequence != null) {
                charSequence = charSequence.toString().toUpperCase();

                ArrayList<Model> noteArrayList = new ArrayList<>();

                for (int i = 0; i < tempArrayList.size(); i++) {
                    if (tempArrayList.get(i).getQUESTION().toUpperCase().contains(charSequence)) {
                        Model note = new Model(tempArrayList.get(i).getUndefined(),
                                tempArrayList.get(i).getQUESTION(),
                                tempArrayList.get(i).getDIFF(),
                                tempArrayList.get(i).getCORRECT(),
                                tempArrayList.get(i).getOPTION1(),
                                tempArrayList.get(i).getOPTION2(),
                                tempArrayList.get(i).getOPTION3(),
                                tempArrayList.get(i).getOPTION4());
                        noteArrayList.add(note);
                    }
                }


                filterResults.count = noteArrayList.size();
                filterResults.values = noteArrayList;

            } else {
                filterResults.count = tempArrayList.size();
                filterResults.values = tempArrayList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listData = (ArrayList<Model>) filterResults.values;
            notifyDataSetChanged();
        }
    }

}
