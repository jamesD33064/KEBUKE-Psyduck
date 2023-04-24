package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView lv_cart_OrderFoods;

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

    }
}