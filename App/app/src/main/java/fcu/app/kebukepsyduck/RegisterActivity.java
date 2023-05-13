package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText registerPhoneNumber;
    EditText registerPassword;
    Button registerBtn;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPhoneNumber = findViewById(R.id.et_register_phoneNumber);
        registerPassword = findViewById(R.id.et_register_password);
        registerBtn = findViewById(R.id.btn_register);
        db = new DatabaseHandler(this);
        db.open();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = registerPhoneNumber.getText().toString();
                String password = registerPassword.getText().toString();
                if (phoneNumber.equals("") || password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "請輸入電話號碼和密碼", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (db.addMember(phoneNumber, password)) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}