package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormActivity extends AppCompatActivity implements AdapterBreed.OnNoteListener {

    String url = "https://dog.ceo/api/breeds/list/all";
    JSONObject breeds;
    List<String> breedList = new ArrayList<String>();
    RequestQueue queue;
    AdapterBreed adapterBreed;
    RecyclerView breedRV;
    Boolean formCorrect, existBreed;
    EditText typeOfDog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        queue = Volley.newRequestQueue(this);

        typeOfDog = findViewById(R.id.typeofdog);

        breedRV = findViewById(R.id.breedList);
        breedRV.setLayoutManager(new LinearLayoutManager(this));

        if (adapterBreed==null){
            adapterBreed = new AdapterBreed(this, breedList, this::onNoteClick);
            setData();
        }
        breedRV.setAdapter(adapterBreed);

        setData();
    }

    void setData() {
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject firstUser = response;
                            String key;
                            breeds = firstUser.getJSONObject("message");

                            for (Iterator<String> it = breeds.keys(); it.hasNext();){
                                key = it.next();
                                breedList.add(key);
                            }
                            System.out.println(breedList);
                            adapterBreed.notifyDataSetChanged();

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
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void saveChanges(View view) {
        formCorrect = true;
        existBreed = false;
        String enteredText = typeOfDog.getText().toString();
        if (enteredText.equals("")){
            typeOfDog.setError("Please enter a valid breed");
            formCorrect = false;
        }
        for (String breed: breedList){
            if (enteredText.equals(breed)){
                existBreed = true;
                break;
            }
        }

        if (formCorrect && existBreed){
            Intent intent = new Intent();
            intent.putExtra("breed", typeOfDog.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }else{
            typeOfDog.setError("Please enter a valid breed");
        }
    }

    @Override
    public void onNoteClick(String image) {
        typeOfDog.setText(image);
    }
}