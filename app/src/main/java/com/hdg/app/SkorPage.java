package com.hdg.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SkorPage extends AppCompatActivity {

    private static String ip = "10.0.2.2";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "AppDb";
    private static String username = "sa";
    private static String password = "koizora22";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    private TextView text,score;
    private Button buton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_page);

        text = (TextView) findViewById(R.id.textView);
        score = (TextView) findViewById(R.id.gameScore);
        buton = (Button) findViewById(R.id.anaSayfa);

        int id = 1;
        int count = 0;

        Intent intent = getIntent();
        int puanDeğeri = intent.getIntExtra("Skor",0);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            if(connection!=null){
                String query = "SELECT * FROM score ORDER BY Puan DESC";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                     // ID sütunu adına göre güncellenmeli
                    int puan = rs.getInt("Puan"); // Puan sütunu adına göre güncellenmeli
                    if(count<10){
                        String existingText = text.getText().toString(); // Mevcut içeriği alın
                        String score ="ID: "+ id + ", Puan: "+puan+"\n";
                        String updatedText = existingText + score; // Mevcut içeriğe yeni veriyi ekleyin
                        System.out.println("ID: " + id + ", Puan: " + puan);
                        text.setText(updatedText);
                        count++;
                    }else{
                        break;
                    }
                    id++;
                }
            }
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

        }
        score.setText("Skorunuz: "+puanDeğeri);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SkorPage.this,MainActivity.class);
                startActivity(intent1);
            }
        });

    }


}