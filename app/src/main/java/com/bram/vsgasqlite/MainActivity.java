package com.bram.vsgasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ed_nama, ed_jumlah;
    TextView tv_data;
    Button btnSimpan;
    DatabaseHelper databaseHelper;
    ArrayList<GetSet> dataBuku;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databaseHelper = new DatabaseHelper(this);
        ed_nama = findViewById(R.id.ed_nama_buku);
        ed_jumlah = findViewById(R.id.ed_jumlah);
        tv_data = findViewById(R.id.tv_data);
        btnSimpan = findViewById(R.id.btnSimpan);
        tampilData();
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_nama.getText().toString().equals("")){
                    ed_nama.setError("Harap isi terlebih dahulu");
                }else if(ed_jumlah.getText().toString().equals("")){
                    ed_jumlah.setError("Harap isi terlebih dahulu");
                }else {
                    setData();
                }

            }
        });
    }

    void setData(){
        databaseHelper.addBuku(ed_nama.getText().toString(),Integer.parseInt(ed_jumlah.getText().toString()));
        ed_nama.setText("");
        ed_jumlah.setText("");
        Toast.makeText(MainActivity.this, "Data buku tersimpan !", Toast.LENGTH_SHORT).show();
        tampilData();
    }
    void tampilData(){
        dataBuku = databaseHelper.getAllBukuList();
        tv_data.setText("");
        for (int i = 0; i<dataBuku.size(); i++){
            if (tv_data.getText().equals("")){
                tv_data.setText(dataBuku.get(i).nama_buku+" : "+dataBuku.get(i).jumlah.toString());
            }else {
                tv_data.setText(tv_data.getText().toString()+"\n"+dataBuku.get(i).nama_buku+" : "+dataBuku.get(i).jumlah.toString());
            }

        }
    }

}