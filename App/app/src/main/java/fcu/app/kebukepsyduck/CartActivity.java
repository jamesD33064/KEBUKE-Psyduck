package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView lv_cart_OrderFoods;

    private Button btn_cart_shop;
    private Button btn_cart_ConfirmOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lv_cart_OrderFoods = findViewById(R.id.lv_cart_OrderFoods);

        List<FoodItem> lsFoods = new ArrayList<FoodItem>();
        lsFoods.add(new FoodItem(R.drawable.logo, "漢堡", 99));
        lsFoods.add(new FoodItem(R.drawable.logo, "飯糰", 88));
        lsFoods.add(new FoodItem(R.drawable.logo, "奶茶", 77));;
        lsFoods.add(new FoodItem(R.drawable.logo, "蛋餅", 66));
        lsFoods.add(new FoodItem(R.drawable.logo, "法式吐司", 55));
        lsFoods.add(new FoodItem(R.drawable.logo, "法國吐司", 44));

        Cart_LV_FoodList_adapter adapter = new Cart_LV_FoodList_adapter(this, lsFoods);
        lv_cart_OrderFoods.setAdapter(adapter);


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
}