package com.claudebernard.projetbf21.control;

public interface GenericControl<T> {

    void getDataAll(String option);
    void getData(String option, Integer id);
    void saveData(T object);
    void editData(T object);
    boolean deleteData(T object);
}
