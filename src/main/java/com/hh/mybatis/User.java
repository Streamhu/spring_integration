package com.hh.mybatis;

/**
 * TODO
 *
 * @author huhui
 * @since 2018/8/10 10:53
 */
public class User {

    /**
     * 用户表id
     * */
    private Integer id;

    /**
     *  用户姓名
     * */
    private String name;

    /**
     * 用户地址
     * */
    private String address;

    /***
     * 所在城市
     */
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
