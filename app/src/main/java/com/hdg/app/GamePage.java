package com.hdg.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePage extends AppCompatActivity {

    private Button [][] buttons = new Button[10][8];
    private Button trueButton,falseButton;
    private TextView text,puan,hataText;

    // Buton dolu mu değil mi kontrolü için
    private int [][] control = new int [10][8];
    // Butona tıklanmış mı kontrolü
    private int [][] click = new int [10][8];
    private String [] sesliHarfler = {"A","E","I","İ","O","Ö","U","Ü"};
    private String [] sessizHarfler = {"B","C","Ç","D","F","G","Ğ","H","J","K","L","M","N","P","R"
            ,"S","Ş","T","V","Y","Z"};
    private String [] alfabe = {"A","B","C","Ç","D","E","F","G","Ğ","H","I","İ","J","K","L","M","N",
            "O","Ö","P","R","S","Ş","T","U","Ü","V","Y","Z"};
    private String word="";
    private String [] hataColumn = new String [10];

    // Rastgele inecek harfin hangi konumu
    private int landing_column;
    private int letter_index;
    private int skor=0;
    private int hata=0;

    private static String ip = "10.0.2.2";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "AppDb";
    private static String username = "sa";
    private static String password = "koizora22";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        buttons[0][0] = (Button) findViewById(R.id.b11);buttons[0][1] = (Button) findViewById(R.id.b12);
        buttons[0][2] = (Button) findViewById(R.id.b13);buttons[0][3] = (Button) findViewById(R.id.b14);
        buttons[0][4] = (Button) findViewById(R.id.b15);buttons[0][5] = (Button) findViewById(R.id.b16);
        buttons[0][6] = (Button) findViewById(R.id.b17);buttons[0][7] = (Button) findViewById(R.id.b18);

        buttons[1][0] = (Button) findViewById(R.id.b21);buttons[1][1] = (Button) findViewById(R.id.b22);
        buttons[1][2] = (Button) findViewById(R.id.b23);buttons[1][3] = (Button) findViewById(R.id.b24);
        buttons[1][4] = (Button) findViewById(R.id.b25);buttons[1][5] = (Button) findViewById(R.id.b26);
        buttons[1][6] = (Button) findViewById(R.id.b27);buttons[1][7] = (Button) findViewById(R.id.b28);

        buttons[2][0] = (Button) findViewById(R.id.b31);buttons[2][1] = (Button) findViewById(R.id.b32);
        buttons[2][2] = (Button) findViewById(R.id.b33);buttons[2][3] = (Button) findViewById(R.id.b34);
        buttons[2][4] = (Button) findViewById(R.id.b35);buttons[2][5] = (Button) findViewById(R.id.b36);
        buttons[2][6] = (Button) findViewById(R.id.b37);buttons[2][7] = (Button) findViewById(R.id.b38);

        buttons[3][0] = (Button) findViewById(R.id.b41);buttons[3][1] = (Button) findViewById(R.id.b42);
        buttons[3][2] = (Button) findViewById(R.id.b43);buttons[3][3] = (Button) findViewById(R.id.b44);
        buttons[3][4] = (Button) findViewById(R.id.b45);buttons[3][5] = (Button) findViewById(R.id.b46);
        buttons[3][6] = (Button) findViewById(R.id.b47);buttons[3][7] = (Button) findViewById(R.id.b48);

        buttons[4][0] = (Button) findViewById(R.id.b51);buttons[4][1] = (Button) findViewById(R.id.b52);
        buttons[4][2] = (Button) findViewById(R.id.b53);buttons[4][3] = (Button) findViewById(R.id.b54);
        buttons[4][4] = (Button) findViewById(R.id.b55);buttons[4][5] = (Button) findViewById(R.id.b56);
        buttons[4][6] = (Button) findViewById(R.id.b57);buttons[4][7] = (Button) findViewById(R.id.b58);

        buttons[5][0] = (Button) findViewById(R.id.b61);buttons[5][1] = (Button) findViewById(R.id.b62);
        buttons[5][2] = (Button) findViewById(R.id.b63);buttons[5][3] = (Button) findViewById(R.id.b64);
        buttons[5][4] = (Button) findViewById(R.id.b65);buttons[5][5] = (Button) findViewById(R.id.b66);
        buttons[5][6] = (Button) findViewById(R.id.b67);buttons[5][7] = (Button) findViewById(R.id.b68);

        buttons[6][0] = (Button) findViewById(R.id.b71);buttons[6][1] = (Button) findViewById(R.id.b72);
        buttons[6][2] = (Button) findViewById(R.id.b73);buttons[6][3] = (Button) findViewById(R.id.b74);
        buttons[6][4] = (Button) findViewById(R.id.b75);buttons[6][5] = (Button) findViewById(R.id.b76);
        buttons[6][6] = (Button) findViewById(R.id.b77);buttons[6][7] = (Button) findViewById(R.id.b78);

        buttons[7][0] = (Button) findViewById(R.id.b81);buttons[7][1] = (Button) findViewById(R.id.b82);
        buttons[7][2] = (Button) findViewById(R.id.b83);buttons[7][3] = (Button) findViewById(R.id.b84);
        buttons[7][4] = (Button) findViewById(R.id.b85);buttons[7][5] = (Button) findViewById(R.id.b86);
        buttons[7][6] = (Button) findViewById(R.id.b87);buttons[7][7] = (Button) findViewById(R.id.b88);

        buttons[8][0] = (Button) findViewById(R.id.b91);buttons[8][1] = (Button) findViewById(R.id.b92);
        buttons[8][2] = (Button) findViewById(R.id.b93);buttons[8][3] = (Button) findViewById(R.id.b94);
        buttons[8][4] = (Button) findViewById(R.id.b95);buttons[8][5] = (Button) findViewById(R.id.b96);
        buttons[8][6] = (Button) findViewById(R.id.b97);buttons[8][7] = (Button) findViewById(R.id.b98);

        buttons[9][0] = (Button) findViewById(R.id.b101);buttons[9][1] = (Button) findViewById(R.id.b102);
        buttons[9][2] = (Button) findViewById(R.id.b103);buttons[9][3] = (Button) findViewById(R.id.b104);
        buttons[9][4] = (Button) findViewById(R.id.b105);buttons[9][5] = (Button) findViewById(R.id.b106);
        buttons[9][6] = (Button) findViewById(R.id.b107);buttons[9][7] = (Button) findViewById(R.id.b108);

        trueButton = (Button) findViewById(R.id.t);
        falseButton = (Button) findViewById(R.id.f);
        text = (TextView) findViewById(R.id.text);
        puan = (TextView) findViewById(R.id.puan);
        hataText = (TextView) findViewById(R.id.hataSayisi);

        puan.setText("Puan: 0");
        hataText.setText("Hata: ");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                control[i][j]=0;
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                control[i][j]=0;
            }
        }

        Random r=new Random();
        // 0 ile 7 arasında rastgele sütun seçimi
        landing_column = r.nextInt(8);

        // Başlangıçtaki 3 sütunun ve gelecek olan ilk harfin indirilmesi için
        start_landing();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                int row = i;
                int column = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Seçmek için tıklanmış
                        if(click[row][column]==0)
                        {
                            click[row][column]=1;
                            buttons[row][column].setBackgroundColor(Color.CYAN);
                            String temp = buttons[row][column].getText().toString();
                            word = word+temp;
                            text.setText(word);
                        }else if(click[row][column]==1) { // Seçimi kaldırmak için tıklanmış
                            click[row][column]=0;
                            buttons[row][column].setBackgroundColor(Color.LTGRAY);
                            String temp = buttons[row][column].getText().toString();
                            word = word.substring(0, word.length() - 1);
                            text.setText(word);
                        }
                    }
                });
            }
        }

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word = "";
                text.setText(word);
                for (int i = 0; i <10 ; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(click[i][j]==1){
                            click[i][j]=0;
                            buttons[i][j].setBackgroundColor(Color.LTGRAY);
                        }
                    }
                }
            }
        });

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kelime var mı bakılacak
                if(searchWord(v,word)==1){ // kelime var
                    // puan artışı
                    for (int i = 0; i < word.length(); i++) {
                        if(word.charAt(i)=='A' || word.charAt(i)=='E' || word.charAt(i)=='İ' || word.charAt(i)=='K' ||
                                word.charAt(i)=='L' || word.charAt(i)=='N' || word.charAt(i)=='R' || word.charAt(i)=='T' ){
                                skor+=1;
                        }else if(word.charAt(i)=='I' || word.charAt(i)=='M' || word.charAt(i)=='O' || word.charAt(i)=='S' ||
                                word.charAt(i)=='U'){
                            skor+=2;
                        }else if(word.charAt(i)=='B' || word.charAt(i)=='D' || word.charAt(i)=='Ü' || word.charAt(i)=='Y' ){
                            skor+=3;
                        }else if(word.charAt(i)=='C' || word.charAt(i)=='Ç' || word.charAt(i)=='Ş' || word.charAt(i)=='Z' ){
                            skor+=4;
                        }else if(word.charAt(i)=='G' || word.charAt(i)=='H' || word.charAt(i)=='P'  ){
                            skor+=5;
                        }else if(word.charAt(i)=='F' || word.charAt(i)=='Ö' || word.charAt(i)=='V'  ){
                            skor+=7;
                        }else if(word.charAt(i)=='Ğ'){
                            skor+=8;
                        }else if(word.charAt(i)=='J'){
                            skor+=10;
                        }
                    }

                    // Kelimenin harfleri butonlardan silinecek
                    for (int j = 0; j < 8; j++) {
                        for (int i = 0; i < 10; i++) {
                            if(click[i][j]==1){
                                control[i][j]=0;
                                buttons[i][j].setText("");
                                buttons[i][j].setBackgroundColor(Color.LTGRAY);
                                click[i][j]=0;
                            }
                        }
                    }
                    fixButtons();
                    puan.setText("Puan: "+skor);

                }else{ // yok
                    // hata artışı
                    if(hata<2){
                        hata++;
                        if(hata==1)
                            hataText.setText("Hata: X");
                        else if(hata==2)
                            hataText.setText("Hata: XX");
                    }else{
                        hata=0;
                        hataText.setText("Hata: ");
                        int sesliAdet;
                        int sessizAdet;

                        // Gelecek harflerin rastgele belirlenmesi
                        for (int i = 0; i <3 ; i++) {
                            Random r=new Random();
                            // 1 ile 4 arasında (4 dahil) sayı
                            // En az bir tane en çok 4 tane sesli harf olacak şekilde
                            sesliAdet = r.nextInt(4)+1;
                            sessizAdet = 8-sesliAdet;
                            int j;
                            for (j = 0; j < sesliAdet; j++) {
                                int temp = r.nextInt(8);
                                hataColumn[j]=sesliHarfler[temp];
                            }
                            for ( ; j < 8; j++) {
                                int temp = r.nextInt(21);
                                hataColumn[j]=sessizHarfler[temp];
                            }
                        }

                        Handler handler1 = new Handler();
                        Handler handler2 = new Handler();
                        Handler handler3 = new Handler();
                        Handler handler4 = new Handler();

                        // 1 sütun harf düşmesi
                        for (int a = 0; a<buttons.length;a++) {
                            int j = a;

                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(control[j][0] == 0)
                                        buttons[j][0].setText(hataColumn[0]);
                                    if(control[j][1] == 0)
                                        buttons[j][1].setText(hataColumn[1]);
                                    if(control[j][2] == 0)
                                        buttons[j][2].setText(hataColumn[2]);
                                    if(control[j][3] == 0)
                                        buttons[j][3].setText(hataColumn[3]);
                                    if(control[j][4] == 0)
                                        buttons[j][4].setText(hataColumn[4]);
                                    if(control[j][5] == 0)
                                        buttons[j][5].setText(hataColumn[5]);
                                    if(control[j][6] == 0)
                                        buttons[j][6].setText(hataColumn[6]);
                                    if(control[j][7] == 0)
                                        buttons[j][7].setText(hataColumn[7]);

                                    // Son satıra gelmiş mi kontrolü
                                    if ((j + 1) < 10) {
                                        if (control[j + 1][0] == 0) {
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttons[j][0].setText("");
                                                }
                                            }, 1000);
                                        } else {
                                            control[j][0] = 1;
                                        }
                                        if (control[j + 1][1] == 0) {
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttons[j][1].setText("");
                                                }
                                            }, 1000);
                                        } else {
                                            control[j][1] = 1;
                                        }
                                        if (control[j + 1][2] == 0) {
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttons[j][2].setText("");
                                                }
                                            }, 1000);
                                        } else {
                                            control[j][2] = 1;
                                        }
                                        if (control[j + 1][3] == 0) {
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttons[j][3].setText("");
                                                }
                                            }, 1000);
                                        } else {
                                            control[j][3] = 1;
                                        }
                                        if (control[j + 1][4] == 0) {
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttons[j][4].setText("");
                                                }
                                            }, 1000);
                                        } else {
                                            control[j][4] = 1;
                                        }
                                        if (control[j + 1][5] == 0) {
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttons[j][5].setText("");
                                                }
                                            }, 1000);
                                        } else {
                                            control[j][5] = 1;
                                        }
                                        if (control[j + 1][6] == 0) {
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttons[j][6].setText("");
                                                }
                                            }, 1000);
                                        } else {
                                            control[j][6] = 1;
                                        }
                                        if (control[j + 1][7] == 0) {
                                            handler2.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttons[j][7].setText("");
                                                }
                                            }, 1000);
                                        } else {
                                            control[j][7] = 1;
                                        }
                                    }
                                }
                            }, 1000 * (a+1));
                        }
                    }
                }


                // Varsa puan artacak yoksa hata sayısı
                // Cümle sıfırlanacak // Seçimler kalkacak
                word = "";
                text.setText(word);
                for (int i = 0; i <10 ; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(click[i][j]==1){
                            click[i][j]=0;
                            buttons[i][j].setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }
            }
        });

        Handler handler1 = new Handler();
        Handler handler2 = new Handler();
        Handler handler3 = new Handler();

        int sure = 1000;
        if(skor<100){
            sure=5000;
        }
        else if(skor>=100 && skor<200){
            sure=4000;
        }else if(skor>=200 && skor<300){
            sure=3000;
        }else if(skor>=300 && skor<400){
            sure=2000;
        }else if(skor>=400){
            sure=1000;
        }

        // Harf düşürme için
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler3.post(new Runnable() {
                    @Override
                    public void run() {
                        landing_column = r.nextInt(8);
                        letter_index = r.nextInt(29);

                        if(control[0][landing_column]!=1){
                            for (int a = 0; a<buttons.length ;a++) {
                                int j = a;
                                handler1.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(control[j][landing_column]==0){
                                            buttons[j][landing_column].setText(alfabe[letter_index]);
                                        }
                                        // Son satıra gelmiş mi kontrolü
                                        if((j+1)<10){
                                            // Sonraki satır dolu mu diye
                                            if(control[j+1][landing_column]==0){
                                                handler2.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        buttons[j][landing_column].setText("");
                                                    }
                                                    },1000);
                                            }else{
                                                control[j][landing_column]=1;
                                            }
                                        }
                                    }
                                    }, 1000 * (a+1));
                            }
                        }else{
                            insertScore(skor);
                            Intent intent = new Intent(GamePage.this,SkorPage.class);
                            intent.putExtra("Skor",skor);
                            startActivity(intent);
                        }
                    }
                });
            }
        };
        timer.schedule(task, 15000, ((findEmptySpots(landing_column)+1)*1000)+sure);
    }

    private void fixButtons(){
        int start=-1,first=0,count=0;

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 10; i++) {
                if(control[i][j]==0){
                    start = i;
                    while(start>0 && control[i][j]==0){
                        buttons[start][j].setText(buttons[start-1][j].getText().toString());
                        buttons[start-1][j].setText("");
                        start--;
                    }
                }
            }
        }

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 10; i++) {
                if(control[i][j]==1) {
                    count++;
                }
            }
            for (int i = 9; i >=0; i--) {
                if(count>0){
                    control[i][j]=1;
                    count--;
                }
                else
                    control[i][j]=0;

            }
            count = 0;
        }

    }

    private void insertScore(int skor){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        PreparedStatement pstmt = null;
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            if(connection!=null){
                String sql = "INSERT INTO score (Puan) VALUES (?)";
                pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1,skor); // Eklemek istediğiniz puan değeri
                pstmt.executeUpdate();
            }
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

        }
    }

    private int findEmptySpots(int column){
        int count=0;
        for (int i = 0; i < 10; i++) {
            if(control[i][column]==0)
                count++;
            else
                break;
        }
        return count;
    }

    public int searchWord(View view,String word) {
        int exist=0;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            if(connection!=null){
                String query = "SELECT * FROM words WHERE words='"+word+"'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);

                if(rs.next()){
                    String data = rs.getString("words");
                    exist= 1;
                }else{
                    exist =0;
                }
            }
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        return exist;
    }

    private void start_landing(){
        int sesliAdet;
        int sessizAdet;
        String [][] column = new String[3][8];

        // Gelecek harflerin rastgele belirlenmesi
        for (int i = 0; i <3 ; i++) {
            Random r=new Random();
            // 1 ile 4 arasında (4 dahil) sayı
            // En az bir tane en çok 4 tane sesli harf olacak şekilde
            sesliAdet = r.nextInt(4)+1;
            sessizAdet = 8-sesliAdet;
            int j;
            for (j = 0; j < sesliAdet; j++) {
                int temp = r.nextInt(8);
                column[i][j]=sesliHarfler[temp];
            }
            for ( ; j < 8; j++) {
                int temp = r.nextInt(21);
                column[i][j]=sessizHarfler[temp];
            }
        }

        Handler handler1 = new Handler();
        Handler handler2 = new Handler();
        Handler handler3 = new Handler();
        Handler handler4 = new Handler();

        // 3 sütunun kaydırılması
        for (int a = 0; a<buttons.length;a++) {
            int j = a;

            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    buttons[j][0].setText(column[0][0]);
                    buttons[j][1].setText(column[0][1]);
                    buttons[j][2].setText(column[0][2]);
                    buttons[j][3].setText(column[0][3]);
                    buttons[j][4].setText(column[0][4]);
                    buttons[j][5].setText(column[0][5]);
                    buttons[j][6].setText(column[0][6]);
                    buttons[j][7].setText(column[0][7]);

                    // Son satıra gelmiş mi kontrolü
                    if((j+1)<10){
                        // Sonraki satır dolu mu diye
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                buttons[j][0].setText("");
                                buttons[j][1].setText("");
                                buttons[j][2].setText("");
                                buttons[j][3].setText("");
                                buttons[j][4].setText("");
                                buttons[j][5].setText("");
                                buttons[j][6].setText("");
                                buttons[j][7].setText("");

                                buttons[j][0].setText(column[1][0]);
                                buttons[j][1].setText(column[1][1]);
                                buttons[j][2].setText(column[1][2]);
                                buttons[j][3].setText(column[1][3]);
                                buttons[j][4].setText(column[1][4]);
                                buttons[j][5].setText(column[1][5]);
                                buttons[j][6].setText(column[1][6]);
                                buttons[j][7].setText(column[1][7]);

                                // Son satıra gelmiş mi kontrolü
                                if((j+1)<9){
                                    // Sonraki satır dolu mu diye
                                    handler3.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            buttons[j][0].setText("");
                                            buttons[j][1].setText("");
                                            buttons[j][2].setText("");
                                            buttons[j][3].setText("");
                                            buttons[j][4].setText("");
                                            buttons[j][5].setText("");
                                            buttons[j][6].setText("");
                                            buttons[j][7].setText("");

                                            buttons[j][0].setText(column[2][0]);
                                            buttons[j][1].setText(column[2][1]);
                                            buttons[j][2].setText(column[2][2]);
                                            buttons[j][3].setText(column[2][3]);
                                            buttons[j][4].setText(column[2][4]);
                                            buttons[j][5].setText(column[2][5]);
                                            buttons[j][6].setText(column[2][6]);
                                            buttons[j][7].setText(column[2][7]);

                                            // Son satıra gelmiş mi kontrolü
                                            if((j+1)<8){
                                                // Sonraki satır dolu mu diye
                                                handler4.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        buttons[j][0].setText("");
                                                        buttons[j][1].setText("");
                                                        buttons[j][2].setText("");
                                                        buttons[j][3].setText("");
                                                        buttons[j][4].setText("");
                                                        buttons[j][5].setText("");
                                                        buttons[j][6].setText("");
                                                        buttons[j][7].setText("");
                                                    }
                                                    },1000);
                                            }
                                        }
                                        },1000);
                                }
                            }
                            },1000);
                    }
                }
                }, 1000 * (a+1));
        }

        // Dolu sütunların belirlenmesi
        for (int i = 7; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                control[i][j]=1;
            }
        }
    }
}