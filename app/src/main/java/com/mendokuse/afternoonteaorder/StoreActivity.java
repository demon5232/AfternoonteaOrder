package com.mendokuse.afternoonteaorder;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class StoreActivity extends AppCompatActivity {
    private FragmentManager mainViewManager;
    private FragmentTransaction mainViewTransaction;
    private Fragment nowFragment;
    private StoreListFragment storeListFragment;
    private MenuFragment menuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        //Fragment初始化
        mainViewManager = getSupportFragmentManager();
        mainViewTransaction = mainViewManager.beginTransaction();
        storeListFragment = new StoreListFragment();
        mainViewTransaction.add(R.id.content_view, storeListFragment).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    public void tabeItemOnclick(View view){
        Fragment changeFragment = new Fragment();
        //storeListFragment.isAdded()
        switch (view.getId()) {
            case R.id.tabItem_storelist:
                changeFragment = storeListFragment;
                break;
            case R.id.tabItem_menu:
                changeFragment = menuFragment;
                break;
        }
        if( nowFragment != changeFragment){
            if (!changeFragment.isAdded()) {
                mainViewTransaction.hide(nowFragment)
                        .add(R.id.content_view, changeFragment).commit();
            } else {
                mainViewTransaction.hide(nowFragment)
                        .show(changeFragment).commit();
            }
            nowFragment = changeFragment;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
