package edu.uic.cs478.spring2025.project3_br;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class RestaurantsActivity extends AppCompatActivity {

    public static String[] restaurantsList;
    public static String[] webList;

    FragmentManager mFragmentManager;

    private FrameLayout restaurantListLayout;
    private FrameLayout webViewListLayout;

    webViewFragment myFragment = new webViewFragment();

    private ListViewModel mModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurants);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //get list of attractions
        restaurantsList = getResources().getStringArray(R.array.Restaurants);
        webList = getResources().getStringArray(R.array.WebSites_Restaurants);

        //get reference to fragments layout
        restaurantListLayout = findViewById(R.id.restaurants);
        webViewListLayout = findViewById(R.id.webview);

        //get a reference to the supportFragmentManager
        mFragmentManager = getSupportFragmentManager();

        final FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();


        fragmentTransaction.replace(
                R.id.restaurants, new RestaurantsFragment()
        );

        fragmentTransaction.commit();

        mFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {

                        setLayout();
                    }

                }
        );

        mModel = new ViewModelProvider(this).get(ListViewModel.class);

        mModel.getSelectedItem().observe(this, item -> {
            //check if fragment is displayed
            if(!myFragment.isAdded()){

                FragmentTransaction fragmentTransaction2 = mFragmentManager.beginTransaction();

                //add fragment to display
                fragmentTransaction2.add(R.id.webview, myFragment);

                //add fragment to backstack
                fragmentTransaction2.addToBackStack(null);

                //commit FragmentTransaction
                fragmentTransaction2.commit();

                mFragmentManager.executePendingTransactions();

            }
        });
    }


    public void setLayout(){

        if(!myFragment.isAdded()){

            restaurantListLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

            );

            webViewListLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

            );

            //uncheck selected item
            RestaurantsFragment restaurantsFrag = (RestaurantsFragment) getSupportFragmentManager().findFragmentById(R.id.restaurants);
            if(restaurantsFrag != null){
                ListView listview = restaurantsFrag.getListView();
                listview.setItemChecked(restaurantsFrag.currIdx, false);
                restaurantsFrag.currIdx = -1;

            }
        } else {

           restaurantListLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    LinearLayout.LayoutParams.MATCH_PARENT, 1)
            );

            webViewListLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 2)
            );


        }


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
}