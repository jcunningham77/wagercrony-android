package org.example.jeffcunningham.wagercrony_android;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import util.Logger;

/**
 * Created by jeffcunningham on 5/5/18.
 */

public class LoginFragment extends Fragment {
    
    @Inject
    Logger logger;

    private static final String TAG = "LoginFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((MainActivity) getActivity()).component().inject(this);

        logger.info(TAG, "onCreateView: initialized logger");
        
        
        return view;

        
    }
}
