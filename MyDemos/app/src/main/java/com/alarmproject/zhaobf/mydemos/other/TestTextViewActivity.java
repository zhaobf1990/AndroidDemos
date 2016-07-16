package com.alarmproject.zhaobf.mydemos.other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.alarmproject.zhaobf.mydemos.R;

/**
 * 在一个textView上显示不同的颜色
 */
public class TestTextViewActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_text_view);

        text = (TextView) findViewById(R.id.text);

        text.setText(Html.fromHtml("赵<font color=\"red\">佰</font>枫"));
    }
}
