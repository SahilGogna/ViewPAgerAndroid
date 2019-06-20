package com.clas.lasalle.viewpager.database;

public class UserFactory {

    private static UserDao userDao = new UserDao();

    public static UserDao getUserDao(){
        return userDao;
    }

}
