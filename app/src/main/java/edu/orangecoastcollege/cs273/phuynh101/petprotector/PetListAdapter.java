package edu.orangecoastcollege.cs273.phuynh101.petprotector;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HuynhHuu on 30-Oct-17.
 */

/**
 * Pet List Adapter
 */
public class PetListAdapter extends ArrayAdapter<Pet>{
    private Context mContext;
    private int mResource;
    private List<Pet> mPetList;

    /**
     * Constructor
     * @param context context
     * @param resource layout resource for each row
     * @param pets the list of pets
     */
    public PetListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Pet> pets) {
        super(context, resource, pets);
        mContext = context;
        mResource = resource;
        mPetList = pets;
    }

    /**
     * populate the layout for each row
     * @param position
     * @param convertView
     * @param parent
     * @return the layout for each row
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, null);

        ImageView onePetImageView = (ImageView) view.findViewById(R.id.onePetImageView);
        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView detailsTextView = (TextView) view.findViewById(R.id.detailsTextView);
        LinearLayout petListLinearLayout = (LinearLayout) view.findViewById(R.id.petListLinearLayout);

        Pet selectedPet = mPetList.get(position);
        onePetImageView.setImageURI(selectedPet.getImageUri());
        nameTextView.setText(selectedPet.getName());
        detailsTextView.setText(selectedPet.getDetails());

        petListLinearLayout.setTag(selectedPet);
        return view;
    }
}
