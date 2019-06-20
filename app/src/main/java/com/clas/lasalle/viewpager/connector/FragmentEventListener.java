package com.clas.lasalle.viewpager.connector;

import com.clas.lasalle.viewpager.model.User;

public interface FragmentEventListener {

    void onUserRegister(User user);
    void onUserLogin(String email, String password);
    void onUserUpdated(String oldEmail, User newUser);
    void onClickedUser(User user);

}
