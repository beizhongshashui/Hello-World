package com.yuyoubang.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyoubang.R;
import com.yuyoubang.listener.OnClickListener;
import com.yuyoubang.utils.ContextUtil;

/**
 * date: Created hongchen on 16/11/05.
 */
public abstract class ToolBarActivity extends UmengActivity {
    private Toolbar mToolbar;
    private FrameLayout mPublicLayout;

    protected HeaderBuilder mHeaderBuilder;
    private ImageView mLeftImg;
    private ImageView mRightImg;
    private TextView mTitleTv;
    private TextView mRightTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initRootViews();
        setDefaultListener();
        initToolBarOperate();
        initPublicLayout();
    }

    private void initRootViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLeftImg = (ImageView) findViewById(R.id.left_iv);
        mRightImg = (ImageView) findViewById(R.id.right_iv);
        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mRightTv = (TextView) findViewById(R.id.right_tv);

        setSupportActionBar(mToolbar);
        mPublicLayout = (FrameLayout) findViewById(R.id.public_layout);
    }

    private void setDefaultListener() {
        mLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            protected void clickOperate() {
                finish();
            }
        });
    }

    public void initToolBarOperate() {
        mHeaderBuilder = new HeaderBuilder();
        initTitleBar(mHeaderBuilder);
    }

    protected abstract void initTitleBar(HeaderBuilder builder);

    private void initPublicLayout() {
        mPublicLayout = (FrameLayout) findViewById(R.id.public_layout);
    }

    protected void addContent(int viewId) {
        LayoutInflater.from(this).inflate(viewId, mPublicLayout);
    }

    public class HeaderBuilder {
        public void setTitle(int resId) {
            if (mTitleTv == null) {
                return;
            }
            String title = ContextUtil.getInstance().getResources().getString(resId);
            mTitleTv.setText(title);
        }

        public void setTitle(String title) {
            if (mTitleTv == null || TextUtils.isEmpty(title)) {
                return;
            }
            mTitleTv.setText(title);
        }

        public void setBackGround(int resId) {
            mToolbar.setBackgroundColor(resId);
        }

        public void setTitleColor(int color) {
            if (mTitleTv == null) {
                return;
            }
            mTitleTv.setTextColor(color);
        }

        public void setLeftOperate(int imgResId, OnClickListener listener) {
            mLeftImg.setVisibility(View.VISIBLE);
            mLeftImg.setImageResource(imgResId);
            mLeftImg.setOnClickListener(listener);
        }

        public void setLeftOperate(OnClickListener listener) {
            mLeftImg.setVisibility(View.VISIBLE);
            mLeftImg.setOnClickListener(listener);
        }

        public void setRightImgOperate(int imgResId, OnClickListener listener) {
            mRightImg.setVisibility(View.VISIBLE);
            mRightTv.setVisibility(View.GONE);
            mRightImg.setImageResource(imgResId);
            mRightImg.setOnClickListener(listener);
        }

        public void setRightTvOperate(int textResId, OnClickListener listener) {
            mRightTv.setVisibility(View.VISIBLE);
            mRightImg.setVisibility(View.GONE);
            String content = getResources().getString(textResId);
            mRightTv.setText(content);
            mRightTv.setOnClickListener(listener);
        }

        public void setRightTvText(int textResId) {
            String content = getResources().getString(textResId);
            mRightTv.setText(content);
        }


        public void setRightTvText(String text, OnClickListener listener) {
            mRightTv.setText(text);
            mRightTv.setOnClickListener(listener);
            mRightTv.setVisibility(View.VISIBLE);
        }

        public void setRightTvColor(int colorResId) {
            int colorId = getResources().getColor(colorResId);
            mRightTv.setTextColor(colorId);
        }

        public void goneLeft() {
            mLeftImg.setVisibility(View.GONE);
        }

        public void goneToolbar() {
            mToolbar.setVisibility(View.GONE);
        }

        public void goneRightTv() {
            mRightTv.setVisibility(View.GONE);
        }

        public void goneRightImg() {
            mRightImg.setVisibility(View.GONE);
        }
    }

    protected <VG extends View> VG getViewById(int resId) {
        return (VG) mPublicLayout.findViewById(resId);
    }

}
