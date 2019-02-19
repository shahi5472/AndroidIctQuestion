package com.s.m.shahi.androidictquestion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewQuestion, textViewAnswer, textViewOPTION1, textViewOPTION2, textViewOPTION3, textViewOPTION4;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewQuestion = (TextView) itemView.findViewById(R.id.textViewQuestion);
        textViewAnswer = (TextView) itemView.findViewById(R.id.textViewAnswer);

        textViewOPTION1 = (TextView) itemView.findViewById(R.id.textViewOPTION1);
        textViewOPTION2 = (TextView) itemView.findViewById(R.id.textViewOPTION2);
        textViewOPTION3 = (TextView) itemView.findViewById(R.id.textViewOPTION3);
        textViewOPTION4 = (TextView) itemView.findViewById(R.id.textViewOPTION4);
    }
}