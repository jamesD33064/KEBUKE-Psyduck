package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PersonalMainPageActivity extends AppCompatActivity {

    Button startOrderBtn;
    Button shoppingCartBtn;
    Button orderManageBtn;
    Button revisePhoneNumberBtn;
    Button revisePasswordBtn;
    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_main_page);

        startOrderBtn = findViewById(R.id.btn_personalPage_start_order);
        shoppingCartBtn = findViewById(R.id.btn_personalPage_shopping_cart);
        orderManageBtn = findViewById(R.id.btn_personalPage_order_manage);
        revisePhoneNumberBtn = findViewById(R.id.btn_personalPage_revise_phone);
        revisePasswordBtn = findViewById(R.id.btn_personalPage_revise_password);
        logoutBtn = findViewById(R.id.btn_personalPage_logout);

        startOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalMainPageActivity.this, FoodListActivity.class);
                startActivity(intent);
            }
        });

        shoppingCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalMainPageActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        orderManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalMainPageActivity.this, OrderManageActivity.class);
                startActivity(intent);
            }
        });

        revisePhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalMainPageActivity.this, RevisePhoneNumberActivity.class);
                startActivity(intent);
            }
        });

        revisePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch RevisePasswordActivity
                Intent intent = new Intent(PersonalMainPageActivity.this, RevisePasswordActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.putString("phoneNumber", "");
                editor.apply();
                Toast.makeText(PersonalMainPageActivity.this, "登出成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PersonalMainPageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}