package zhaobf.okhttp3demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://localhost:8080/WebHttp/servlet/TestServlet";
    private Button btnAsyncGet;
    private TextView text;
    private Button btnAsyncPost;
    private Button btnSyncGet;
    private Button btnSyncPost;
    OkHttpClient mOkHttpClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOkHttpClient = new OkHttpClient();
//        mOkHttpClient = new OkHttpClient.Builder().readTimeout(2, TimeUnit.MILLISECONDS).build();  //设置读取的超时时间
        btnAsyncGet = (Button) findViewById(R.id.btnAsyncGet);

        btnAsyncGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAsynHttp();
            }
        });

        btnSyncGet = (Button) findViewById(R.id.btnSyncGet);
        btnSyncGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(MainActivity.class.getSimpleName(), "线程Id=" + Thread.currentThread().getId());
                try {
                    getSyncHttp();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnAsyncPost = (Button) findViewById(R.id.btnAsyncPost);
        btnAsyncPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postAsynHttp();
            }
        });
        text = (TextView) findViewById(R.id.text);

        btnSyncPost = (Button) findViewById(R.id.btnSyncPost);
        btnSyncPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    postSyncHttp();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getSyncHttp() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(MainActivity.class.getSimpleName(), "handler线程Id=" + Thread.currentThread().getId());
                Request request = new Request.Builder().url(URL).build();

                Response response = null;
                String body = null;
                try {
                    response = mOkHttpClient.newCall(request).execute();
                    body = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final String finalBody = body;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(finalBody);
                    }
                });

            }
        }).start();

    }

    private void getAsynHttp() {
        Log.d(MainActivity.class.getSimpleName(), "线程Id=" + Thread.currentThread().getId());

        Request.Builder requestBuilder = new Request.Builder().url(URL);
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mcall = mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(MainActivity.class.getSimpleName(), "异常", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(MainActivity.class.getSimpleName(), "onResponse线程Id=" + Thread.currentThread().getId());

                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("wangshu", "cache---" + str);
                } else {
                    final String body = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(body);
                            Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    String str = response.networkResponse().toString();
                    Log.i("wangshu", "network---" + str);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }


    private void postAsynHttp() {

        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(str);
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

    private void postSyncHttp() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody frombody = new FormBody.Builder().add("size", "10").build();
                Request request = new Request.Builder().url(URL).post(frombody).build();

                String body = null;
                try {
                    Response response = mOkHttpClient.newCall(request).execute();
                    body = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final String finalBody = body;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(finalBody);
                    }
                });

            }
        }).start();

    }

}
