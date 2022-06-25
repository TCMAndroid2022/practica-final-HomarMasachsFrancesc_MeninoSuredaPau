package cat.tecnocampus.mobileapps.practicafinal.homarmasachsfrancesc.meninosuredapau;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FragmentNotDepressed extends Fragment {

    View rootView;
    ImageView imageView;
    FirebaseStorage firebaseStorage;
    StorageReference imgReference;
    String imagePath = "gs://practica-final-3d0c3.appspot.com/goku__ssgss__offers_a_fist_bump_by_l_dawg211_degcqan-pre.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_not_depressed, container, false);

        imageView = rootView.findViewById(R.id.imageNotDepressed);

        firebaseStorage = FirebaseStorage.getInstance();
        imgReference = firebaseStorage.getReferenceFromUrl(imagePath);

        Glide.with(getContext()).load(imgReference).into(imageView);

        return rootView;
    }
}