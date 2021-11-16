package com.example.btvn8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class add extends AppCompatActivity {
    private ImageView imgBackAdd;
    private EditText edtNameAdd;
    private EditText edtAddressAdd;
    private EditText edtPhoneAdd;
    private Button btnAdd,btnluu;
    private TextView title;
    private final Database database=new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initUI();

        Intent intent1=getIntent();
        String idd=intent1.getStringExtra("id");
        String name=intent1.getStringExtra("n");
        String address=intent1.getStringExtra("a");
        String phone = intent1.getStringExtra("p");
        String key = intent1.getStringExtra("KEY");

        if (key.equals("100")){
            title.setText("thêm sinh viên");
           imgBackAdd.setVisibility(View.VISIBLE);
           btnAdd.setVisibility(View.VISIBLE);
            btnluu.setVisibility(View.GONE);
        } else if (key.equals("101")) {
            title.setText("Chỉnh sửa sinh viên");
            imgBackAdd.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            btnluu.setVisibility(View.VISIBLE);
        }

        edtNameAdd.setText(name);
        edtAddressAdd.setText(address);
        edtPhoneAdd.setText(phone);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names = edtNameAdd.getText().toString().trim();
                String addresss = edtAddressAdd.getText().toString().trim();
                String phones = edtPhoneAdd.getText().toString().trim();

                if ((names.isEmpty()) || (addresss.isEmpty() || phones.isEmpty())) {
                    displayToast("Điền đủ thông tin đê bạn ơi!");
                    return;
                }
                else{

                    database.addContact(new Contact(edtNameAdd.getText().toString(),
                            edtAddressAdd.getText().toString(),edtPhoneAdd.getText().toString()));
                    displayToast("OK bạn ơi!");
                    cancelActivity();
                }

            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.updatedb(new Contact(Integer.parseInt(idd),edtNameAdd.getText().toString(),
                        edtAddressAdd.getText().toString(),edtPhoneAdd.getText().toString()));
                displayToast("OK bạn ơi!");
                cancelActivity();
            }
        });
        imgBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(add.this, androidx.appcompat.R.anim.abc_fade_in));
                Intent intent = new Intent(add.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initUI() {
        imgBackAdd = findViewById(R.id.img_back_add);
        edtNameAdd = findViewById(R.id.edt_name_add);
        title=findViewById(R.id.tv_tieude);
        edtAddressAdd = findViewById(R.id.edt_address_add);
        edtPhoneAdd = findViewById(R.id.edt_phone_add);
        btnAdd = findViewById(R.id.btn_add);
        btnluu=findViewById(R.id.btn_luu);
    }
    private void cancelActivity() {
        Intent intent = new Intent(add.this, MainActivity.class);
        startActivity(intent);
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}