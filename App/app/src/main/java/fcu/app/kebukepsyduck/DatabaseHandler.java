package fcu.app.kebukepsyduck;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseHandler {
    private AppCompatActivity activity;

    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "kebukePsyduck.db";

    private static final String CREATE_MEMBER_TABLE = "CREATE TABLE IF NOT EXISTS Members (" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " phone TEXT NOT NULL UNIQUE, " +
            " password TEXT NOT NULL)";

    private static final String CREATE_CART_TABLE = "CREATE TABLE IF NOT EXISTS Cart (" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " phone TEXT NOT NULL UNIQUE, " +
            " item_name TEXT NOT NULL, " +
            " quantity INTEGER NOT NULL)";

    public DatabaseHandler(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void open() {
        database = activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        database.execSQL(CREATE_MEMBER_TABLE);
        database.execSQL(CREATE_CART_TABLE);
    }

    public Boolean addMember(String phone, String password) {
        Cursor cursor = database.rawQuery("SELECT * FROM Members WHERE phone=?", new String[]{phone});
        if (cursor.moveToFirst()) {
            // A record with the same phone number already exists
            Toast.makeText(activity, "此手機號碼已被註冊", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            // No record with the same phone number exists, insert the new member
            ContentValues values = new ContentValues();
            values.put("phone", phone);
            values.put("password", password);
            database.insert("Members", null, values);
            Toast.makeText(activity, "註冊成功", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        return true;
    }

    public boolean updateMemberPhone(String oldPhone, String newPhone) {
        Cursor cursor = database.rawQuery("SELECT * FROM Members WHERE phone=?", new String[]{newPhone});
        if (cursor.moveToFirst()) {
            // A record with the same phone number already exists
            Toast.makeText(activity, "此手機號碼已被註冊", Toast.LENGTH_SHORT).show();
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("phone", newPhone);
        database.update("Members", values, "phone=?", new String[]{oldPhone});
        Toast.makeText(activity, "手機號碼已更新", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean updateMemberPassword(String phone, String oldPassword, String newPassword) {
        if (!getMemberPassword(phone).equals(oldPassword)) {
            Toast.makeText(activity, "原密碼錯誤", Toast.LENGTH_SHORT).show();
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("password", newPassword);
        int rowsAffected = database.update("Members", values, "phone=?", new String[]{phone});
        Toast.makeText(activity, "密碼已更新", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Cursor getAllMember() {
        Cursor cursor = database.rawQuery("SELECT * FROM Members", null);
        return cursor;
    }

    public String getMemberPassword(String phone) {
        Cursor cursor = database.query("Members", new String[]{"password"}, "phone=?", new String[]{phone}, null, null, null);
        if (cursor.moveToFirst()) {
            String password = cursor.getString(0);
            cursor.close();
            return password;
        } else {
            cursor.close();
            return null;
        }
    }

//    ----------------------- For CART -----------------------
    public void addItem2Cart(String phoneNumber, String item_name, int quantity){

    }


}
