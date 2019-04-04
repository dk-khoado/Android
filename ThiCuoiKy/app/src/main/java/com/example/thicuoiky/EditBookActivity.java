package com.example.thicuoiky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thicuoiky.Module.SQLControl;
import com.example.thicuoiky.models.Book;

public class EditBookActivity extends AppCompatActivity {
    Toolbar toolbar;
    ArrayAdapter<CharSequence> adapter;
    EditText tenSach, tenTG, tenNXB, soLuong;
    Spinner theloai;
    Button btnUpdate;
    SQLControl sqlControl;
    Book item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        init();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle("Chỉnh sửa Sách");
        Intent intent = getIntent();
        item = (Book)intent.getSerializableExtra("book");
        tenSach.setText(item.getTenSach());
        tenTG.setText(item.getTenTG());
        tenNXB.setText(item.getNXB());
        soLuong.setText(String.valueOf(item.getSoLuong()));

        adapter = ArrayAdapter.createFromResource(this,R.array.type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theloai.setAdapter(adapter);
        theloai.setSelection(intent.getIntExtra("ID", 0));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
                finish();
            }
        });
    }
    void init(){
        tenSach = findViewById(R.id.editBook_tenSach);
        tenTG = findViewById(R.id.editBook_tenTG);
        tenNXB = findViewById(R.id.editBook_NXB);
        soLuong = findViewById(R.id.editBook_soLuong);
        theloai =findViewById(R.id.editBook_theLoai);
        toolbar = findViewById(R.id.editBook_toolbar);
        btnUpdate = findViewById(R.id.editBook_btnUpdate);
        sqlControl  =new SQLControl(this);
    }
    void Update(){
        sqlControl.UpdateBook(item.getID(), tenSach.getText().toString(), tenTG.getText().toString(), tenNXB.getText().toString(), theloai.getSelectedItem().toString(), Integer.parseInt(soLuong.getText().toString()));
        Toast.makeText(this, "Đã Cập Nhập", Toast.LENGTH_SHORT).show();
    }
}
