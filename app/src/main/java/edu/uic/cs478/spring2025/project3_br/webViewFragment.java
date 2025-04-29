package edu.uic.cs478.spring2025.project3_br;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.ViewModelProvider;

public class webViewFragment extends Fragment {

    private int currIdx = -1;

    private ListViewModel model;

    private WebView webView;


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);


    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //inflate fragment layout
        View rootView = inflater.inflate(R.layout.webview_fragment, container, false);

        // Initiliaze WebView  inflated layout
        webView = rootView.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //initialize viewModel
        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        //observe attraction selected
        model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {

            if(item < 0 ){
                return;
            }

            //make fragment weight 2

            FragmentContainerView fragment_webview = getActivity().findViewById(R.id.webview);
            fragment_webview.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2));

            //set selected attraction index
            currIdx = item;

            Log.i("MARIA", String.valueOf(currIdx));

            if(requireActivity().getClass().getName().equals("edu.uic.cs478.spring2025.project3_br.AttractionsActivity")){

                //load selected attraction's url
                webView.loadUrl(AttractionsActivity.webList[currIdx]);

            } else {

                //load selected attraction's url
                webView.loadUrl(RestaurantsActivity.webList[currIdx]);

            }




        });

    }

}
