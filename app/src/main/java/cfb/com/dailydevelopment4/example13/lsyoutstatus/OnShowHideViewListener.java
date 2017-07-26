package cfb.com.dailydevelopment4.example13.lsyoutstatus;

import android.view.View;

/**
 * 状态View显示隐藏监听事件
 * Created by fengbincao on 2017/7/26.
 */

public interface OnShowHideViewListener {

    void onShowView(View view, int id);

    void onHideView(View view, int id);
}
