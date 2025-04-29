package edu.uic.cs478.spring2025.project3_br;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AttractionsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //launch AttractionsActivity
        Intent i = new Intent(context, AttractionsActivity.class);
        context.startActivity(i);
    }
}