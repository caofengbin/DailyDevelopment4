package cfb.com.httpibrary.http.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

import cfb.com.httpibrary.http.HttpHeader;
import cfb.com.httpibrary.http.HttpRequest;
import cfb.com.httpibrary.http.HttpResponse;

/**
 * 抽象
 * Created by fengbincao on 2017/5/24.
 */

public abstract class AbstractHttpRequest implements HttpRequest {

    private static final String GZIP = "gzip";

    private HttpHeader mHeader = new HttpHeader();

    private ZipOutputStream mZip;

    private boolean executed;

    @Override
    public HttpHeader getHeaders() {
        return mHeader;
    }

    @Override
    public OutputStream getBody() {
        OutputStream body = getBodyOutputStream();
        if (isGzip()) {

            return getGzipOutStream(body);
        }
        return body;
    }

    private OutputStream getGzipOutStream(OutputStream body) {
        if (this.mZip == null) {
            this.mZip = new ZipOutputStream(body);
        }
        return mZip;
    }

    public boolean isGzip() {
        String contentEncoding = getHeaders().getContentEncoding();

        return GZIP.equals(contentEncoding);
    }

    @Override
    public HttpResponse execute() throws IOException {
        if(mZip != null) {
            mZip.close();
        }

        HttpResponse response = executeInternal(mHeader);

        executed = true;
        return response;
    }

    protected abstract HttpResponse executeInternal(HttpHeader mHeader) throws IOException;

    protected abstract OutputStream getBodyOutputStream();






}
