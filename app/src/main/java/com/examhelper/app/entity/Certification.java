package com.examhelper.app.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/7/29.
 */

@DatabaseTable
public class Certification {
    //证数ID
    @SerializedName("cerId")
    @DatabaseField(id = true,canBeNull = false)
    private int cerId;

    //证数名
    @SerializedName("name")
    @DatabaseField(canBeNull = false)
    private String cerName;

    //证数等级
    @SerializedName("grade")
    @DatabaseField(canBeNull = false)
    private String cerGrade;

    public Certification() {
    }

    public Certification(int cerId, String cerName, String cerGrade) {
        this.cerId = cerId;
        this.cerName = cerName;
        this.cerGrade = cerGrade;
    }

    public int getCerId() {
        return cerId;
    }

    public void setCerId(int cerId) {
        this.cerId = cerId;
    }

    public String getCerName() {
        return cerName;
    }

    public void setCerName(String cerName) {
        this.cerName = cerName;
    }

    public String getCerGrade() {
        return cerGrade;
    }

    public void setCerGrade(String cerGrade) {
        this.cerGrade = cerGrade;
    }

    @Override
    public String toString() {
        return "Certificates{" +
                "cerId=" + cerId +
                ", cerName='" + cerName + '\'' +
                ", cerGrade='" + cerGrade + '\'' +
                '}';
    }
}
