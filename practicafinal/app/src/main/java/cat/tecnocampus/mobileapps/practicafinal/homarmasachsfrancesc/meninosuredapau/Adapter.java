package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<String> images;
    OnNoteListener listener;

    public Adapter(Context context, List<String> images, OnNoteListener onNoteListener) {
        this.context = context;
        this.images = images;
        this.listener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_img, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dogName.setText(String.valueOf(position+1));
        Glide.with(context).load(images.get(position)).into(holder.image);
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete contact")
                        .setMessage("Are you sure you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                images.remove(holder.getBindingAdapterPosition());
                                notifyItemRemoved(holder.getBindingAdapterPosition());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView dogName;
        CardView cardView;
        ConstraintLayout mainLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageSmall);
            cardView = itemView.findViewById(R.id.cardView);
            dogName = itemView.findViewById(R.id.dogName);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onNoteClick(images.get(getBindingAdapterPosition()));
        }
    }

    public interface OnNoteListener{
        void onNoteClick(String image);
    }
}


