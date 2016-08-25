package com.alarmproject.zhaobf.mydemos.ListView;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alarmproject.zhaobf.mydemos.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class EmptyActivity extends Activity {

    private PullToRefreshListView mListView;
    private TextView tv;
        private String[] mData = new String[]{"avc", "def", "wew"};
    private MyAdapter myAdapter;
//    private String[] mData = new String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);


        mListView = (PullToRefreshListView) findViewById(R.id.listview);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                MyTask myTask = new MyTask();
                myTask.execute();
            }
        });
        tv = (TextView) findViewById(R.id.text);
        mListView.setEmptyView(tv);
        mListView.setAdapter(new EmptyAdapter(EmptyActivity.this));
        mListView.setRefreshing();

        Log.d("PullToRefresh", "demo");
//        mListView.demo();
//        MyAdapter myAdapter = new MyAdapter();
//        mListView.setAdapter(myAdapter);
//        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
//                MyTask myTask = new MyTask();
//                myTask.execute();
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
//                MyTask myTask = new MyTask();
//                myTask.execute();
//            }
//        });



//
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListView.setRefreshing(false);
//            }
//        });
//        mListView.setRefreshing();
//        tv.performClick();

    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
              myAdapter = new MyAdapter(EmptyActivity.this);
            mListView.setAdapter(myAdapter);
            mListView.onRefreshComplete();
            myAdapter.notifyDataSetChanged();
        }
    }

    class MyAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;

        public MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return mData.length;
        }


        @Override
        public String getItem(int position) {
            return mData[position];
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listview_sample_item, parent, false);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.text2);
            textView.setText(getItem(position));
            return convertView;
        }
    }

    class EmptyAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        private String[] data = new String[]{""};

        public EmptyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return data.length;
        }


        @Override
        public String getItem(int position) {
            return mData[position];
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.listview_empty, parent, false);
            }
            return convertView;
        }
    }
}
