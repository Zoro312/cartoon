package com.scartoon.starcartoon.consts;

public class CartoonConsts {//
    public static final String NO_UPLOAD_IMG="/images/logo.png";
    public static final Double AREA_RANGE=5.0;
    public static final String YES="YES";
    public static final String NO="NO";
    public static final String WINDOW_UPLOAD_PATH="F:\\";
    public static final String LINUX_UPLOAD_PATH="/";
    public static final String WINDOW_CONFIG_UPLOAD_PATH="file:F:\\cartoon\\";
    public static final String LINUX_CONFIG_UPLOAD_PATH="file:/cartoon/";
    public static String getConfigUploadPath(){
        String realPath= CartoonConsts.LINUX_CONFIG_UPLOAD_PATH;
        String systemType=System.getProperty("os.name");
        if(systemType.toLowerCase().contains("windows")){
            realPath= CartoonConsts.WINDOW_CONFIG_UPLOAD_PATH;
        }
        return realPath;
    }
    public static String getUploadPath(){
        String realPath= CartoonConsts.LINUX_UPLOAD_PATH;
        String systemType=System.getProperty("os.name");
        if(systemType.toLowerCase().contains("windows")){
            realPath= CartoonConsts.WINDOW_UPLOAD_PATH;
        }
        return realPath;
    }
}
