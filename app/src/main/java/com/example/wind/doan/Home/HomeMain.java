package com.example.wind.doan.Home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.location.Location;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


import android.widget.SearchView;
import android.widget.Toast;

import com.example.wind.doan.MapsActivity;
import com.example.wind.doan.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class HomeMain extends Activity{

    GridView gridView;
    SearchView searchView;
    GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    LocationRequest mLocationRequest;

    String[] ten = {"Sân bay","Công viên","ATM","Trạm xăng","Bãi đỗ xe","Spa","Nhà hàng","Cà Phê","Hiệu thuốc","Cửa hàng",
            "Nhà Sách","Bưu điện","Phòng gym",
            "Trường Học","Bệnh Viện","Ngân hàng","Bar","Trạm xe buýt","Nhà thờ","Salon","Siêu thị","Rạp phim","Thư viện","Sở thú"};
    String[] places = {"airport","park","atm","gas_station","parking","spa","restaurant",
            "cafe","pharmacy","store","book_store","post_office","gym","school","hospital"
            ,"bank","bar","bus_station","church","beauty_salon","shopping_mall","movie_theater","library","zoo"};
    int[] hinh = {R.drawable.airplane,R.drawable.park,R.drawable.atm,R.drawable.gas,R.drawable.parking,R.drawable.spa,
            R.drawable.restaurant,R.drawable.coffee,R.drawable.pills,R.drawable.store,R.drawable.book,
            R.drawable.postoffice,R.drawable.gym,R.drawable.school,R.drawable.hospital,R.drawable.bank,R.drawable.bar,
    R.drawable.bus,R.drawable.church,R.drawable.salon,R.drawable.mall,R.drawable.cinema,R.drawable.library,R.drawable.zoo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkLocationPermission();
//        }

        gridView = (GridView)findViewById(R.id.gridView);
        searchView = (SearchView)findViewById(R.id.searchView);

        searchView.setVisibility(View.GONE);

        final GridviewAdapter gridviewAdapter = new GridviewAdapter(this, this.getPlayers());
        gridView.setAdapter(gridviewAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String arg0) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                gridviewAdapter.getFilter().filter(query);
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //khởi tạo intent

                Intent intent = new Intent(HomeMain.this, MapsActivity.class);

                //Đóng gói dữ liệu để gửi cho activity_maps.xml
                Bundle bundle = new Bundle();
                bundle.putString("name_places", places[position]);
                bundle.putString("name_places2", ten[position]);
                intent.putExtra("data", bundle);
//                Mở Activity_Result
                startActivity(intent);

//                Toast.makeText(HomeMain.this, places[position], Toast.LENGTH_LONG).show();
            }
        });
    }

    public ArrayList<player> getPlayers ()
    {
        ArrayList<player> players = new ArrayList<player>();
        player p;

        for (int i = 0; i < ten.length; i++)
        {
            p = new player(ten[i], hinh [i], places[i]);
            players.add(p);
        }
        return players;
    }
}
