package com.jude.beamdome;

/**
 * Created by Mr.Jude on 2015/7/26.
 */
public class Person {
    private String name;
    private String face;
    private String sign;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Person(String face, String name, String sign) {
        this.name = name;
        this.face = face;
        this.sign = sign;
    }
}
