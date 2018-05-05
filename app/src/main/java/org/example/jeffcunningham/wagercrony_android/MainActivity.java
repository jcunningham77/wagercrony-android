package org.example.jeffcunningham.wagercrony_android;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.example.jeffcunningham.wagercrony_android.di.DaggerMainComponent;
import org.example.jeffcunningham.wagercrony_android.di.MainComponent;
import org.example.jeffcunningham.wagercrony_android.di.MainModule;

import javax.inject.Inject;

import util.Constants;
import util.Logger;

public class MainActivity extends AppCompatActivity {

    private MainComponent component;
    private static final String TAG = "MainActivity";

    @Inject
    Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        component().inject(this);

        logger.info(TAG, "onCreate: logger initialized");

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container,new LoginFragment(), Constants.LoginFragmentTag);
        transaction.addToBackStack(null);
        transaction.commit();








    }


    /** Build dagger component */
    public MainComponent component() {
        if (component == null) {
            component = DaggerMainComponent.builder()
                    .applicationComponent(((BaseApplication) getApplication()).getApplicationComponent())
                    .mainModule(new MainModule())
                    .build();
        }
        return component;
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
