package edu.orangecoastcollege.cs273.phuynh101.petprotector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuynh101 on 10/26/2017.
 */

class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "PetProtector";
    private static final String DATABASE_TABLE = "Pets";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DETAILS = "details";
    private static final String FIELD_PHONE = "phone ";
    private static final String FIELD_IMAGE_URI = "image_uri";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_NAME + " TEXT, "
                + FIELD_DETAILS + " TEXT, "
                + FIELD_PHONE + " TEXT, "
                + FIELD_IMAGE_URI + " TEXT"
                + ")";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addPet(Pet mPet)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, mPet.getName());
        values.put(FIELD_DETAILS, mPet.getDetails());
        values.put(FIELD_PHONE, mPet.getPhone());
        values.put(FIELD_IMAGE_URI, mPet.getImageUri().toString());

        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    public List<Pet> getAllPets()
    {
        SQLiteDatabase db = getReadableDatabase();
        List<Pet> mPetsList = new ArrayList<>();

        Cursor mCursor = db.query(DATABASE_TABLE, new String[]{KEY_FIELD_ID, FIELD_NAME, FIELD_DETAILS,
                FIELD_PHONE, FIELD_IMAGE_URI},
                null, null, null, null, null);

        if(mCursor.moveToFirst())
        {
            do{
                Pet mPet = new Pet(mCursor.getInt(0), mCursor.getString(1), mCursor.getString(2),
                        mCursor.getString(3), Uri.parse(mCursor.getString(4)));
                mPetsList.add(mPet);
            }while(mCursor.moveToNext());
        }

        db.close();
        mCursor.close();

        return mPetsList;
    }
}
