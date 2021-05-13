package com.example.m7uf2_projecte2_grup_beta;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SubEsculturasAdapter extends RecyclerView.Adapter<SubEsculturasAdapter.SubItemViewHolder> {

    private List<String> subItemList;

    SubEsculturasAdapter(List<String> subItemList) {
        this.subItemList = subItemList;
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_sub_item, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder subItemViewHolder, int i) {

        subItemViewHolder.tvSubItemTitle.setText(subItemList.get(i));

        subItemViewHolder.tvSubItemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subItemViewHolder.tvSubItemTitle.setText("Has pulsado: "+subItemList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return subItemList.size();
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle;

        SubItemViewHolder(View itemView) {
            super(itemView);
            tvSubItemTitle = itemView.findViewById(R.id.tv_esculturas);
        }
    }
}
