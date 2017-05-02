package com.example.biao.thehealth.user.login.entities;

/**
 * Created by Biao on 2017/4/25.
 */

/**
 * User
 * 用户封装类，用户的属性包括用户名、密码、性别、联系方式等
 */
public class User {
    /**
     * username 用户名
     * password 密码
     * sex 用户性别
     * communication 用户联系方式
     */
    private String username = null;
    private String password = null;
    private String sex = null;
    private String communication = null;
    private String myCommunication_content = null;
    private String communication_way = null;
    private String communication_num = null;

    public String getMyCommunication_content() {
        return myCommunication_content;
    }
    public void setMyCommunication_content(String myCommunication_content) {
        this.myCommunication_content = myCommunication_content;
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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getCommunication() {
        return communication;
    }
    public void setCommunication(String communication) {
        this.communication = communication;
    }
    public String getCommunication_way() {
        return communication_way;
    }
    public void setCommunication_way(String communicationWay) {
        communication_way = communicationWay;
    }
    public String getCommunication_num() {
        return communication_num;
    }
    public void setCommunication_num(String communicationNum) {
        communication_num = communicationNum;
    }
}
