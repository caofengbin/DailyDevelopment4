package cfb.com.dailydevelopment4.example5.download;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.id;

/**
 * Created by fengbincao on 2017/5/4.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    // 定义状态值标识下载的情况
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSE = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener listener;

    // 外界设置状态
    private boolean isCanceled = false;
    private boolean isPaused = false;

    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    // 后台下载功能的核心实现之处
    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            // 记录下载的长度
            long downloadLength = 0;
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if(file.exists()) {
                // 文件已经下载了，或下载了部分的文件
                downloadLength += file.length();
            }
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if(contentLength ==downloadLength ) {
                // 已下载的长度等于最终的长度，说明下载已经完成
                return TYPE_SUCCESS;
            }

            // 其他情况下，正常的走下载的流程即可

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    // 断点下载，指定从已开始的字节处开始下载
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();

            Call call = client.newCall(request);

            Response response = call.execute();

            if(response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                // 跳过已经下载过的字节部分
                savedFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while( (len = is.read(b)) != -1) {
                    if(isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSE;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        // 计算已经下载的百分比
                        int progress = (int) ( (total + downloadLength) * 100 / contentLength);
                        // 更新下载的进度值
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                } if(isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch(status) {
            case TYPE_SUCCESS :
                listener.onSuccess();
                break;
            case TYPE_FAILED :
                listener.onFailed();
                break;
            case TYPE_PAUSE :
                listener.onPause();
                break;
            case TYPE_CANCELED :
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if(progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }
    public void cancelDownload() {
        isCanceled = true;
    }

    /**
     * 计算指定文件的长度值
     * @param url           指定的url路径
     * @return
     */
    private long getContentLength(String downloadUrl) throws IOException {
        // 第1步，获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();

        // 第2步，构造Request
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();

        // 第3步，将request封装为call,类似runnable,非常重要的一个步骤
        Call call = client.newCall(request);

        // 第4步，同步执行
        Response response = call.execute();

        if(response != null && response.isSuccessful()) {
            // 待请求的资源文件的长度大小
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }

        return 0;
    }
}
