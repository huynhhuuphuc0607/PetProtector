package edu.orangecoastcollege.cs273.phuynh101.petprotector;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Pet list activity
 */
public class PetListActivity extends AppCompatActivity {

    private List<Pet> petsList;
    private DBHelper db;
    private PetListAdapter mPetListAdapter;
    private ListView petsListView;

    private EditText nameEditText;
    private EditText detailsEditText;
    private EditText phoneEditText;
    private ImageView petImageView;

    private Uri imageUri;

    /**
     * establishes connections from Model to Controller
     * and from View to Controller
     * @param savedInstanceState settings from the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        petsListView = (ListView) findViewById(R.id.petsListView);
        petImageView = (ImageView) findViewById(R.id.petImageView);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        detailsEditText = (EditText) findViewById(R.id.detailsEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        imageUri = getUriFromResource(this,R.drawable.none);
        petImageView.setImageURI(imageUri);
        db = new DBHelper(this);

        petsList = db.getAllPets();
        mPetListAdapter = new PetListAdapter(this, R.layout.pet_list_item, petsList);
        petsListView.setAdapter(mPetListAdapter);
    }

    /**
     * view the details of the selected pet
     * @param view the view that triggers the event
     */
    public void viewPetDetails(View view)
    {
        if(view instanceof LinearLayout)
        {
            LinearLayout layout = (LinearLayout) view;
            Pet selectedPet = (Pet)layout.getTag();

            Intent detailsIntent = new Intent(this, PetDetailsActivity.class);
            detailsIntent.putExtra("Name", selectedPet.getName());
            detailsIntent.putExtra("Details", selectedPet.getDetails());
            detailsIntent.putExtra("Phone", selectedPet.getPhone());
            detailsIntent.putExtra("ImageUri", selectedPet.getImageUri().toString());
            startActivity(detailsIntent);
        }
    }

    /**
     * a helper function that generates URI from a resource with a project
     * @param context the current context
     * @param resID the resource identifier of the drawable
     * @return uri generated from the drawbable
     * @throws Resources.NotFoundException if the given resource id doesn't exist
     */
    public static Uri getUriFromResource(Context context, int resID) throws Resources.NotFoundException
    {
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
            "://" + res.getResourcePackageName(resID) +
            "/" + res.getResourceTypeName(resID) +
            "/" + res.getResourceEntryName(resID));
    }

    /**
     * add a new pet to the database
     * @param view the view that triggers the event
     */
    public void addPet(View view)
    {
        if(nameEditText.getText().toString().isEmpty() ||
                detailsEditText.getText().toString().isEmpty() ||
                phoneEditText.getText().toString().isEmpty())
            Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
        else
        {
            Pet newPet = new Pet(nameEditText.getText().toString(),
                    detailsEditText.getText().toString(),
                    phoneEditText.getText().toString(),
                    imageUri);

            db.addPet(newPet);
            petsList.add(newPet);
            mPetListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * fires an intent to the gallery app so users can choose an image
     * @param view the view that triggers the event
     */
    public void selectPetImage(View view)
    {
        List<String> permsList = new ArrayList<>();
        int readStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(readStoragePerm == PackageManager.PERMISSION_DENIED)
            permsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        if(permsList.size()>0)
        {
            String[] permsArray = new String[permsList.size()];
            permsList.toArray(permsArray);

            ActivityCompat.requestPermissions(this, permsArray, 999);
        }

        if(readStoragePerm == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 67);
        }
    }

    /**
     * catching the data sent back from another place
     * @param requestCode request code of the intent
     * @param resultCode result code of the intent
     * @param data the data that is attached to the intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 67 && resultCode == RESULT_OK && data!=null)
        {
            imageUri = data.getData();
            petImageView.setImageURI(imageUri);
        }
    }
}
