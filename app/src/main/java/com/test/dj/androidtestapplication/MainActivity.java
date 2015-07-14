package com.test.dj.androidtestapplication;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new MyAdapter());
        View footview = View.inflate(this, R.layout.footview, null);
        list.addFooterView(footview);
        mTextView = (TextView) findViewById(R.id.text);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
        String text = getIntent().getStringExtra("text");
        if(text!=null){
            mTextView.setText(text);
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.piece_moment_content, null);
//                convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
            }
//            TextView item = (TextView) convertView.findViewById(R.id.item_text);
//            item.setText("item " + position);
            return convertView;
        }
    }

    static int x;
    public void showNotification() {

        Intent startIntent = new Intent(this, MainActivity.class);
//        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startIntent.putExtra("text",x+++" 次启动");
        PendingIntent intent = PendingIntent.getActivity(this, 0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String strMessage = "msg.messageContent";
        String strTickerMsg = "strCompositeName strMessage)";
        String strCompositeName = "msg.displayName";

        if (null != strTickerMsg) {
            // notification
            NotificationManager notiManager = (NotificationManager)
                    this.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification note = new Notification(R.drawable.notification_template_icon_bg, strTickerMsg,
                    System.currentTimeMillis());
            note.setLatestEventInfo(this, strCompositeName, strMessage, intent);
            notiManager.notify(0, note);

        }
    }
}
