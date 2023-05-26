package fcu.app.kebukepsyduck;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import fcu.app.kebukepsyduck.databinding.ActivityFoodListBinding;

public class FoodListActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityFoodListBinding binding;

    private component_FoodItem item1;
    private component_FoodItem item2;
    private component_FoodItem item3;
    private component_FoodItem item4;
    private component_FoodItem item5;
    private component_FoodItem item6;
    private component_FoodItem item7;
//    private component_FoodItem[] foodItem_list = {item1,item2,item3,item4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFoodListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setSupportActionBar(binding.toolbar);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FoodListActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });



        item1 = findViewById(R.id.FoodItem_1);
        item2 = findViewById(R.id.FoodItem_2);
        item3 = findViewById(R.id.FoodItem_3);
        item4 = findViewById(R.id.FoodItem_4);
        item5 = findViewById(R.id.FoodItem_5);
        item6 = findViewById(R.id.FoodItem_6);
        item7 = findViewById(R.id.FoodItem_7);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                intent.setClass(FoodListActivity.this,ProductActivity.class);
                switch (v.getId()){
                    case R.id.FoodItem_1:
                        bundle.putString("FoodName","高級蛋餅");
                        bundle.putString("FoodPrice","10$");
                        bundle.putInt("FoodImg",R.drawable.eggcake);
                        break;
                    case R.id.FoodItem_2:
                        bundle.putString("FoodName","高級漢堡");
                        bundle.putString("FoodPrice","11$");
                        bundle.putInt("FoodImg",R.drawable.burger);
                        break;
                    case R.id.FoodItem_3:
                        bundle.putString("FoodName","高級吐司");
                        bundle.putString("FoodPrice","12$");
                        bundle.putInt("FoodImg",R.drawable.toast);
                        break;
                    case R.id.FoodItem_4:
                        bundle.putString("FoodName","高級奶茶");
                        bundle.putString("FoodPrice","13$");
                        bundle.putInt("FoodImg",R.drawable.milktea);
                        break;
                    case R.id.FoodItem_5:
                        bundle.putString("FoodName","咖啡");
                        bundle.putString("FoodPrice","14$");
                        bundle.putInt("FoodImg",R.drawable.coffee);
                        break;
                    case R.id.FoodItem_6:
                        bundle.putString("FoodName","炒麵");
                        bundle.putString("FoodPrice","15$");
                        bundle.putInt("FoodImg",R.drawable.noodle);
                        break;
                    case R.id.FoodItem_7:
                        bundle.putString("FoodName","紅茶");
                        bundle.putString("FoodPrice","16$");
                        bundle.putInt("FoodImg",R.drawable.blacktea);
                        break;
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };
        item1.setOnClickListener(listener);
        item2.setOnClickListener(listener);
        item3.setOnClickListener(listener);
        item4.setOnClickListener(listener);
        item5.setOnClickListener(listener);
        item6.setOnClickListener(listener);
        item7.setOnClickListener(listener);


    }

}