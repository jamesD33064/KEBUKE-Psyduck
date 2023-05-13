package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RevisePasswordActivity extends AppCompatActivity {

    EditText reviseOldPassword;
    EditText reviseNewPassword;
    Button revisePasswordBtn;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_password);

        reviseOldPassword = findViewById(R.id.et_revise_old_password);
        reviseNewPassword = findViewById(R.id.et_revise_new_password);
        revisePasswordBtn = findViewById(R.id.btn_revise_password);
        db = new DatabaseHandler(this);
        db.open();

        revisePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = reviseOldPassword.getText().toString();
                String newPassword = reviseNewPassword.getText().toString();
                if (oldPassword.equals("") || newPassword.equals("")) {
                    Toast.makeText(RevisePasswordActivity.this, "請輸入舊密碼和新密碼", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
                String phoneNumber = sharedPref.getString("phoneNumber", "");
                db.updateMemberPassword(phoneNumber, oldPassword, newPassword);
                reviseOldPassword.setText("");
                reviseNewPassword.setText("");
            }
        });


    }
}