package com.claudebernard.projetbf21.control;

import com.claudebernard.projetbf21.model.Coach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CoachControl {


    private static ArrayList<Coach> _listCoaches = new ArrayList<>();


    //=====
    public static ArrayList getDataCoaches() {

        Collections.sort(_listCoaches, new Comparator<Coach>() {
            public int compare(Coach c1, Coach c2) {
                return c1.get_name().compareTo(c2.get_name());
            }
        });

        return _listCoaches;
    }


    //=====
    public static String getNameCoach(String login) {

        String ret = "Name Coach";

        return ret;
    }


    //=====
    public static boolean addCoach(Coach coach) {
        _listCoaches.add(coach);

        return true;
    }


    //=====
    public static boolean modifyCoach(Coach coach) {

        for (int i = 0; i < _listCoaches.size(); i++) {

            if (coach.get_id() == _listCoaches.get(i).get_id()){
                _listCoaches.get(i).set_name(coach.get_name());
                _listCoaches.get(i).set_eMail(coach.get_eMail());
                _listCoaches.get(i).set_phone(coach.get_phone());
                _listCoaches.get(i).set_login(coach.get_login());
                _listCoaches.get(i).set_password(coach.get_password());

                return true;
            }
        }
        return false;
    }


    //=====
    public static boolean removeCoach(Coach coach) {

        for (int i = 0; i < _listCoaches.size(); i++) {

            if (coach.get_id() == _listCoaches.get(i).get_id()){
                _listCoaches.remove(i);

                return true;
            }
        }
        return false;
    }
}
