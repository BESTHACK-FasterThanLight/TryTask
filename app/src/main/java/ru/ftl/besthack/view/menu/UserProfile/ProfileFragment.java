package ru.ftl.besthack.view.menu.UserProfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

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
        UserModel user = (UserModel) getArguments().getParcelable(UserListActivity.USERMODEL);
        View ProfileView = inflater.inflate(R.layout.fragment_profile, container, false);
        String name = user.getName();
        TextView text_name = ProfileView.findViewById(R.id.UserName);
        text_name.setText(name);

        String surname = user.getSurname();
        TextView text_surname = ProfileView.findViewById(R.id.UserSurname);
        text_surname.setText(surname);

        String middlename = user.getMiddlename();
        TextView text_middlename = ProfileView.findViewById(R.id.UserMiddlename);
        text_middlename.setText(middlename);

        String group = user.getGroup();
        TextView text_group = ProfileView.findViewById(R.id.UserGroup);
        text_group.setText(group);

        String about = user.getAbout();
        TextView text_about = ProfileView.findViewById(R.id.UserAbout);
        text_about.setText(about);

        String imageUrl = user.getImageUrl();
        ImageView imageView = (ImageView) ProfileView.findViewById(R.id.UserImage);
        imageView.setImageURI(URI.parse(imageUrl));

        return ProfileView;
    }

    public UserModel getUser(){
        return userModel;
    };
}
