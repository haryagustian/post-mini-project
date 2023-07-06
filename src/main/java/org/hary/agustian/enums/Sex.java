package org.hary.agustian.enums;

public enum Sex {
    MALE("M"), FEMALE("F");

    private final String sex;

    Sex(String sex){
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

}
