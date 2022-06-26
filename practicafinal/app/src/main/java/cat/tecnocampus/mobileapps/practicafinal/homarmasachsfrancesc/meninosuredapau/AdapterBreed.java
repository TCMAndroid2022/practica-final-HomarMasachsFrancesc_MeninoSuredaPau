package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterBreed extends RecyclerView.Adapter<AdapterBreed.ViewHolder> {

    Context context;
    List<String> breeds;
    Adapter.OnNoteListener listener;

    public AdapterBreed(Context context, List<String> images, Adapter.OnNoteListener onNoteListener) {
        this.context = context;
        this.breeds = images;
        this.listener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_breed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dogName.setText(breeds.get(position));
    }

    @Override
    public int getItemCount() {
        return breeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dogName;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            dogName = itemView.findViewById(R.id.dogName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onNoteClick(breeds.get(getBindingAdapterPosition()));
        }
    }
    public interface OnNoteListener{
        void onNoteClick(String image);
    }
}


