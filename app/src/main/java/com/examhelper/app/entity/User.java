package com.examhelper.app.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/7/29.
 */

@DatabaseTable
public class User {
    //用户id
    @DatabaseField(id = true)
    private int userId;

    //登陆账号
    @DatabaseField
    private String username;

    //登陆密码
    @DatabaseField
    private String password;

    //权限
    @DatabaseField
    private int role;


    //创建时间
    @DatabaseField
    private String createDate;

    //状态
    @DatabaseField
    private int status;

    //微信标识
    @DatabaseField
    private String wecharOpenid;

    //qq号码
    @DatabaseField
    private String qqNumber;

    //qq标识
    @DatabaseField
    private String qqOpenid;

    //证书id
    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private Certification certification;

    public User() {
    }

    public User(int userId, String username, String password, int role, String createDate, int status, String wecharOpenid, String qqNumber, String qqOpenid, Certification certification) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createDate = createDate;
        this.status = status;
        this.wecharOpenid = wecharOpenid;
        this.qqNumber = qqNumber;
        this.qqOpenid = qqOpenid;
        this.certification = certification;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getWecharOpenid() {
        return wecharOpenid;
    }

    public void setWecharOpenid(String wecharOpenid) {
        this.wecharOpenid = wecharOpenid;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }
}
