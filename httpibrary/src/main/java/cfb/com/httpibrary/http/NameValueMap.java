package cfb.com.httpibrary.http;

import java.util.Map;

/**
 * 请求头部相关键值对
 * Created by fengbincao on 2017/5/24.
 */

public interface NameValueMap<K, V> extends Map<K, V> {

    String get(String key);

    void set(String key, String value);

    void setAll(Map<String, String> map);
}
