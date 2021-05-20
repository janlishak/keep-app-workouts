package com.janlishak.keepappworkouts.ui.exercise_creation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.janlishak.keepappworkouts.R;
import com.janlishak.keepappworkouts.services.api.ImgurRepository;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.janlishak.keepappworkouts.services.api.Link;
import com.janlishak.keepappworkouts.ui.main_activity.MainActivity;

import java.io.File;


import java.io.File;

import static android.app.Activity.RESULT_OK;

public class ExerciseCreationFragment extends Fragment {
    private View root;
    private ExerciseCreationViewModel exerciseCreationViewModel;
    private EditText name;
    private EditText description;
    private Button addImageButton;
    private ProgressBar progressBar;

    private static final int RESULT_LOAD_IMG = 123;
    private ImgurRepository repository;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        exerciseCreationViewModel = new ViewModelProvider(getActivity()).get(ExerciseCreationViewModel.class);
        root = inflater.inflate(R.layout.fragment_exercise_creation, container, false);
        setHasOptionsMenu(true);
        MainActivity.verifyStoragePermissions(getActivity());

        name = root.findViewById(R.id.exercise_name_edit_text);
        description = root.findViewById(R.id.exercise_description_edit_text);
        addImageButton = root.findViewById(R.id.add_image_button);
        progressBar = root.findViewById(R.id.image_upload_progress);
        progressBar.setVisibility(View.GONE);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });

        repository = ImgurRepository.getInstance();
        return root;
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        //super.onActivityResult(reqCode, resultCode, data);
        Log.i("Retrofit", "onActivityResult: ");
        if (resultCode == RESULT_OK) {
            Log.i("Retrofit", "result ok");
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String mediaPath = cursor.getString(columnIndex);

            Log.i("Retrofit", "mp: " + mediaPath );

            exerciseCreationViewModel.getFile(mediaPath).observe(getViewLifecycleOwner(), new Observer<Link>() {
                @Override
                public void onChanged(Link link) {
                    progressBar.setVisibility(link.isLoading()? View.VISIBLE : View.GONE);
                    if(!link.isLoading()) Toast.makeText(getActivity(), "image added", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.creation_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create:
                exerciseCreationViewModel.getName().setValue(name.getText().toString());
                exerciseCreationViewModel.getDescription().setValue(description.getText().toString());
                exerciseCreationViewModel.createExercise();
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(root.getWindowToken(), 0);
                Navigation.findNavController(root).popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
