package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAccept(View view) {
        Intent intent = new Intent(this, Result_Activity.class);
        intent.putExtra("button", "yes");
        startActivity(intent);
    }

    public void clickDeny(View view) {
        Intent intent = new Intent(this, Result_Activity.class);
        intent.putExtra("button", "no");
        startActivity(intent);
    }

}