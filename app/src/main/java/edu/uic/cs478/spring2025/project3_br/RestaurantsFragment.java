package edu.uic.cs478.spring2025.project3_br;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

public class RestaurantsFragment extends ListFragment {


    public int currIdx = -1;
    private ListViewModel model;



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
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        //single list item selection
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, RestaurantsActivity.restaurantsList));

        //sets selected list item to true
        if (currIdx != -1) {
            getListView().setItemChecked(currIdx, true);
        }

    }


    @Override
    public void onListItemClick(ListView listview, View view, int pos, long id){
        //checks if clicked item is already selected
        if(currIdx != pos){
            currIdx = pos;
            model.selectedItem(pos); //updated viewModel with new selection
        }

        //set selected item to true
        listview.setItemChecked(currIdx, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onStop(){
        super.onStop();
    }


}
