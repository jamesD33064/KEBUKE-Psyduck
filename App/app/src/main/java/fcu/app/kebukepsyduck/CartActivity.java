package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

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
        String PhoneNumber = sharedPref.getString("phoneNumber", "");
        Cursor cursor = db.getAllCartItem(PhoneNumber);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                CartActivity.this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] { "item_name", "quantity" },
//      new String[] { "meal_name", "img_name" },
                new int[] { android.R.id.text1, android.R.id.text2},
                0
        );
        lv_cart_OrderFoods.setAdapter(adapter);
    }
}