package cfb.com.dailydevelopment4.example13.lsyoutstatus;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by fengbincao on 2017/7/26.
 */

public abstract class AbsLayout {
    // 用于延迟加载的布局
    protected ViewStub mLayoutViewStub;
    protected View mContentView;

    protected void initLayout(@LayoutRes int layoutResId, Context context) {
        mLayoutViewStub = new ViewStub(context);
        mLayoutViewStub.setLayoutResource(layoutResId);
    }

    protected ViewStub getLayoutVs() {
        return mLayoutViewStub;
    }

    protected void setView(View contentView) {
        mContentView = contentView;
    }

    protected abstract void setData(Object... objects);
}
