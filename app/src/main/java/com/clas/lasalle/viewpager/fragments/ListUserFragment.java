package com.clas.lasalle.viewpager.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.clas.lasalle.viewpager.R;
import com.clas.lasalle.viewpager.connector.FragmentEventListener;
import com.clas.lasalle.viewpager.database.UserDao;
import com.clas.lasalle.viewpager.database.UserFactory;
import com.clas.lasalle.viewpager.model.User;

public class ListUserFragment extends ListFragment {

    private UserDao userDao;
    private FragmentEventListener fragmentEventListener;


    public ListUserFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentEventListener = (FragmentEventListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDao = UserFactory.getUserDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<User> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1);
        adapter.addAll(userDao.getAll());
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        fragmentEventListener.onClickedUser(userDao.getUserByPosition(position));
    }
}
