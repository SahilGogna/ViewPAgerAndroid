package com.clas.lasalle.viewpager.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.clas.lasalle.viewpager.R;
import com.clas.lasalle.viewpager.connector.FragmentEventListener;
import com.clas.lasalle.viewpager.database.UserDao;
import com.clas.lasalle.viewpager.database.UserFactory;
import com.clas.lasalle.viewpager.model.User;

public class LoginUserFragment extends Fragment {

    public LoginUserFragment() {
    }

    private FragmentEventListener fragmentEventListener;
    private UserDao userDao;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextUserName = view.findViewById(R.id.editTextUserName);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        Button btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                if (userDao.contains(userName)){
                    User user = userDao.getUserByEmail(userName);
                    if (user.getPassword().equals(password)){
                        fragmentEventListener.onUserLogin(userName, password);
                        Toast.makeText(v.getContext(), "Successfully logged in!", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(v.getContext(), "Wrong password!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(v.getContext(), "Email not exist!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
