package com.captain.wds.degsinpattern.contextState;

import android.content.Context;

public interface UserState {
    /**
     * 转发
     * @param context
     */
     void forward(Context context);

    /**
     * 评论
     * @param context
     */
     void comment(Context context);
}
