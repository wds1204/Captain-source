package com.captain.wds.degsinpattern.contextState;

import android.content.Context;
import android.widget.Toast;

public class LoginedState implements UserState {
    @Override public void forward(Context context) {
        Toast.makeText(context, "转发", Toast.LENGTH_SHORT).show();
    }

    @Override public void comment(Context context) {
        Toast.makeText(context, "评论", Toast.LENGTH_SHORT).show();

    }
}
