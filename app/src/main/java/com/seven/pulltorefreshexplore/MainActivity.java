package com.seven.pulltorefreshexplore;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class MainActivity extends ActionBarActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {

    private PullToRefreshListView mPullToRefreshListView;
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
        mAdapter = new TestAdapter();
        mPullToRefreshListView.setAdapter(mAdapter);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshListView.setOnRefreshListener(this);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        mAdapter.mNames.clear();

        queryData();
    }

    private void queryData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mAdapter.mNames.add(String.valueOf(System.currentTimeMillis()));
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();

                        mPullToRefreshListView.onRefreshComplete();
                    }
                });
            }
        });
        thread.start();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        queryData();
    }
}
