package com.captain.wds.degsinpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.captain.wds.degsinpattern.contextState.LoginContext;
import com.captain.wds.degsinpattern.contextState.LoginedState;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        LoginContext.getLoginContext().setmState(new LoginedState());
        finish();
    }
}
