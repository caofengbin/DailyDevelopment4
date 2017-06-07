package cfb.com.httpibrary.http.impl;

import java.io.IOException;
import java.net.URI;

import cfb.com.httpibrary.http.HttpMethod;
import cfb.com.httpibrary.http.HttpRequest;

/**
 * 生成HttpRequest的工厂
 * Created by fengbincao on 2017/5/24.
 */

public interface HttpRequestFactory {

    HttpRequest createHttpRequest(URI uri, HttpMethod method) throws IOException;
}
