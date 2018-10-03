package com.example.maoyi.pole;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;


    private homeFragment fragment1;
    private hotFragment fragment2;
    private personalFragment fragment3;
    private Fragment[] fragments;
    private int lastShowFragment = 0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0) {
                        switchFrament(lastShowFragment, 0);
                        lastShowFragment = 0;
                    }
                    return true;

                case R.id.navigation_dashboard:
                    if (lastShowFragment != 1) {
                        switchFrament(lastShowFragment, 1);
                        lastShowFragment = 1;
                    }
                    return true;

                case R.id.navigation_notifications:
                if (lastShowFragment != 2) {
                    switchFrament(lastShowFragment, 2);
                    lastShowFragment = 2;
                }
                return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragments();
    }

    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.content, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragments() {
        fragment1 = new homeFragment();
        fragment2 = new hotFragment();
        fragment3 = new personalFragment();
        fragments = new Fragment[]{fragment1, fragment2, fragment3};
        lastShowFragment = 0;
        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment1).show(fragment1).commit();
    }
}
