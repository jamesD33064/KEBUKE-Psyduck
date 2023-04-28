package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderManageActivity extends AppCompatActivity {

    Button order1Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manage);

        order1Btn = findViewById(R.id.btn_orderManage_order1);
        order1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderManageActivity.this, OrderDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}