package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderManageActivity extends AppCompatActivity {

    DatabaseHandler db;
    TextView tvDoingOrderCount;
    TextView tvFinishOrderCount;
    TextView tvOrderCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manage);

        db = new DatabaseHandler(this);
        db.open();

        SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
        String phoneNumber = sharedPref.getString("phoneNumber", "");

        Cursor orderList = db.getAllOrderByPhone(phoneNumber);
        generateOrderCards(orderList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Clear the existing order cards
        LinearLayout orderContainer = findViewById(R.id.linear_layout_orderList);
        orderContainer.removeAllViews();

        // Retrieve the updated order list and regenerate the order cards
        SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
        String phoneNumber = sharedPref.getString("phoneNumber", "");

        Cursor orderList = db.getAllOrderByPhone(phoneNumber);
        generateOrderCards(orderList);
    }

    private void generateOrderCards(Cursor orderList) {
        Integer totalCost = 0;
        Integer doingOrderCount = 0;
        Integer finishOrderCount = 0;
        LinearLayout orderContainer = findViewById(R.id.linear_layout_orderList);
        ArrayList<View> orderCardViews = new ArrayList<>();

        while (orderList.moveToNext()) {
            @SuppressLint("Range") String orderState = orderList.getString(orderList.getColumnIndex("state"));
            View orderCardView = null;
            if (orderState.equals("doing")) {
                doingOrderCount++;
                orderCardView = getLayoutInflater().inflate(R.layout.order_card_doing_layout, null);
            } else if (orderState.equals("finish")) {
                finishOrderCount++;
                orderCardView = getLayoutInflater().inflate(R.layout.order_card_finish_layout, null);
            }

            // Find the TextViews and ImageView inside the order card view
            TextView tvOrderTime = orderCardView.findViewById(R.id.tv_orderManage_time2);
            TextView tvOrderCost = orderCardView.findViewById(R.id.tv_orderManage_cost2);

            // Retrieve the order data from the cursor
            @SuppressLint("Range") String orderId = orderList.getString(orderList.getColumnIndex("_id"));
            @SuppressLint("Range") String orderTime = orderList.getString(orderList.getColumnIndex("date"));
            @SuppressLint("Range") String orderCost = orderList.getString(orderList.getColumnIndex("cost"));

            tvOrderTime.setText(orderTime);
            tvOrderCost.setText(orderCost);
            totalCost += Integer.parseInt(orderCost);

            Button btnOrderAction = orderCardView.findViewById(R.id.btn_orderManage_order);
            btnOrderAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderManageActivity.this, OrderDetailsActivity.class);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("orderDate", orderTime);
                    intent.putExtra("orderCost", orderCost);
                    intent.putExtra("orderState", orderState);
                    startActivity(intent);
                }
            });

            orderCardViews.add(orderCardView);
            orderContainer.addView(orderCardView);
        }

        tvDoingOrderCount = findViewById(R.id.tv_orderManage_doing);
        tvFinishOrderCount = findViewById(R.id.tv_orderManage_finish);
        tvOrderCost = findViewById(R.id.tv_orderManage_totalCost);
        tvDoingOrderCount.setText(doingOrderCount.toString());
        tvFinishOrderCount.setText(finishOrderCount.toString());
        tvOrderCost.setText(totalCost.toString());
        orderList.close();
    }

}