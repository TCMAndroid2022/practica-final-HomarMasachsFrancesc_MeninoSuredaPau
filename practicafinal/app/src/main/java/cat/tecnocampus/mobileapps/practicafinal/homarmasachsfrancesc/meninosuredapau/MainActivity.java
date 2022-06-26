package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String breed = "random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAccept(View view) {
        Intent intent = new Intent(this, Result_Activity.class);
        intent.putExtra("button", "yes");
        intent.putExtra("breed", breed);
        startActivity(intent);
    }

    public void clickDeny(View view) {
        Intent intent = new Intent(this, Result_Activity.class);
        intent.putExtra("button", "no");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void openOptions(MenuItem item) {
        Intent intent = new Intent(this, FormActivity.class);
        activityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        breed = data.getStringExtra("breed");
                        System.out.println(breed);
                    }
                }
            });
}