package org.example.jeffcunningham.wagercrony_android.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import util.Logger;
import util.LoggerImpl;

/**
 * Created by jeffcunningham on 5/5/18.
 */
@Module
public class ApplicationModule {

    private final Application application;

    /**
     * Expose the application to the graph.
     */
    @Provides
    @Singleton
    Application application() {
        return application;
    }

    public ApplicationModule(Application application) {
        this.application = application;

    }

    @Provides
    Logger provideLogger(LoggerImpl impl){
        return impl;
    }

}
