package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

public class Result_Activity extends AppCompatActivity implements Adapter.OnNoteListener {

    String buttonResult;
    final String YES = "yes";
    final String NO = "no";
    FragmentDepressedPhoto fragmentDepressedPhoto;
    FragmentDepressed fragmentDepressed;
    FragmentNotDepressed fragmentNotDepressed;
    String breed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if(getIntent().hasExtra("button")){
            buttonResult = getIntent().getStringExtra("button");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int orientation = getResources().getConfiguration().orientation;
        if (YES.equals(buttonResult)){
            breed = getIntent().getStringExtra("breed");
            Bundle bundle = new Bundle();
            bundle.putString("breed", breed);
            if(orientation == Configuration.ORIENTATION_PORTRAIT){
                fragmentDepressed = new FragmentDepressed();
                fragmentDepressed.setArguments(bundle);
                fragmentTransaction.add(R.id.fragmentContainerView, fragmentDepressed);
                fragmentTransaction.commit();
            }else{
                fragmentDepressed = new FragmentDepressed();
                fragmentDepressed.setArguments(bundle);
                fragmentTransaction.add(R.id.fragmentList, fragmentDepressed);
                fragmentTransaction.commit();

                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                fragmentDepressedPhoto = new FragmentDepressedPhoto();
                fragmentTransaction2.replace(R.id.fragmentPhoto, fragmentDepressedPhoto);
                fragmentTransaction2.commit();
            }

        }else if(NO.equals(buttonResult)){
            if(orientation == Configuration.ORIENTATION_PORTRAIT){
                fragmentNotDepressed = new FragmentNotDepressed();
                fragmentTransaction.add(R.id.fragmentContainerView, fragmentNotDepressed);
                fragmentTransaction.commit();
            }else{
                fragmentNotDepressed = new FragmentNotDepressed();
                fragmentTransaction.add(R.id.fragmentList, fragmentNotDepressed);
                fragmentTransaction.commit();

                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                FragmentNotDepressed fragmentNotDepressed2 = new FragmentNotDepressed();
                fragmentTransaction2.add(R.id.fragmentPhoto, fragmentNotDepressed2);
                fragmentTransaction2.commit();
            }

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onNoteClick(String image) {
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentDepressedPhoto = new FragmentDepressedPhoto();
            Bundle bundle = new Bundle();
            bundle.putString("image", image);
            fragmentDepressedPhoto.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragmentContainerView, fragmentDepressedPhoto);
            fragmentTransaction.commit();
        }else{
            fragmentDepressedPhoto.changePhoto(image);
        }
    }

    public void changeFragments(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragmentDepressed);
        fragmentTransaction.commit();

    }
}