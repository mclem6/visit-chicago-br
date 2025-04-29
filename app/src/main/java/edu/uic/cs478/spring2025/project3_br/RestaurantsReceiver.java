package edu.uic.cs478.spring2025.project3_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RestaurantsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //launch AttractionsActivity
        Intent i = new Intent(context, RestaurantsActivity.class);
        context.startActivity(i);

    }
}