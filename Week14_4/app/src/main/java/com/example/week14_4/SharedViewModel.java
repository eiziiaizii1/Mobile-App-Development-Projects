package com.example.week14_4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> text = new MutableLiveData<>();

    public void setText(String input) {
        text.setValue(input);
    }

    public LiveData<String> getText() {
        return text;
    }
}
