package com.example.wind.doan.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wind.doan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ShowList extends AppCompatActivity {

    ListView listView;

    TextView title;

    ArrayList<ListView_player> mangListview;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView)findViewById(R.id.lv);

        mangListview = new ArrayList<ListView_player>();

        intent = getIntent();

        title = (TextView)findViewById(R.id.title);

        clicklistview();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.amin_title);
        title.startAnimation(animation);

        //Nhận gói dữ liệu từ activity_home_main
        Bundle bundle = intent.getBundleExtra("data");
        //Đọc dữ liệu và gán vào biến Places
        final String url = bundle.getString("url");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new DocJSON().execute(url);
            }
        });
    }

    class DocJSON extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String kq = docNoiDung_Tu_URL(params[0]);
            return kq;
        }

        @Override
        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

            try {
//                Log.d("Thong bao ","Ban dang o onPostExecute");
//                JSONArray mangJson = new JSONArray(s);
                JSONObject DTJsoObject = new JSONObject(s);
                JSONArray mangJsonArray = DTJsoObject.getJSONArray("results");
                String ketqua = "";
                for (int i = 0; i < mangJsonArray.length(); i++) {
//                    Log.d("Thong bao ","Ban dang o dong for onPostExecute");
                    JSONObject jsonObject = mangJsonArray.getJSONObject(i);
                    mangListview.add(new ListView_player
                            (
                                    jsonObject.getString("name"),
//
                                    jsonObject.getString("vicinity")

                            ));
                }
                Log.d("Ket Qua ", ketqua.toString());
                ListviewAdapter adapter = new ListviewAdapter
                        (
                                getApplicationContext(),
                                R.layout.activity_dong_dl,
                                mangListview
                        );
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }

    private void clicklistview(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String position = mangListview.get(i).diachi;

                Toast.makeText(getApplicationContext(), position, Toast.LENGTH_LONG).show();
            }
        });
    }
}
