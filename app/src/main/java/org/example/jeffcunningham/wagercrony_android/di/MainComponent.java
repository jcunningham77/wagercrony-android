package org.example.jeffcunningham.wagercrony_android.di;

import org.example.jeffcunningham.wagercrony_android.fragments.AboutFragment;
import org.example.jeffcunningham.wagercrony_android.fragments.AdminFragment;
import org.example.jeffcunningham.wagercrony_android.MainActivity;
import org.example.jeffcunningham.wagercrony_android.fragments.SignInFragment;
import org.example.jeffcunningham.wagercrony_android.fragments.SignOutFragment;
import org.example.jeffcunningham.wagercrony_android.di.annotations.PerActivity;

import dagger.Component;

/**
 * Created by jeffcunningham on 5/5/18.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);
    void inject(SignInFragment fragment);
    void inject(SignOutFragment fragment);
    void inject(AdminFragment fragment);
    void inject(AboutFragment fragment);
}
