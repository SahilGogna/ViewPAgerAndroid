package com.clas.lasalle.viewpager.pager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.clas.lasalle.viewpager.fragments.ListUserFragment;
import com.clas.lasalle.viewpager.fragments.LoginUserFragment;
import com.clas.lasalle.viewpager.fragments.RegisterUserFragment;
import com.clas.lasalle.viewpager.fragments.UpdateUserFragment;

public class ViewPagerAdaptor extends FragmentPagerAdapter {

    public ViewPagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                RegisterUserFragment registerUserFragment = new RegisterUserFragment();
                return registerUserFragment;
            case 1:
                LoginUserFragment loginUserFragment = new LoginUserFragment();
                return loginUserFragment;
            case 2:
                UpdateUserFragment updateUserFragment = new UpdateUserFragment();
                return updateUserFragment;
            case 3:
                ListUserFragment listUserFragment = new ListUserFragment();
                return listUserFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Register";
            case 1:
                return "Login";
            case 2:
                return "Update";
            case 3:
                return "List User";
            default:
                return "NA";
        }
    }
}
