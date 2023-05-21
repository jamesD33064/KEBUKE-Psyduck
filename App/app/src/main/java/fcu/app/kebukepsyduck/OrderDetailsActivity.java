package fcu.app.kebukepsyduck;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailsActivity extends AppCompatActivity {

    String id;
    String date;
    String cost;
    String state;
    TextView tvOrderState;
    TextView tvOrderDate;
    TextView tvOrderCost;
    TextView tvFoodItemList;
    ImageView ivDuck;
    Button btnFinishOrder;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Intent intent = getIntent();
        id = intent.getStringExtra("orderId");
        date = intent.getStringExtra("orderDate");
        cost = intent.getStringExtra("orderCost");
        state = intent.getStringExtra("orderState");

        SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
        String phoneNumber = sharedPref.getString("phoneNumber", "");

        db = new DatabaseHandler(this);
        db.open();
        Cursor orderItem = db.getCartItemByPhoneAndDate(phoneNumber, date);

        tvOrderState = findViewById(R.id.tv_orderDetails_state);
        tvOrderDate = findViewById(R.id.tv_orderDetails_time);
        tvOrderCost = findViewById(R.id.tv_orderDetails_cost);
        ivDuck = findViewById(R.id.iv_duck);
        btnFinishOrder = findViewById(R.id.btn_orderDetail_finishOrder);
        if (state.equals("finish")) {
            tvOrderState.setText("已完成");
            ivDuck.setImageResource(R.drawable.dark_psyduck);
            btnFinishOrder.setVisibility(View.GONE);
        } else {
            ivDuck.setImageResource(R.drawable.light_psyduck);
            tvOrderState.setText("進行中");
        }
        tvOrderCost.setText(cost);
        tvOrderDate.setText(date);

        tvFoodItemList = findViewById(R.id.tv_orderDetail_foodItems);
        String foodItemList = "";
        while (orderItem.moveToNext()) {
            @SuppressLint("Range") String foodName = orderItem.getString(orderItem.getColumnIndex("item_name"));
            @SuppressLint("Range") Integer foodCount = orderItem.getInt(orderItem.getColumnIndex("quantity"));
            @SuppressLint("Range") Integer foodPrice = orderItem.getInt(orderItem.getColumnIndex("price"));
            foodItemList += foodName + " x " + foodCount + " : " + foodPrice + "\n";
        }
        tvFoodItemList.setText(foodItemList);

        btnFinishOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateOrder(Integer.parseInt(id), "finish");
                Intent intent = new Intent(OrderDetailsActivity.this, OrderDetailsActivity.class);
                intent.putExtra("orderId", id);
                intent.putExtra("orderDate", date);
                intent.putExtra("orderCost", cost);
                intent.putExtra("orderState", "finish");
                startActivity(intent);
                finish();
            }
        });
    }
}
