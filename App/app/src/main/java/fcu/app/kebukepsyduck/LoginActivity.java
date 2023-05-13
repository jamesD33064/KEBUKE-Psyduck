package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView loginRegister;
    EditText loginPhoneNumber;
    EditText loginPassword;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
        boolean isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            Intent intent = new Intent(LoginActivity.this, PersonalMainPageActivity.class);
            startActivity(intent);
            finish(); // Finish the LoginActivity so the user cannot go back to it
            return;
        }

        setContentView(R.layout.activity_login);

        loginRegister = findViewById(R.id.tv_login_register);
        loginPhoneNumber = findViewById(R.id.et_login_phoneNumber);
        loginPassword = findViewById(R.id.et_login_password);
        loginBtn = findViewById(R.id.btn_login);
        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = loginPhoneNumber.getText().toString();
                String password = loginPassword.getText().toString();
                if (phoneNumber.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "請輸入電話號碼和密碼", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseHandler db = new DatabaseHandler(LoginActivity.this);
                db.open();
                if (db.getMemberPassword(phoneNumber) == null) {
                    Toast.makeText(LoginActivity.this, "此電話號碼尚未註冊", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!db.getMemberPassword(phoneNumber).equals(password)) {
                    Toast.makeText(LoginActivity.this, "密碼錯誤", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveLoginStatus(true);
                Intent intent = new Intent(LoginActivity.this, PersonalMainPageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveLoginStatus(boolean isLoggedIn) {
        SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.apply();
    }
}