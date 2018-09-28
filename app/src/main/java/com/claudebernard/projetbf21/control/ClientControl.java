package com.claudebernard.projetbf21.control;

import com.claudebernard.projetbf21.model.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ClientControl {


    private static ArrayList<Client> _listClients = new ArrayList<>();


    //=====
    public static ArrayList getDataClients() {

        Collections.sort(_listClients, new Comparator<Client>() {
            public int compare(Client c1, Client c2) {
                return c1.get_name().compareTo(c2.get_name());
            }
        });

        return _listClients;
    }


    //=====
    public static boolean addClient(Client client) {
        _listClients.add(client);

        return true;
    }


    //=====
    public static boolean modifyClient(Client client) {

        for (int i = 0; i < _listClients.size(); i++) {

            if (client.get_id() == _listClients.get(i).get_id()){
                _listClients.get(i).set_name(client.get_name());
                _listClients.get(i).set_age(client.get_age());
                _listClients.get(i).set_eMail(client.get_eMail());
                _listClients.get(i).set_phoneNumber(client.get_phoneNumber());
                _listClients.get(i).set_height(client.get_height());
                _listClients.get(i).set_weight(client.get_weight());
                _listClients.get(i).set_bodyFatPercentage(client.get_bodyFatPercentage());
                _listClients.get(i).set_idClientGoal(client.get_idClientGoal());
                _listClients.get(i).set_tdee(client.get_tdee());

                return true;
            }
        }
        return false;
    }


    //=====
    public static boolean removeClient(Client client) {

        for (int i = 0; i < _listClients.size(); i++) {

            if (client.get_id() == _listClients.get(i).get_id()){
                _listClients.remove(i);

                return true;
            }
        }
        return false;
    }
}
