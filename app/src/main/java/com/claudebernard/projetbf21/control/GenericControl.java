package com.claudebernard.projetbf21.control;

import android.view.View;

import java.util.List;

public interface GenericControl<T> {

    List<T> getDataAll(String option);
    T getData(String option, Integer id);
    boolean saveData(T object);
    boolean editData(T object);
    boolean deleteData(T object);
}
