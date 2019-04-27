package com.captain.wds.degsinpattern.contextState;

import android.content.Context;
import android.content.Intent;

import com.captain.wds.degsinpattern.LoginActivity;

public class LogoutState implements UserState {
    @Override public void forward(Context context) {
        gotoLoginActivity(context);
    }

    private void gotoLoginActivity(Context context) {
        context.startActivity(new Intent(context,LoginActivity.class));
    }

    @Override public void comment(Context context) {
        gotoLoginActivity(context);
    }
}
