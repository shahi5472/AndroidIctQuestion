package com.s.m.shahi.androidictquestion.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.s.m.shahi.androidictquestion.Model.Model;
import com.s.m.shahi.androidictquestion.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter implements Filterable {

    private Context context;
    private int layout;
    private ArrayList<Model> noteArrayList, tempArrayList;
    private CustomFilter customFilter;

    public Adapter(Context context, int layout, ArrayList<Model> noteArrayList) {
        this.context = context;
        this.layout = layout;
        this.noteArrayList = noteArrayList;
        this.tempArrayList = noteArrayList;
    }

    @Override
    public int getCount() {
        return noteArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder {
        TextView textViewQuestion, textViewAnswer, textViewOPTION1, textViewOPTION2, textViewOPTION3, textViewOPTION4;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder viewHolder = new ViewHolder();

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            viewHolder.textViewQuestion = (TextView) view.findViewById(R.id.textViewQuestion);
            viewHolder.textViewAnswer = (TextView) view.findViewById(R.id.textViewAnswer);
            viewHolder.textViewOPTION1 = (TextView) view.findViewById(R.id.textViewOPTION1);
            viewHolder.textViewOPTION2 = (TextView) view.findViewById(R.id.textViewOPTION2);
            viewHolder.textViewOPTION3 = (TextView) view.findViewById(R.id.textViewOPTION3);
            viewHolder.textViewOPTION4 = (TextView) view.findViewById(R.id.textViewOPTION4);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Model model = noteArrayList.get(position);

        viewHolder.textViewQuestion.setText(model.getUndefined() + " Question : " + model.getQUESTION());
        viewHolder.textViewAnswer.setText("Answer : " + model.getCORRECT());

        viewHolder.textViewOPTION1.setText("Option A : " + model.getOPTION1());
        viewHolder.textViewOPTION2.setText("Option B : " + model.getOPTION2());
        viewHolder.textViewOPTION3.setText("Option C : " + model.getOPTION3());
        viewHolder.textViewOPTION4.setText("Option D : " + model.getOPTION4());

        return view;
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
            noteArrayList = (ArrayList<Model>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
