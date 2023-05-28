package fcu.app.kebukepsyduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfirmOrderActivity extends AppCompatActivity {
  private Button btn_confirm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirm_order);


    btn_confirm = findViewById(R.id.btn_confirm);
    btn_confirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ConfirmOrderActivity.this, PersonalMainPageActivity.class);
        startActivity(intent);
      }
    });



  }
}