package com.example.hongweiyu.demo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ChatWindow.OnChatChoiceCallBack {

    public static final String TAG = "MainActivity";

    private ActivityManager mActivityManager;
    private Window window;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    private ChatWindow mChatWindow;

    private ListView listview;
    private String[] data = {"101", "102", "103", "104", "105", "106", "107", "108", "201", "202",
            "203", "204", "205", "206"};

    private int mPostion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        mToolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);//设置Toolbar的 导航图标

        /* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过Activity的onOptionsItemSelected回调方法来处理 */
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "action_search", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        listview = ((ListView) findViewById(R.id.listview));
        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, data));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPostion = position;
                showWindow();
            }
        });

        //onScrollviewListener
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {}

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                listview.setItemChecked(mPostion, false);
            }
        });
    }

    private void showWindow() {
        if(mChatWindow == null){
            mChatWindow = new ChatWindow(this);
            mChatWindow.setOnChatChoiceCallBack(this);
        }
        mChatWindow.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onChatChoiceCallBack(ChatWindow.ChAT_TYPE chAT_type) {
        // TODO: 15/12/31 选择的聊天类型
        Toast.makeText(MainActivity.this, chAT_type+"", Toast.LENGTH_SHORT).show();
    }
}
