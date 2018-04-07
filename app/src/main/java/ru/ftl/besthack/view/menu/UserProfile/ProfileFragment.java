package ru.ftl.besthack.view.menu.UserProfile;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import ru.ftl.besthack.R;
import ru.ftl.besthack.data.auth.UserModel;
import ru.ftl.besthack.view.menu.ui.UserMenuActivity;

/**
 * Created by john on 05.04.18.
 */

public class ProfileFragment extends Fragment {

    private UserModel userModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        userModel = Objects.requireNonNull(bundle).getParcelable(UserMenuActivity.getUSERMODEL());

        final LinearLayout profileView = (LinearLayout) inflater.inflate(R.layout.fragment_profile, container, false);

        if (userModel == null) {
            Log.w("Parcelable is null", "UserModel from bundle is null");
            return profileView;
        }
        String name = userModel.getName();
        TextView text_name = profileView.findViewById(R.id.UserName);
        text_name.setText(name);

        String surname = userModel.getSurname();
        TextView text_surname = profileView.findViewById(R.id.UserSurname);
        text_surname.setText(surname);

        String middlename = userModel.getMiddlename();
        TextView text_middlename = profileView.findViewById(R.id.UserMiddlename);
        text_middlename.setText(middlename);

        String group = userModel.getGroup();
        TextView text_group = profileView.findViewById(R.id.UserGroup);
        text_group.setText(group);

        String about = userModel.getAbout();
        TextView text_about = profileView.findViewById(R.id.UserAbout);
        text_about.setText(about);

        String imageUrl = userModel.getImageUrl();
        ImageView imageView = profileView.findViewById(R.id.UserImage);
        imageView.setImageURI(Uri.parse(imageUrl));

        return profileView;
    }

    public UserModel getUser(){
        return userModel;
    };
}
