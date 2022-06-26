package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentDepressedPhoto extends Fragment{

    View rootView;
    ImageView imageView;
    String imagePath;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_depressed_photo, container, false);

        button = rootView.findViewById(R.id.back_button);

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.GONE);
        }

        Bundle bundle = getArguments();
        if (bundle==null){
            imagePath = "https://images.dog.ceo/breeds/greyhound-italian/n02091032_923.jpg";
        }else{
            imagePath = bundle.getString("image");

        }

        imageView = rootView.findViewById(R.id.imageBig);

        Glide.with(getContext()).load(imagePath).into(imageView);


        return rootView;
    }

    public void changePhoto(String image) {
        Glide.with(getContext()).load(image).into(imageView);
    }

}