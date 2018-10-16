package com.claudebernard.projetbf21.control;

import android.content.Context;

import java.util.List;

public interface GenericControl<T> {

    List<T> getDataAll();
    T getData(String option, Integer id);
    boolean saveData(T object);
    boolean editData(T object);
    boolean deleteData(T object);
}
