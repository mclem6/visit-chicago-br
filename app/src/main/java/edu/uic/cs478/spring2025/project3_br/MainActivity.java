package edu.uic.cs478.spring2025.project3_br;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver attractionsReceiver;
    private IntentFilter attractionsFilter;
    private BroadcastReceiver restaurantsReceiver;
    private IntentFilter restaurantsFilter;

    private static final String ATTRACTIONS_ACTION = "edu.uic.cs478.Broadcastreceiver.attractions";
    private static final String RESTAURANTS_ACTION = "edu.uic.cs478.Broadcastreceiver.restaurants";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //actionbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //create Receiver instances and IntentFilter instances
        attractionsReceiver = new AttractionsReceiver();
        attractionsFilter = new IntentFilter(ATTRACTIONS_ACTION);

        restaurantsReceiver = new RestaurantsReceiver();
        restaurantsFilter = new IntentFilter(RESTAURANTS_ACTION);

        //register receivers
        registerReceiver(attractionsReceiver, attractionsFilter, Context.RECEIVER_EXPORTED);
        registerReceiver(restaurantsReceiver, restaurantsFilter, Context.RECEIVER_EXPORTED);

        //get reference to fragments



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu, menu);
       return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.attractions_item){
            Intent i = new Intent(this, AttractionsActivity.class);
            startActivity(i);
            return true;
        }
        else if(item.getItemId() == R.id.restaurants_item) {
            Intent i = new Intent(this, RestaurantsActivity.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(attractionsReceiver);
        unregisterReceiver(restaurantsReceiver);
    }



}