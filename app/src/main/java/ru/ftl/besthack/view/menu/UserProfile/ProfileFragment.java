package ru.ftl.besthack.view.menu.UserProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.ftl.besthack.R;
import ru.ftl.besthack.data.auth.UserModel;

/**
 * Created by john on 05.04.18.
 */

public class ProfileFragment extends Fragment {

    private UserModel userModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String name = bundle.getString("UserName");
        View ProfileView = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView text_name = ProfileView.findViewById(R.id.UserName);
        text_name.setText("Имя: " + name);
        return ProfileView;
    }

    public UserModel getUser(){
        return userModel;
    };
}
