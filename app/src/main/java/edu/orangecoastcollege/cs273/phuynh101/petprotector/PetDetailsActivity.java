package edu.orangecoastcollege.cs273.phuynh101.petprotector;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Pet Details Activity
 */
public class PetDetailsActivity extends AppCompatActivity {

    private ImageView petDetailsImageImageView;
    private TextView petDetailsNameTextView;
    private TextView petDetailsDetailsTextView;
    private TextView petDetailsPhoneTextView;

    /**
     * establishes connections from Model to Controller
     * and from View to Controller
     * @param savedInstanceState settings from the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        petDetailsImageImageView = (ImageView) findViewById(R.id.petDetailsImageImageView);
        petDetailsNameTextView = (TextView) findViewById(R.id.petDetailsNameTextView);
        petDetailsDetailsTextView = (TextView) findViewById(R.id.petDetailsDetailsTextView);
        petDetailsPhoneTextView = (TextView) findViewById(R.id.petDetailsPhoneTextView);

        Intent intent = getIntent();
        petDetailsImageImageView.setImageURI(Uri.parse(intent.getStringExtra("ImageUri")));
        petDetailsNameTextView.setText(intent.getStringExtra("Name"));
        petDetailsDetailsTextView.setText(intent.getStringExtra("Details"));
        petDetailsPhoneTextView.setText(intent.getStringExtra("Phone"));
    }
}
