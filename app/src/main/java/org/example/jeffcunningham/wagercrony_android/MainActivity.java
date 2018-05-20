package org.example.jeffcunningham.wagercrony_android;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.example.jeffcunningham.wagercrony_android.di.DaggerMainComponent;
import org.example.jeffcunningham.wagercrony_android.di.MainComponent;
import org.example.jeffcunningham.wagercrony_android.di.MainModule;
import org.example.jeffcunningham.wagercrony_android.fragments.AboutFragment;
import org.example.jeffcunningham.wagercrony_android.fragments.AdminFragment;
import org.example.jeffcunningham.wagercrony_android.fragments.SignInFragment;
import org.example.jeffcunningham.wagercrony_android.fragments.SignOutFragment;

import javax.inject.Inject;

import util.Constants;
import util.Logger;

public class MainActivity extends AppCompatActivity {

    private MainComponent component;
    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;

    @Inject
    Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        Drawable menuIcon = getResources().getDrawable(R.drawable.ic_menu_black_24dp);
        int color = Color.parseColor("#ffffff");
        menuIcon.setTint(color);
        actionbar.setHomeAsUpIndicator(menuIcon);
        actionbar.setDisplayShowTitleEnabled(false);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {


                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped

                        switch (menuItem.getItemId()){
                            case R.id.nav_signin:
                                navigateToFragment(Constants.SignInFragmentTag);
                                break;
                            case R.id.nav_signout:
                                navigateToFragment(Constants.SignOutFragmentTag);
                                break;
                            case R.id.nav_admin:
                                navigateToFragment(Constants.AdminFragmentTag);
                                break;
                            case R.id.nav_about:
                                navigateToFragment(Constants.AboutFragmentTag);
                                break;
                        }

                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });




        component().inject(this);

        logger.info(TAG, "onCreate: logger initialized");

        navigateToFragment(Constants.SignInFragmentTag);

    }

    private void navigateToFragment(String destination){

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (destination){
            case Constants.SignInFragmentTag:{
                transaction.replace(R.id.fragment_container,new SignInFragment(), Constants.SignInFragmentTag);
                break;
            }
            case Constants.SignOutFragmentTag:{
                transaction.replace(R.id.fragment_container,new SignOutFragment(), Constants.SignInFragmentTag);
                break;
            }
            case Constants.AboutFragmentTag:{
                transaction.replace(R.id.fragment_container,new AboutFragment(), Constants.SignInFragmentTag);
                break;
            }
            case Constants.AdminFragmentTag:{
                transaction.replace(R.id.fragment_container,new AdminFragment(), Constants.SignInFragmentTag);
                break;
            }
        }


        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
//        getMenuInflater().inflate(R.menu.drawer_view, menu);
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
