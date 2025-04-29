package edu.uic.cs478.spring2025.project3_br;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListViewModel extends ViewModel{

    private final MutableLiveData<Integer> selectedItem =  new MutableLiveData<>();

    public void selectedItem(Integer item){
        selectedItem.setValue(item);
    }

    public LiveData<Integer> getSelectedItem(){
        return selectedItem;
    }
}
