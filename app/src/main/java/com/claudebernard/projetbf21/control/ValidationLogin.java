package com.claudebernard.projetbf21.control;

import android.content.Context;
import android.content.Intent;

import com.claudebernard.projetbf21.view.ActivityClient;

public class ValidationLogin {

    public static final String EXTRA_MESSAGE = "com.claudebernard.projetbf21";

    public static boolean accessSystem(final Context c, final String login, final String password){

        Intent intent = new Intent(c, ActivityClient.class);
        intent.putExtra(EXTRA_MESSAGE, login);
        c.startActivity(intent);

        return true;
    }
}
