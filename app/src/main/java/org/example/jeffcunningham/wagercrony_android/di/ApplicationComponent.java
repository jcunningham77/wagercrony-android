package org.example.jeffcunningham.wagercrony_android.di;

import android.app.Application;

import org.example.jeffcunningham.wagercrony_android.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;
import util.Logger;

/**
 * Created by jeffcunningham on 5/5/18.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseApplication baseApplication);
    Application application();

    //seems like we need these available to use the @Inject at field level

    Logger logger();
}
