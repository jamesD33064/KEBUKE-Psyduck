package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private ListView lv_cart_OrderFoods;

    private Button btn_cart_shop;
    private Button btn_cart_ConfirmOrder;
    private DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        db = new DatabaseHandler(this);
        db.open();

        lv_cart_OrderFoods = findViewById(R.id.lv_cart_OrderFoods);
        showAllCartItem();

// ------------------------------- delete btn click listener
        AdapterView.OnItemClickListener listviewItemClickListener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String Name = ((TextView) view.findViewById(R.id.tv_foodname)).getText().toString();
//                Toast.makeText(CartActivity.this, Name, Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
                String PhoneNumber = sharedPref.getString("phoneNumber", "");
                db.delCartItem(PhoneNumber, Name);
                showAllCartItem();
            }
        };
        lv_cart_OrderFoods.setOnItemClickListener(listviewItemClickListener);

// ------------------------------- jump to other activity
        btn_cart_shop = findViewById(R.id.btn_cart_shop);
        btn_cart_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, FoodListActivity.class);
                startActivity(intent);
            }
        });

        btn_cart_ConfirmOrder = findViewById(R.id.btn_cart_ConfirmOrder);
        btn_cart_ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ConfirmOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAllCartItem() {
        SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
        String phoneNumber = sharedPref.getString("phoneNumber", "");
        Cursor cursor = db.getAllCartItem(phoneNumber);

        Map<String, Integer> quantityMap = new HashMap<>();

        while (cursor.moveToNext()) {
            String itemName = cursor.getString(cursor.getColumnIndex("item_name"));
            int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));

            if (quantityMap.containsKey(itemName)) {
                quantity += quantityMap.get(itemName);
            }
            quantityMap.put(itemName, quantity);
        }

        List<Map<String, String>> data = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : quantityMap.entrySet()) {
            String itemName = entry.getKey();
            String quantity = String.valueOf(entry.getValue());
            Map<String, String> itemData = new HashMap<>();
            itemData.put("item_name", itemName);
            itemData.put("quantity", "X "+quantity);

            int totalprice = 0;
            switch (itemName){
                case "高級蛋餅":
                    totalprice = Integer.parseInt(quantity)*10;
                    break;
                case "高級漢堡":
                    totalprice = Integer.parseInt(quantity)*11;
                    break;
                case "高級吐司":
                    totalprice = Integer.parseInt(quantity)*12;
                    break;
                case "高級奶茶":
                    totalprice = Integer.parseInt(quantity)*13;
                    break;
                case "咖啡":
                    totalprice = Integer.parseInt(quantity)*14;
                    break;
                case "炒麵":
                    totalprice = Integer.parseInt(quantity)*15;
                    break;
                case "紅茶":
                    totalprice = Integer.parseInt(quantity)*16;
                    break;
            }
            itemData.put("totalprice", String.valueOf(totalprice)+" $");


            data.add(itemData);
        }

        SimpleAdapter adapter = new SimpleAdapter(
          CartActivity.this,
          data,
          R.layout.cart_fooditem_layout,
          new String[]{"item_name","totalprice" , "quantity", "item_name"},
          new int[]{R.id.tv_foodname, R.id.tv_foodprice, R.id.tv_foodquantity, R.id.iv_foodimg}
        ) {
            @Override
            public void setViewImage(ImageView v, String value) {

                int img_id = R.drawable.ic_launcher_foreground;
                switch (value){
                    case "高級蛋餅":
                        img_id = R.drawable.eggcake;
                        break;
                    case "高級漢堡":
                        img_id = R.drawable.burger;
                        break;
                    case "高級吐司":
                        img_id = R.drawable.toast;
                        break;
                    case "高級奶茶":
                        img_id = R.drawable.milktea;
                        break;
                    case "咖啡":
                        img_id = R.drawable.coffee;
                        break;
                    case "炒麵":
                        img_id = R.drawable.noodle;
                        break;
                    case "紅茶":
                        img_id = R.drawable.blacktea;
                        break;
                }
                v.setImageResource(img_id);
            }
        };
        lv_cart_OrderFoods.setAdapter(adapter);
    }

}