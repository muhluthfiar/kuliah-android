package com.example.sqlite_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;

    private static final String DATABASE_NAME = "MahasiswaDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Mahasiswa";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String NAMA_FIELD = "nama";
    private static final String JENISKELAMIN_FIELD = "jeniskelamin";
    private static final String JURUSAN_FIELD = "jurusan";
    private static final String ALAMAT_FIELD = "alamat";
    private static final String DELETED_FIELD = "deleted";

    private static final DateFormat dateFormat = new SimpleDateFormat("DD-mm-yyyy HH:mm:ss");

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static SQLiteManager instanceOfDatabase(Context context) {
        if (sqLiteManager == null) {
            sqLiteManager = new SQLiteManager(context);
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append(" (")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(NAMA_FIELD)
                .append(" TEXT, ")
                .append(JENISKELAMIN_FIELD)
                .append(" TEXT, ")
                .append(JURUSAN_FIELD)
                .append(" TEXT, ")
                .append(ALAMAT_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT ) ");

        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addDataToDatabase(Data data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, data.getId());
        contentValues.put(NAMA_FIELD, data.getNama());
        contentValues.put(JENISKELAMIN_FIELD, data.getJenisKelamin());
        contentValues.put(JURUSAN_FIELD, data.getJurusan());
        contentValues.put(ALAMAT_FIELD, data.getAlamat());
        contentValues.put(DELETED_FIELD, getStringFromDate(data.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME, null)) {

            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(1);
                    String nama = result.getString(2);
                    String jeniskelamin = result.getString(3);
                    String jurusan = result.getString(4);
                    String alamat = result.getString(5);
                    String stringDeleted = result.getString(6);
                    Date deleted = getDateFromString(stringDeleted);

                    Data data = new Data(id, nama, jeniskelamin, jurusan, alamat, deleted);
                    Data.dataArrayList.add(data);
                }
            }
        }
    }

    public void updateData(Data data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, data.getId());
        contentValues.put(NAMA_FIELD, data.getNama());
        contentValues.put(JENISKELAMIN_FIELD, data.getJenisKelamin());
        contentValues.put(JURUSAN_FIELD, data.getJurusan());
        contentValues.put(ALAMAT_FIELD, data.getAlamat());
        contentValues.put(DELETED_FIELD, getStringFromDate(data.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + "=?", new String[]{String.valueOf(data.getId())});

    }

    private String getStringFromDate(Date date) {
        if (date == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    private Date getDateFromString(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}
