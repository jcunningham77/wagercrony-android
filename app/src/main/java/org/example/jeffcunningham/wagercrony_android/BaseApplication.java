package org.example.jeffcunningham.wagercrony_android;

import android.app.Application;

import org.example.jeffcunningham.wagercrony_android.di.ApplicationComponent;
import org.example.jeffcunningham.wagercrony_android.di.ApplicationModule;
import org.example.jeffcunningham.wagercrony_android.di.DaggerApplicationComponent;

import javax.inject.Inject;

import util.Logger;


/**
 * Created by jeffcunningham on 12/8/16.
 */

public class BaseApplication extends Application {

    @Inject
    Logger logger;

    private ApplicationComponent applicationComponent;

    private static final String TAG = "BaseApplication";
    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);

        logger.info(TAG, "onCreate: logger initialized");
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
