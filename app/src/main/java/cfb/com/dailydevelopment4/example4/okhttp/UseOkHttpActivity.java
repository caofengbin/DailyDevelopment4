package cfb.com.dailydevelopment4.example4.okhttp;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cfb.com.dailydevelopment4.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 演示OkHttp的基本使用规范
 */
public class UseOkHttpActivity extends AppCompatActivity {

    private static final String TAG = "okhttp";

    // 第一步；
    OkHttpClient okHttpClient = new OkHttpClient();

    @BindView(R.id.get_button)
    public Button mGetButton;

    @BindView(R.id.post_button)
    public Button mPostButton;

    @BindView(R.id.post_string_button)
    public Button mPostStringButton;

    @BindView(R.id.download_file)
    public Button mDownLoadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_ok_http);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.get_button)
    public void makeGetRequest() {
        // 第一步；

        // 2.构造request
        Request.Builder builder = new Request.Builder();

        final Request request = builder.get().url("https://api.douban.com/v2/book/search?q=%E7%A8%8B%E5%BA%8F%E5%91%98%E4%BF%AE%E7%82%BC%E4%B9%8B%E9%81%93--%E4%BB%8E%E5%B0%8F%E5%B7%A5%E5%88%B0%E4%B8%93%E5%AE%B6&fields=id,title,url").build();

        // 3.将request封装为call,类似runnable,非常重要的一个步骤
        Call call = okHttpClient.newCall(request);

        // 4.请求
        //call.execute();

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse");
                String result = response.body().string();
                Log.e(TAG, result);
                boolean isUIThread = Looper.myLooper() == Looper.getMainLooper();
                if (isUIThread) {
                    Log.e(TAG, "回调onResponse在UI线程执行");
                } else {
                    Log.e(TAG, "回调onResponse在子线程执行");
                }
            }
        });
    }

    @OnClick(R.id.post_button)
    public void makePostRequest() {

        // 2.构造request
        Request.Builder builder = new Request.Builder();

        FormBody body = new FormBody.Builder()
                .add("your_param_1", "your_value_1")
                .add("your_param_2", "your_value_2")
                .build();

        final Request request = builder.url("").post(body).build();

        // 3.将request封装为call,类似runnable,非常重要的一个步骤
        Call call = okHttpClient.newCall(request);

        // 4.请求
        //call.execute();

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse");
            }
        });
    }

    @OnClick(R.id.post_string_button)
    public void makePostStringRequest() {

        // 2.构造request
        Request.Builder builder = new Request.Builder();

        // 传递一个post类型，第一个为Mine-Type,d
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;chaset=utf-8"),"json string");

        final Request request = builder.url("").post(requestBody).build();

        // 3.将request封装为call,类似runnable,非常重要的一个步骤
        Call call = okHttpClient.newCall(request);

        // 4.请求
        //call.execute();

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse");
            }
        });
    }

    @OnClick(R.id.download_file)
    public void makeDownLoadRequest() {

    }
}
