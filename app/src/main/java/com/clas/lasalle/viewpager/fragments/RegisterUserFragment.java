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

public class RegisterUserFragment extends Fragment {

    private FragmentEventListener fragmentEventListener;
    private UserDao userDao;


    public RegisterUserFragment() {
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
        return inflater.inflate(R.layout.registration_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextFirstName = view.findViewById(R.id.editTextFirstName);
        final EditText editTextLastName = view.findViewById(R.id.editTextLastName);
        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        final EditText editTextConfirm = view.findViewById(R.id.editTextConfirm);
        Button btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = editTextFirstName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if(!(password.equals(editTextConfirm.getText().toString()))) {
                    Toast.makeText(v.getContext(), "Password not matched!", Toast.LENGTH_SHORT).show();
                } else {
                    fragmentEventListener.onUserRegister(new User(firstName, lastName,email,password));
                    Toast.makeText(v.getContext(), "Successfully Registered!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
