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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmOrderActivity extends AppCompatActivity {
  private Button btn_confirm;
  private DatabaseHandler db;

  private EditText et_phone;
  private EditText et_address;
  private EditText et_remark;
  private TextView totalcost;
  private String date_now;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirm_order);
    et_phone = findViewById(R.id.et_phone);
    et_address = findViewById(R.id.et_address);
    et_remark = findViewById(R.id.et_remark);
    totalcost = findViewById(R.id.totalcost);

    SharedPreferences sharedPref = getSharedPreferences("loginPref", MODE_PRIVATE);
    String PhoneNumber = sharedPref.getString("phoneNumber", "");

    int TotalCost = getIntent().getExtras().getInt("TotalCost");
    et_phone.setText(PhoneNumber);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date currentTime = Calendar.getInstance().getTime();
    date_now = sdf.format(currentTime.getTime());

    db = new DatabaseHandler(this);
    db.open();


    btn_confirm = findViewById(R.id.btn_confirm);
    btn_confirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        db.confirmCart(PhoneNumber, date_now);
        String adr = et_address.getText().toString();
        String remark = et_remark.getText().toString();
        db.addOrder(PhoneNumber, TotalCost, "doing", adr, remark, date_now);
        Toast.makeText(ConfirmOrderActivity.this, "訂購完成!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ConfirmOrderActivity.this, PersonalMainPageActivity.class);
        startActivity(intent);
      }
    });



  }
}