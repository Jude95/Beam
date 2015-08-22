package com.jude.beamdemo.config;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public class API {
    public static class URL{
        public static final String GetQuestionList = "http://redrock.hotwoo.cn/zhihu/getQuestionList.php";
        public static final String PublicQuestion = "http://redrock.hotwoo.cn/zhihu/question.php";
        public static final String GetAnswerList = "http://redrock.hotwoo.cn/zhihu/getAnswerList.php";
        public static final String PublicAnswer = "http://redrock.hotwoo.cn/zhihu/answer.php";
        public static final String QiniuToken = "http://redrock.hotwoo.cn/zhihu/qiniu.php";
        public static final String ModifyFace = "http://redrock.hotwoo.cn/zhihu/modifyFace.php";
        public static final String Register = "http://redrock.hotwoo.cn/zhihu/register.php";
        public static final String Login = "http://redrock.hotwoo.cn/zhihu/login.php";
    }

    public static class KEY{
        public static final String STATUS = "status";
        public static final String INFO = "info";
        public static final String DATA = "data";
    }

    public static class CODE{
        public static final int SUCCEED = 200;
        public static final int Failure = 0;
        public static final int PERMISSION_DENIED = 213;
    }
}
