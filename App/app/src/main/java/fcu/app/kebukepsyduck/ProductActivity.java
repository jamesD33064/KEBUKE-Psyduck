package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    private TextView FoodName;
    private TextView FoodPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        FoodName = findViewById(R.id.product_detail_name);
        FoodPrice = findViewById(R.id.product_detail_price);

        FoodName.setText(getIntent().getExtras().getString("FoodName"));
        FoodPrice.setText(getIntent().getExtras().getString("FoodPrice"));


    }
}