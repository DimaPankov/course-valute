package more.muny.mainproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>{


    interface OnStateClickListener{
        void onStateClick(courseValute state, int position);
    }
    private final OnStateClickListener onClickListener;
    private final LayoutInflater inflater;
    private final List<courseValute> states;


    StateAdapter(Context context, ArrayList<courseValute> states, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recy2, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        courseValute state = states.get(holder.getAdapterPosition());
        holder.name.setText(state.getName());
        holder.course.setText(state.getCource());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(state, holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView course;
        ViewHolder(View view){
            super(view);
            name = (TextView)view.findViewById(R.id.Tv1);
            course = (TextView) view.findViewById(R.id.Tv2);
        }
    }
}
