package cfb.com.dailydevelopment4.example5.download;

/**
 * 下载功能监听的Listener
 * Created by fengbincao on 2017/5/4.
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPause();

    void onCanceled();
}
