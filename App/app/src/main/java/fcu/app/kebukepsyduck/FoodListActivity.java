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
//    private component_FoodItem[] foodItem_list = {item1,item2,item3,item4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFoodListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
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
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                intent.setClass(FoodListActivity.this,ProductActivity.class);
                switch (v.getId()){
                    case R.id.FoodItem_1:
                        bundle.putString("FoodName","高級蛋餅");
                        bundle.putString("FoodPrice","120$");
                        break;
                    case R.id.FoodItem_2:
                        bundle.putString("FoodName","漢堡");
                        bundle.putString("FoodPrice","130$");
                        break;
                    case R.id.FoodItem_3:
                        bundle.putString("FoodName","吐司");
                        bundle.putString("FoodPrice","140$");
                        break;
                    case R.id.FoodItem_4:
                        bundle.putString("FoodName","奶茶");
                        bundle.putString("FoodPrice","150$");
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


    }

}