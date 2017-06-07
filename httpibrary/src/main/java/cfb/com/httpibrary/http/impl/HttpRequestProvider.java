package cfb.com.httpibrary.http.impl;

import java.io.IOException;
import java.net.URI;

import cfb.com.httpibrary.http.HttpMethod;
import cfb.com.httpibrary.http.HttpRequest;

/**
 * Created by fengbincao on 2017/5/24.
 */

public class HttpRequestProvider {

    private static final int USE_OK_HTTP_AS_LIBRARY = 0;
    private static final int USE_URL_CONNECTION_AS_LIBRARY = 1;

    private HttpRequestFactory mHttpRequestFactory;

    private int currentLibraryType = USE_OK_HTTP_AS_LIBRARY;

    public HttpRequestProvider() {
        switch (currentLibraryType) {
            case USE_OK_HTTP_AS_LIBRARY :
                mHttpRequestFactory = new OkHttpRequestFactory();
                break;
            case USE_URL_CONNECTION_AS_LIBRARY :
                mHttpRequestFactory = new UrlConnHttpRequestFactory();
                break;
        }
    }

    public HttpRequest getHttpRequest(URI uri, HttpMethod httpMethod) throws IOException {
        return mHttpRequestFactory.createHttpRequest(uri, httpMethod);
    }

    public HttpRequestFactory getHttpRequestFactory() {
        return mHttpRequestFactory;
    }

    public void setHttpRequestFactory(HttpRequestFactory httpRequestFactory) {
        mHttpRequestFactory = httpRequestFactory;
    }
}
