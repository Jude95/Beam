package com.jude.beamdemo.utils;

import com.jude.utils.JTimeTransform;

public class RecentDateFormat implements JTimeTransform.DateFormat{
    @Override
    public String format(JTimeTransform date, long delta) {
        if (delta>0){
            if (delta / JTimeTransform.SECOND < 1){
                return delta +"秒前";
            }else if (delta / JTimeTransform.HOUR < 1){
                return delta / JTimeTransform.SECOND+"分钟前";
            }else if (delta / JTimeTransform.DAY < 2 && new JTimeTransform().getDay() == date.getDay()){
                return delta / JTimeTransform.HOUR+"小时前";
            }else if (delta / JTimeTransform.DAY < 3 && new JTimeTransform().getDay() == new JTimeTransform(date.getTimestamp()+ JTimeTransform.DAY).getDay()){
                return "昨天"+date.toString("HH:mm");
            }else if (delta / JTimeTransform.DAY < 4 && new JTimeTransform().getDay() == new JTimeTransform(date.getTimestamp()+ JTimeTransform.DAY*2).getDay()){
                return "前天"+date.toString("HH:mm");
            }else{
                return date.toString("yyyy/MM/dd  hh:mm");
            }
        }else{
            delta = -delta;
            if (delta / JTimeTransform.SECOND < 1){
                return delta +"秒后";
            }else if (delta / JTimeTransform.HOUR < 1){
                return delta / JTimeTransform.SECOND+"分钟后";
            }else if (delta / JTimeTransform.DAY > -2 && new JTimeTransform().getDay() == date.getDay()){
                return delta / JTimeTransform.HOUR+"小时后";
            }else if (delta / JTimeTransform.DAY > -3 && new JTimeTransform().getDay() == new JTimeTransform(date.getTimestamp()- JTimeTransform.DAY).getDay()){
                return "明天"+date.toString("HH:mm");
            }else if (delta / JTimeTransform.DAY > -4 && new JTimeTransform().getDay() == new JTimeTransform(date.getTimestamp()- JTimeTransform.DAY*2).getDay()){
                return "后天"+date.toString("HH:mm");
            }else{
                return date.toString("yyyy/MM/dd  hh:mm");
            }
        }
    }
}