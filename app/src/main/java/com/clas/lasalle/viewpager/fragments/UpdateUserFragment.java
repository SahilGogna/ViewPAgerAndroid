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

public class UpdateUserFragment extends Fragment {

    private FragmentEventListener fragmentEventListener;
    private UserDao userDao;


    public UpdateUserFragment() {
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
        return inflater.inflate(R.layout.update_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editTextEmailForUpdate = view.findViewById(R.id.editTextEmailForUpdate);
        final EditText editTextFirstNameU = view.findViewById(R.id.editTextFirstNameU);
        final EditText editTextLastNameU = view.findViewById(R.id.editTextLastNameU);
        final EditText editTextEmailU = view.findViewById(R.id.editTextEmailU);
        final EditText editTextPasswordU = view.findViewById(R.id.editTextPasswordU);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        Button btnGetInfo = view.findViewById(R.id.btnGetInfo);
        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldEmail = editTextEmailForUpdate.getText().toString();
                if (userDao.contains(oldEmail)) {
                    User user = userDao.getUserByEmail(oldEmail);
                    editTextFirstNameU.setText(user.getFirstName());
                    editTextLastNameU.setText(user.getLastName());
                    editTextEmailU.setText(user.getUserName());
                    editTextPasswordU.setText(user.getPassword());
                }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldEmail = editTextEmailForUpdate.getText().toString();
                String firstName = editTextFirstNameU.getText().toString();
                String lastName = editTextLastNameU.getText().toString();
                String newEmail = editTextEmailU.getText().toString();
                String newPassword = editTextPasswordU.getText().toString();

                fragmentEventListener.onUserUpdated(oldEmail, new User(firstName, lastName, newEmail, newPassword));

                Toast.makeText(v.getContext(), "Information Updated!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
