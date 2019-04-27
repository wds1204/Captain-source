package com.captain.wds.degsinpattern.contextState;

import android.content.Context;

public class LoginContext implements UserState {
    UserState mState=new LogoutState();
    static LoginContext mLoginContext=new LoginContext();
    private LoginContext( ) {
        this.mState = mState;
    }

    public static LoginContext getLoginContext(){
        return mLoginContext;
    }

    public void setmState(UserState mState) {
        this.mState = mState;
    }


    @Override public void forward(Context context) {
        mState.forward(context);
    }

    @Override public void comment(Context context) {
        mState.comment(context);
    }
}
