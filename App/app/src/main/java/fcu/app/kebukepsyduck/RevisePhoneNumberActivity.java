package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RevisePhoneNumberActivity extends AppCompatActivity {

    EditText revisePhoneNumber;
    Button revisePhoneNumberBtn;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_phone_number);

        revisePhoneNumber = findViewById(R.id.et_revise_new_phoneNumber);
        revisePhoneNumberBtn = findViewById(R.id.btn_revise_phoneNumber);
        db = new DatabaseHandler(this);
        db.open();

        revisePhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = revisePhoneNumber.getText().toString();
                if (phoneNumber.equals("")) {
                    Toast.makeText(RevisePhoneNumberActivity.this, "請輸入新的電話號碼", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
                String oldPhoneNumber = sharedPref.getString("phoneNumber", "");
                Boolean result = db.updateMemberPhone(oldPhoneNumber, phoneNumber);
                if (result) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("phoneNumber", phoneNumber);
                    editor.commit();
                }
                revisePhoneNumber.setText("");
            }
        });
    }
}