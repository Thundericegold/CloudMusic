package com.ixuea.courses.mymusic.activity;

import com.ixuea.courses.mymusic.util.PreferenceUtil;

/**
 * 项目中特有逻辑
 * <p>
 * 例如:显示迷你控制栏播放状态
 */
public class BaseLogicActivity extends BaseCommonActivity {

    protected PreferenceUtil sp;

    @Override
    protected void initDatum() {
        super.initDatum();
        sp = PreferenceUtil.getInstance(getHostActivity());
    }

    /**
     * 获取界面方法
     *
     * @return
     */
    protected BaseLogicActivity getHostActivity() {
        return this;
    }
}
