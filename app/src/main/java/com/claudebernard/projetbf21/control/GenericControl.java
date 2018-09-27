package com.claudebernard.projetbf21.control;

import android.content.Context;

import java.util.List;

public interface GenericControl<T> {

    List<T> getDataAll();
    T getData(Integer id);
    void saveData(Context c, T object);
    void editData(Context c, T object);
    void deleteData(Context c, T object);
}
