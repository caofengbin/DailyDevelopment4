package cfb.com.httpibrary.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by fengbincao on 2017/5/24.
 */

public interface HttpRequest extends Header {

    HttpMethod getMethod();

    URI getUri();

    OutputStream getBody();

    HttpResponse execute() throws IOException;
}
