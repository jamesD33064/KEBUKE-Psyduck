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
            " phone TEXT NOT NULL, " +
            " item_name TEXT NOT NULL, " +
            " quantity INTEGER NOT NULL," +
            " price INTEGER NOT NULL," +
            " date Text)";

    private static final String CREATE_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS Orders (" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " phone TEXT NOT NULL, " +
            " cost INTEGER NOT NULL, " +
            " state TEXT NOT NULL, " +
            " address TEXT NOT NULL, " +
            " remark TEXT, " +
            " date TEXT NOT NULL)";

    public DatabaseHandler(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void open() {
        database = activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        database.execSQL(CREATE_MEMBER_TABLE);
        database.execSQL(CREATE_CART_TABLE);
        database.execSQL(CREATE_ORDER_TABLE);
    }

    private static final String DROP_ORDER_TABLE = "DROP TABLE IF EXISTS Orders";
    private static final String DROP_CART_TABLE = "DROP TABLE IF EXISTS Cart";

    public void deleteOrderTable() {
        database.execSQL(DROP_ORDER_TABLE);
    }

    public void deleteCartTable() {
        database.execSQL(DROP_CART_TABLE);
    }

    //    ----------------------- For User Account -----------------------

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
    public Cursor getAllCartItem(String phone) {
        Cursor cursor = database.rawQuery("SELECT * FROM Cart Where phone=" + phone + " AND date == \"\"", null);
//        Toast.makeText(activity, cursor.getCount()+"is added", Toast.LENGTH_SHORT).show();
        return cursor;
    }

    public void delCartItem(String phone, String itemName){
        String whereClause = "item_name = '" + itemName + "' AND phone = '" + phone + "' AND date == \"\"";
        database.delete("Cart", whereClause, null);
    }

    public Cursor getCartItemByPhoneAndDate(String phone, String date) {
        Cursor cursor = database.rawQuery("SELECT * FROM Cart WHERE phone='" + phone + "' AND date='" + date + "'", null);
        return cursor;
    }

    public void addItem2Cart(String phoneNumber, String item_name, int quantity, int price) {
        ContentValues values = new ContentValues();
        values.put("phone", phoneNumber);
        values.put("item_name", item_name);
        values.put("quantity", quantity);
        values.put("price", price);
        values.put("date", "");
        database.insert("Cart", null, values);
        Toast.makeText(activity, "添加"+quantity+"個"+item_name, Toast.LENGTH_SHORT).show();
    }

//    ----------------------- For Confirm Order -----------------------
    public void confirmCart(String phone, String date){
        ContentValues values = new ContentValues();
        String whereClause = "phone=? AND date == \"\"";
        values.put("date", date);
        database.update("Cart", values, whereClause, new String[]{phone});
//        return true;
    }

    //    ----------------------- For Order List -----------------------
    public boolean addOrder(String phoneNumber, Integer cost, String state, String address, String remark, String date) {
        ContentValues values = new ContentValues();
        values.put("phone", phoneNumber);
        values.put("state", state);
        values.put("date", date);
        values.put("address", address);
        values.put("remark", remark);
        values.put("cost", cost);
        database.insert("Orders", null, values);
        return true;
    }

    public void updateOrder(Integer id, String state) {
        ContentValues values = new ContentValues();
        values.put("state", state);
        database.update("Orders", values, "_id=?", new String[]{id.toString()});
    }

    public Cursor getAllOrderByPhone(String phone) {
        Cursor cursor = database.rawQuery("SELECT * FROM Orders WHERE phone=" + phone + " ORDER BY _id DESC", null);
        return cursor;
    }
}
