package cfb.com.httpibrary.http;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 通用HTTP请求首部
 * Created by fengbincao on 2017/5/24.
 */

public class HttpHeader implements NameValueMap<String, String>{

    // HTTP常用头部定义

    /**
     * Accept首部字段可通知服务器，用户代理能够处理的媒体类型及媒体类型的相对优先级
     * 可使用type/subtype这种形式，一次指定多种媒体类型。
     */
    public final static String ACCEPT = "Accept";

    /**
     * Pragma是HTTP/1.1之前版本的历史遗留字段，仅作为与HTTP/1.1的向后兼容而定义。
     */
    public final static String PRAGMA = "Pragma";

    /**
     *
     */
    public final static String PROXY_CONNECTION = "Proxy-Connection";
    public final static String USER_AGENT = "User-Agent";
    public final static String ACCEPT_ENCODING = "accept-encoding";
    public final static String CACHE_CONTROL = "Cache-Control";
    public final static String CONTENT_ENCODING = "Content-Encoding";

    /**
     * 两个作用
     * (1) 控制不再转发给代理的首部字段；
     * (2) 管理持有连接
     */
    public final static String CONNECTION = "Connection";

    /**
     * 表明了实体主体部分的大小(以字节为单位)
     */
    public final static String CONTENT_LENGTH = "Content-length";

    /**
     * 说明了实体主体内对象的媒体类型,
     * 和首部字段Accept一样，字段值用type/subtype形式赋值。
     */
    public static final String CONTENT_TYPE = "Content-Type";

    private Map<String, String> mMap = new HashMap<>();

    public String getAccept() {
        return get(ACCEPT);
    }

    public void setAccept(String value) {
        set(ACCEPT, value);
    }

    public String getPragma() {
        return get(PRAGMA);
    }

    public void setPragma(String value) {
        set(PRAGMA, value);
    }

    public String getUserAgent() {
        return get(USER_AGENT);
    }

    public void setUserAgent(String value) {
        set(USER_AGENT, value);
    }

    public String getProxyConnection() {
        return get(PROXY_CONNECTION);
    }

    public void setProxyConnection(String value) {
        set(PROXY_CONNECTION, value);
    }

    public String getAcceptEncoding() {
        return get(ACCEPT_ENCODING);
    }

    public void setAcceptEncoding(String value) {
        set(ACCEPT_ENCODING, value);
    }

    public String getCacheControl() {
        return get(CACHE_CONTROL);
    }

    public void setCacheControl(String value) {
        set(CACHE_CONTROL, value);
    }

    public String getContentEncoding() {
        return get(CONTENT_ENCODING);
    }

    public void setContentEncoding(String value) {
        set(CONTENT_ENCODING, value);
    }

    public String getConnection() {
        return get(CONNECTION);
    }

    public void setConnection(String value) {
        set(CONNECTION, value);
    }

    public String getContentLength() {
        return get(CONTENT_LENGTH);
    }

    public void setContentLength(String value) {
        set(CONTENT_LENGTH, value);
    }

    public String getContentType() {
        return get(CONTENT_TYPE);
    }

    public void setContentType(String value) {
        set(CONTENT_TYPE, value);
    }

    @Override
    public String get(String key) {
        return mMap.get(key);
    }

    @Override
    public void set(String key, String value) {
        mMap.put(key, value);
    }

    @Override
    public void setAll(Map<String, String> map) {
        mMap.putAll(map);
    }

    @Override
    public int size() {
        return mMap.size();
    }

    @Override
    public boolean isEmpty() {
        return mMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return mMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return mMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return mMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        return mMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return mMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        mMap.putAll(m);
    }

    @Override
    public void clear() {
        mMap.clear();
    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return mMap.keySet();
    }

    @NonNull
    @Override
    public Collection<String> values() {
        return mMap.values();
    }

    @NonNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return mMap.entrySet();
    }
}