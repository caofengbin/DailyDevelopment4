package cfb.com.httpibrary.http.service;

/**
 * Created by fengbincao on 2017/5/24.
 */

public abstract class MoocResponse<T> {

    public abstract void success(MoocRequest request, T data);

    public abstract void fail(int errorCode, String errorMsg);
}
