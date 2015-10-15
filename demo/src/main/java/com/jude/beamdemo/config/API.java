package com.jude.beamdemo.config;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public class API {


    public static class URL{
        public static final String BASEURL = "http://redrock.hotwoo.cn/zhihu/";

        public static final String GetQuestionList = "/getQuestionList.php";
        public static final String GetAnswerList = "/getAnswerList.php";
    }

    public static class WRAPPER {
        public static final String STATUS = "status";
        public static final String INFO = "info";
        public static final String DATA = "data";
    }

    public static class CODE{
        public static final int SUCCEED = 200;
    }

    public static class HEADER{
        public static  String TOKEN = "";
        public static  String UID = "";
    }
}
