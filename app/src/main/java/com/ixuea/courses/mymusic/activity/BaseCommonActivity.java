package com.ixuea.courses.mymusic.activity;

import android.content.Intent;


/**
 * 通用界面逻辑
 */
public class BaseCommonActivity extends BaseActivity {
    /**
     * 启动界面并关闭当前界面
     *
     * @param clazz
     */
    protected void startActivityAfterFinishThis(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }
}
