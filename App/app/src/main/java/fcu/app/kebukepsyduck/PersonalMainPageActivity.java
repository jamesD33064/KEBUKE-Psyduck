package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalMainPageActivity extends AppCompatActivity {

    Button startOrderBtn;
    Button shoppingCartBtn;
    Button orderManageBtn;
    Button revisePhoneNumberBtn;
    Button revisePasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_main_page);

        startOrderBtn = findViewById(R.id.btn_personalPage_start_order);
        shoppingCartBtn = findViewById(R.id.btn_personalPage_shopping_cart);
        orderManageBtn = findViewById(R.id.btn_personalPage_order_manage);
        revisePhoneNumberBtn = findViewById(R.id.btn_personalPage_revise_phone);
        revisePasswordBtn = findViewById(R.id.btn_personalPage_revise_password);

        startOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PersonalMainPage.this, .class);
//                startActivity(intent);
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
//                Intent intent = new Intent(PersonalMainPage.this, .class);
//                startActivity(intent);
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
    }

}