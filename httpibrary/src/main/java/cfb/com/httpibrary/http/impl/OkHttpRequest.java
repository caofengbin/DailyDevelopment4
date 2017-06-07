package cfb.com.httpibrary.http.impl;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import cfb.com.httpibrary.http.HttpHeader;
import cfb.com.httpibrary.http.HttpMethod;
import cfb.com.httpibrary.http.HttpResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by fengbincao on 2017/5/24.
 */

public class OkHttpRequest extends BufferHttpRequest {

    private OkHttpClient mClient;

    private HttpMethod mMethod;

    private String mUrl;

    public OkHttpRequest(OkHttpClient client, HttpMethod method, String url) {
        this.mClient = client;
        this.mMethod = method;
        this.mUrl = url;
    }

    @Override
    protected HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException {
        boolean isBody = (mMethod == HttpMethod.POST) ;
        RequestBody requestBody = null;

        if(isBody) {
            // POST常见的有四种，这里后续需要完善
            requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                    data);
        }

        Request.Builder builder = new Request.Builder()
                .url(mUrl)
                .method(mMethod.name(),requestBody);

        for(Map.Entry<String, String> entry : header.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }

        // OkHttp的同步请求接口
        Response response = mClient.newCall(builder.build()).execute();

        return new OkHttpResponse(response);
    }

    @Override
    public HttpMethod getMethod() {
        return mMethod;
    }

    @Override
    public URI getUri() {
        return URI.create(mUrl);
    }
}
