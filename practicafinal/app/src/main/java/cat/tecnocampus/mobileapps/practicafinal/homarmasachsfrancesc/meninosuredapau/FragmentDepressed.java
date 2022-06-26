package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentDepressed extends Fragment{

    View rootView;
    ImageView imageView;
    FirebaseStorage firebaseStorage;
    StorageReference imgReference;
    String imagePath = "gs://practica-final-3d0c3.appspot.com/goku__ssgss__offers_a_fist_bump_by_l_dawg211_degcqan-pre.jpg";
    Adapter adapter;
    List<String> images = new ArrayList<String>();
    RecyclerView imageList;
    RequestQueue queue;
    String url;
    JSONArray photoUrl;

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_depressed, container, false);

        imageView = rootView.findViewById(R.id.imageNotDepressed);

        firebaseStorage = FirebaseStorage.getInstance();
        imgReference = firebaseStorage.getReferenceFromUrl(imagePath);

        imageList = rootView.findViewById(R.id.imageList);
        imageList.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle == null){
            url = "https://dog.ceo/api/breeds/image/random/10";
        }else if(bundle.getString("breed").equals("random")){
            url = "https://dog.ceo/api/breeds/image/random/10";
        }else{
            url = "https://dog.ceo/api/breed/"+bundle.getString("breed")+"/images/random/10";
        }

        queue = Volley.newRequestQueue(getContext());

        if (adapter==null){
            adapter = new Adapter(getContext(), images, (Adapter.OnNoteListener) getActivity());
            setData();
        }

        imageList.setAdapter(adapter);

        return rootView;
    }

    void setData() {
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject firstUser = response;
                            photoUrl = firstUser.getJSONArray("message");
                            for (int i = 0; i<photoUrl.length();i++){
                                images.add(photoUrl.get(i).toString());
                            }
                            adapter.notifyDataSetChanged();



                        } catch (Exception ex) {
                            Log.d("SwA", "Error parsing json array");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SwA", "Error in request ");
                    }
                }
        );
        queue.add(jsonArrayRequest);
    }
}