package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {

    private TextView tv_FoodName;
    private TextView tv_FoodPrice;

    private TextView tv_item_quantity;
    private Button btn_sub;
    private Button btn_add;
    private Button btn_add2cart;
    private int item_quantity = 1;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        tv_FoodName = findViewById(R.id.product_detail_name);
        tv_FoodPrice = findViewById(R.id.product_detail_price);

        String FoodName = getIntent().getExtras().getString("FoodName");
        String FoodPrice = getIntent().getExtras().getString("FoodPrice");
        tv_FoodName.setText(FoodName);
        tv_FoodPrice.setText(FoodPrice);

        db = new DatabaseHandler(this);
        db.open();

        btn_sub = findViewById(R.id.product_detail_subbtn);
        btn_add = findViewById(R.id.product_detail_addbtn);
        btn_add2cart = findViewById(R.id.btn_cart_shop);
        tv_item_quantity = findViewById(R.id.product_detail_num);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.product_detail_subbtn:
                        item_quantity = (item_quantity>1) ? (item_quantity-=1) : 1;
                        break;
                    case R.id.product_detail_addbtn:
                        item_quantity+=1;
                        break;
                    case R.id.btn_cart_shop:
                        SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
                        String PhoneNumber = sharedPref.getString("phoneNumber", "");
                        db.addItem2Cart(PhoneNumber, FoodName, item_quantity, Integer.parseInt(FoodPrice) * item_quantity);
                        Toast.makeText(ProductActivity.this, "添加"+item_quantity+"個"+FoodName, Toast.LENGTH_SHORT).show();

                        item_quantity = 1;
                        break;
                }
                tv_item_quantity.setText(Integer.toString(item_quantity));
            }
        };
        btn_sub.setOnClickListener(listener);
        btn_add.setOnClickListener(listener);
        btn_add2cart.setOnClickListener(listener);
    }
}