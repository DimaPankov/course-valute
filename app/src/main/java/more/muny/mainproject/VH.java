package more.muny.mainproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class VH extends RecyclerView.ViewHolder {
   TextView tv1;
   TextView tv2;


   public VH(@NonNull View itemView) {
       super(itemView);
       tv1 = itemView.findViewById(R.id.Tv1);
       tv2 = itemView.findViewById(R.id.Tv2);

   }
}
