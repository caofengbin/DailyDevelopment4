package cfb.com.dailydevelopment4.example13.lsyoutstatus;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

/**
 * Created by fengbincao on 2017/7/26.
 */

public class StatusLayoutManager {

    final Context context;

    final ViewStub netWorkErrorViewStub;
    final int netWorkErrorRetryViewId;

    final ViewStub emptyDataViewStub;
    final int emptyDataRetryViewId;

    final ViewStub errorViewStub;
    final int errorRetryViewId;

    final int loadingLayoutResId;
    final int contentLayoutResId;
    final int retryViewId;

    final int emptyDataIconImageId;
    final int emptyDataTextTipId;

    final int errorIconImageId;
    final int errorTextTipId;

    final AbsLayout errorLayout;
    final AbsLayout emptyDataLayout;


    final RootFrameLayout rootFrameLayout;
    final OnShowHideViewListener onShowHideViewListener;
    final OnRetryListener onRetryListener;

    public StatusLayoutManager(Builder builder) {
        this.context = builder.context;
        this.loadingLayoutResId = builder.loadingLayoutResId;
        this.netWorkErrorViewStub = builder.netWorkErrorViewStub;
        this.netWorkErrorRetryViewId = builder.netWorkErrorRetryViewId;
        this.emptyDataViewStub = builder.emptyDataViewStub;
        this.emptyDataRetryViewId = builder.emptyDataRetryViewId;
        this.errorViewStub = builder.errorViewStub;
        this.errorRetryViewId = builder.errorRetryViewId;
        this.contentLayoutResId = builder.contentLayoutResId;
        this.onShowHideViewListener = builder.onShowHideViewListener;
        this.retryViewId = builder.retryViewId;
        this.onRetryListener = builder.onRetryListener;
        this.emptyDataIconImageId = builder.emptyDataIconImageId;
        this.emptyDataTextTipId = builder.emptyDataTextTipId;
        this.errorIconImageId = builder.errorIconImageId;
        this.errorTextTipId = builder.errorTextTipId;
        this.errorLayout = builder.errorLayout;
        this.emptyDataLayout = builder.emptyDataLayout;

        rootFrameLayout = new RootFrameLayout(this.context);
        rootFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        rootFrameLayout.setStatusLayoutManager(this);
    }

    /**
     * 显示loading
     */
    public void showLoading() {
        rootFrameLayout.showLoading();
    }

    /**
     * 显示内容
     */
    public void showContent() {
        rootFrameLayout.showContent();
    }

    /**
     * 显示空数据
     */
    public void showEmptyData(int iconImage, String textTip) {
        rootFrameLayout.showEmptyData(iconImage, textTip);
    }

    public void showEmptyData() {
        showEmptyData(0, "");
    }

    public void showLayoutEmptyData(Object... objects) {
        rootFrameLayout.showLayoutEmptyData(objects);
    }

    /**
     * 显示网络异常
     */
    public void showNetWorkError() {
        rootFrameLayout.showNetWorkError();
    }

    /**
     * 显示异常
     */
    public void showError(int iconImage, String textTip) {
        rootFrameLayout.showError(iconImage, textTip);
    }

    public void showError() {
        showError(0, "");
    }

    public void showLayoutError(Object... objects) {
        rootFrameLayout.showLayoutError(objects);
    }

    /**
     * 得到root 布局
     */
    public View getRootLayout() {
        return rootFrameLayout;
    }


    public static final class Builder {
        private Context context;
        private ViewStub netWorkErrorViewStub;
        private int netWorkErrorRetryViewId;
        private ViewStub emptyDataViewStub;
        private int emptyDataRetryViewId;
        private ViewStub errorViewStub;
        private int errorRetryViewId;
        private int loadingLayoutResId;
        private int contentLayoutResId;
        private int retryViewId;
        private int emptyDataIconImageId;
        private int emptyDataTextTipId;
        private int errorIconImageId;
        private int errorTextTipId;
        private AbsLayout errorLayout;
        private AbsLayout emptyDataLayout;
        private OnShowHideViewListener onShowHideViewListener;
        private OnRetryListener onRetryListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder loadingView(@LayoutRes int loadingLayoutResId) {
            this.loadingLayoutResId = loadingLayoutResId;
            return this;
        }

        public Builder netWorkErrorView(@LayoutRes int newWorkErrorId) {
            netWorkErrorViewStub = new ViewStub(context);
            netWorkErrorViewStub.setLayoutResource(newWorkErrorId);
            return this;
        }

        public Builder emptyDataView(@LayoutRes int noDataViewId) {
            emptyDataViewStub = new ViewStub(context);
            emptyDataViewStub.setLayoutResource(noDataViewId);
            return this;
        }

        public Builder errorView(@LayoutRes int errorViewId) {
            errorViewStub = new ViewStub(context);
            errorViewStub.setLayoutResource(errorViewId);
            return this;
        }

        public Builder contentView(@LayoutRes int contentLayoutResId) {
            this.contentLayoutResId = contentLayoutResId;
            return this;
        }

        public Builder errorLayout(AbsLayout errorLayout) {
            this.errorLayout = errorLayout;
            this.errorViewStub = errorLayout.getLayoutVs();
            return this;
        }

        public Builder emptyDataLayout(AbsLayout emptyDataLayout) {
            this.emptyDataLayout = emptyDataLayout;
            this.emptyDataViewStub = emptyDataLayout.getLayoutVs();
            return this;
        }

        public Builder netWorkErrorRetryViewId(int netWorkErrorRetryViewId) {
            this.netWorkErrorRetryViewId = netWorkErrorRetryViewId;
            return this;
        }

        public Builder emptyDataRetryViewId(int emptyDataRetryViewId) {
            this.emptyDataRetryViewId = emptyDataRetryViewId;
            return this;
        }

        public Builder errorRetryViewId(int errorRetryViewId) {
            this.errorRetryViewId = errorRetryViewId;
            return this;
        }

        public Builder retryViewId(int retryViewId) {
            this.retryViewId = retryViewId;
            return this;
        }

        public Builder emptyDataIconImageId(int emptyDataIconImageId) {
            this.emptyDataIconImageId = emptyDataIconImageId;
            return this;
        }

        public Builder emptyDataTextTipId(int emptyDataTextTipId) {
            this.emptyDataTextTipId = emptyDataTextTipId;
            return this;
        }

        public Builder errorIconImageId(int errorIconImageId) {
            this.errorIconImageId = errorIconImageId;
            return this;
        }

        public Builder errorTextTipId(int errorTextTipId) {
            this.errorTextTipId = errorTextTipId;
            return this;
        }

        public Builder onShowHideViewListener(OnShowHideViewListener onShowHideViewListener) {
            this.onShowHideViewListener = onShowHideViewListener;
            return this;
        }

        public Builder onRetryListener(OnRetryListener onRetryListener) {
            this.onRetryListener = onRetryListener;
            return this;
        }

        public StatusLayoutManager build() {
            return new StatusLayoutManager(this);
        }
    }
}
