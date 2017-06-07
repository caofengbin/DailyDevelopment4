package cfb.com.httpibrary.http.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import cfb.com.httpibrary.http.HttpHeader;
import cfb.com.httpibrary.http.HttpResponse;

/**
 * Created by fengbincao on 2017/5/24.
 */

public abstract class BufferHttpRequest extends AbstractHttpRequest {

    private ByteArrayOutputStream mByteArray = new ByteArrayOutputStream();

    protected OutputStream getBodyOutputStream() {
        return mByteArray;
    }

    @Override
    protected HttpResponse executeInternal(HttpHeader mHeader) throws IOException {
        // post数据部分
        byte[] data = mByteArray.toByteArray();
        return executeInternal(mHeader, data);
    }

    protected abstract HttpResponse executeInternal(HttpHeader header, byte[] data) throws IOException;
}
