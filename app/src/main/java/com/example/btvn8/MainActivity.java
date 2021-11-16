package com.example.btvn8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imgAdd,imgBackAdd;;

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<Contact> contactList;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        database = new Database(this);
//        database.addContact(new Contact("dat","bac giang","0362582413"));

//        database.deleteAll();
        adapter = new ContactAdapter(this);
        contactList = database.getAllContacts();
        adapter.setContacts(contactList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
imgAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, androidx.appcompat.R.anim.abc_fade_in));
        Intent intent = new Intent(MainActivity.this, add.class);
        intent.putExtra("KEY", "100");
        startActivity(intent);

    }
});
        adapter.setClickListener(new ContactAdapter.ClickListener() {
            @Override
            public void onClick(Contact folder, int position) {

                Intent intent1 = new Intent(MainActivity.this, add.class);
                String n=database.getAllContacts().get(position).getmName().toString();
                String a=database.getAllContacts().get(position).getmAddress().toString();
                String p=database.getAllContacts().get(position).getmPhone().toString();
                String id=database.getAllContacts().get(position).getmId()+"";
                intent1.putExtra("id",id);
                intent1.putExtra("n", n);
                intent1.putExtra("a", a);
                intent1.putExtra("p", p);
                intent1.putExtra("KEY", "101");
                startActivity(intent1);
            }
        });
    }
    private void initUI() {
        recyclerView = findViewById(R.id.recyclerview);
        imgAdd = findViewById(R.id.img_add);
        imgBackAdd=findViewById(R.id.img_back_add);
        imgBackAdd.setVisibility(View.GONE);
    }
}