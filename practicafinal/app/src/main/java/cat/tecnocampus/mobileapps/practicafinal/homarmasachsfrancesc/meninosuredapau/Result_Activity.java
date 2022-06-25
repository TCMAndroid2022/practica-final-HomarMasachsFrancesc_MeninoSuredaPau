package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class Result_Activity extends AppCompatActivity implements Adapter.OnNoteListener {

    String buttonResult;
    final String YES = "yes";
    final String NO = "no";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if(getIntent().hasExtra("button")){
            buttonResult = getIntent().getStringExtra("button");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (YES.equals(buttonResult)){
            FragmentDepressed fragmentDepressed = new FragmentDepressed();
            fragmentTransaction.add(R.id.fragmentContainerView, fragmentDepressed);
            fragmentTransaction.commit();
        }else if(NO.equals(buttonResult)){
            FragmentNotDepressed fragmentNotDepressed = new FragmentNotDepressed();
            fragmentTransaction.add(R.id.fragmentContainerView, fragmentNotDepressed);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onNoteClick(String image) {

    }
}