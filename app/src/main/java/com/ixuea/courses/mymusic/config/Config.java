package com.ixuea.courses.mymusic.config;


import com.ixuea.courses.mymusic.BuildConfig;

/**
 * 配置文件
 * 例如:API地址，QQ等第三方服务配置信息等
 */
public class Config {
    /**
     * 默认延时时间
     */
    public static final long SPLASH_DEFAULT_DELAY_TIME = 1000;

    /**
     * 端点
     */
    public static String ENDPOINT = BuildConfig.ENDPOINT;

    /**
     * 资源端点
     */
    public static String RESOURCE_ENDPOINT = BuildConfig.RESOURCE_ENDPOINT;
}
