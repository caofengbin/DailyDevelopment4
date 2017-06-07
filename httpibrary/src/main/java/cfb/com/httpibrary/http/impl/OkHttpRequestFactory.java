package cfb.com.httpibrary.http.impl;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import cfb.com.httpibrary.http.HttpMethod;
import cfb.com.httpibrary.http.HttpRequest;
import okhttp3.OkHttpClient;

/**
 * Created by fengbincao on 2017/5/24.
 */

public class OkHttpRequestFactory implements HttpRequestFactory {

    private OkHttpClient mClient;

    public OkHttpRequestFactory() {
        this.mClient = new OkHttpClient();
    }

    public OkHttpRequestFactory(OkHttpClient client) {
        this.mClient = client;
    }

    /**
     * Sets the read timeout
     * @param readTimeOut
     */
    public void setReadTimeOut(int readTimeOut) {
        this.mClient = mClient.newBuilder().
                readTimeout(readTimeOut, TimeUnit.MILLISECONDS).
                build();
    }

    /**
     * Sets the write timeout for new connections.
     * @param writeTimeOut
     */
    public void setWriteTimeOut(int writeTimeOut) {
        this.mClient = mClient.newBuilder().
                writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS).
                build();
    }

    /**
     * Sets the default connect timeout
     * @param connectionTimeOut
     */
    public void setConnectionTimeOut(int connectionTimeOut) {
        this.mClient = mClient.newBuilder().
                connectTimeout(connectionTimeOut, TimeUnit.MILLISECONDS).
                build();
    }

    @Override
    public HttpRequest createHttpRequest(URI uri, HttpMethod method) throws IOException {
        return new OkHttpRequest(mClient, method, uri.toString());
    }
}
