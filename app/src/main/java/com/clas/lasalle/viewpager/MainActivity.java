package com.clas.lasalle.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.clas.lasalle.viewpager.connector.FragmentEventListener;
import com.clas.lasalle.viewpager.database.UserDao;
import com.clas.lasalle.viewpager.database.UserFactory;
import com.clas.lasalle.viewpager.model.User;
import com.clas.lasalle.viewpager.pager.ViewPagerAdaptor;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements FragmentEventListener {

    private UserDao userDao;
    private ViewPager viewPager;
    private ViewPagerAdaptor viewPagerAdaptor;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDao = UserFactory.getUserDao();
        userDao.addUser(new User("Admin", "admin", "admin@admin.com", "123456"));

        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager());

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdaptor);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onUserRegister(User user) {
        userDao.addUser(user);
        viewPagerAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onUserLogin(String email, String password) {
        userDao.loginUser(email, password);
    }

    @Override
    public void onUserUpdated(String oldEmail, User newUser) {
        userDao.updateUser(userDao.getUserByEmail(oldEmail), newUser);
        viewPagerAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onClickedUser(User user) {

    }
}
