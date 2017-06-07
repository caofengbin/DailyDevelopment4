package cfb.com.httpibrary.http;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fengbincao on 2017/5/24.
 */

public interface HttpResponse extends Header, Closeable {

    HttpStatus getStatus();

    String getStatusMsg();

    InputStream getBody() throws IOException;

    void close();

    long getContentLength();
}
