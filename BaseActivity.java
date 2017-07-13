package com.yuyoubang.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.yuyoubang.R;
import com.yuyoubang.app.AppManager;
import com.yuyoubang.view.LoadingDialog;

/**
 * date: Created hongchen on 16/11/05.
 */
public abstract class BaseActivity extends ToolBarActivity {

    private LoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRootContent();
        AppManager.getAppManager().addActivity(this);
    }

    private void setRootContent(){
        if (getContentResId() > 0){
            addContent(getContentResId());
        } else if (getFragment() != null){
            addFragment(getFragment());
        } else {
            throw new NullPointerException("必须实现getContentResId()或者getFragment()方法");
        }
    }

    private void addFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.public_layout, fragment);
        transaction.commitAllowingStateLoss();
    }

    protected int getContentResId(){
        return 0;
    }

    protected Fragment getFragment(){
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    public void qStartActivity(Intent intent) {
        startActivity(intent);
    }

    public void qStartActivity(Class<? extends Activity> cls) {
        qStartActivity(cls, null);
    }

    public void qStartActivity(Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(this, cls);
        startActivity(intent);
    }


    public void onShowProgressDlg() {
        if (dialog == null) {
            dialog = new LoadingDialog(this,"");
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

    }

    public void onShowProgressDlg(String text) {
        if (dialog == null) {
            dialog = new LoadingDialog(this,text);
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }

    }

    public void cancelProgressDlg() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }

    }

}
