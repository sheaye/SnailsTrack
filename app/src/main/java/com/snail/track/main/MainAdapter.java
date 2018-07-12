package com.snail.track.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.snail.track.R;
import com.snail.track.data.MainEntity;
import com.snail.track.samples.canvas_operations.CanvasOperationActivity;
import com.snail.track.samples.draw_bitmap_text.DrawBitmapTextActivity;
import com.snail.track.samples.path.bezier.BezierActivity;
import com.snail.track.samples.path.PathTestActivity;
import com.snail.track.samples.pie_chart.PieChartActivity;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final Context mContext;
    private List<MainEntity> list;

    public MainAdapter(Context context, List<MainEntity> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_main_entry, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainEntity entity = list.get(position);
        if (entity != null) {
            holder.mButton.setText(entity.getName());
            holder.mButton.setOnClickListener(new OnClickListener(entity.getId()));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button mButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mButton = ((Button) itemView);
        }
    }

    class OnClickListener implements View.OnClickListener {

        private int id;

        public OnClickListener(int id) {
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            switch (id) {
                case 1:
                    break;
                case 2:
                    mContext.startActivity(new Intent(mContext, PieChartActivity.class));
                    break;
                case 3:
                    mContext.startActivity(new Intent(mContext, CanvasOperationActivity.class));
                    break;
                case 4:
                    mContext.startActivity(new Intent(mContext, DrawBitmapTextActivity.class));
                    break;
                case 5:
                    mContext.startActivity(new Intent(mContext, PathTestActivity.class));
                    break;
            }
        }
    }
}
