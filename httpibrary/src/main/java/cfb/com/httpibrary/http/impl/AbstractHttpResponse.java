package cfb.com.httpibrary.http.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import cfb.com.httpibrary.http.HttpResponse;

/**
 * Created by fengbincao on 2017/5/24.
 */

public abstract class AbstractHttpResponse implements HttpResponse {

    private static final String GZIP = "gzip";

    private InputStream mGzipInputStream;

    @Override
    public void close() {
        if (mGzipInputStream != null) {
            try {
                mGzipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeInternal();
    }

    @Override
    public InputStream getBody() throws IOException {

        InputStream body = getBodyInternal();

        if (isGzip()) {
            return getBodyGzip(body);
        }

        return body;
    }

    protected abstract InputStream getBodyInternal() throws IOException;

    protected abstract void closeInternal();

    private InputStream getBodyGzip(InputStream body) throws IOException {

        if (this.mGzipInputStream == null) {
            this.mGzipInputStream = new GZIPInputStream(body);
        }
        return mGzipInputStream;

    }

    /**
     * 判断是否开启了gzip压缩
     * @return
     */
    private boolean isGzip() {
        String contentEncoding = getHeaders().getContentEncoding();

        return GZIP.equals(contentEncoding);

    }

}
