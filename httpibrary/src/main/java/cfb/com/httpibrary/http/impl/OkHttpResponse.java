package cfb.com.httpibrary.http.impl;

import java.io.IOException;
import java.io.InputStream;

import cfb.com.httpibrary.http.HttpHeader;
import cfb.com.httpibrary.http.HttpStatus;
import okhttp3.Response;

/**
 * Created by fengbincao on 2017/5/24.
 */

public class OkHttpResponse extends AbstractHttpResponse {

    private Response mResponse;

    private HttpHeader mHeaders;

    public OkHttpResponse(Response response) {
        this.mResponse = response;
    }

    @Override
    protected InputStream getBodyInternal() throws IOException {
        return mResponse.body().byteStream();
    }

    @Override
    protected void closeInternal() {
        mResponse.body().close();
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.getValue(mResponse.code());
    }

    @Override
    public String getStatusMsg() {
        return mResponse.message();
    }

    @Override
    public long getContentLength() {
        return mResponse.body().contentLength();
    }

    @Override
    public HttpHeader getHeaders() {

        if(mHeaders == null) {
            mHeaders = new HttpHeader();
        }

        for(String name : mResponse.headers().names()) {
            mHeaders.set(name, mResponse.headers().get(name));
        }

        return mHeaders;
    }
}
