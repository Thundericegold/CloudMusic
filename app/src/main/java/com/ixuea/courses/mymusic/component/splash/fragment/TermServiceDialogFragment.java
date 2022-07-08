package com.ixuea.courses.mymusic.component.splash.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.fragment.BaseDialogFragment;
import com.ixuea.courses.mymusic.superui.process.SuperProcessUtil;
import com.ixuea.courses.mymusic.util.ScreenUtil;
import com.ixuea.courses.mymusic.util.SuperTextUtil;

/**
 * 服务条款和隐私协议对话框
 */
public class TermServiceDialogFragment extends BaseDialogFragment {

    private static final String TAG = "TermServiceDialogFragment";
    private TextView contentView;
    private MaterialButton primaryView;
    private Button disagreeView;
    private View.OnClickListener onAgreementClickListener;

    public static TermServiceDialogFragment newInstance() {

        Bundle args = new Bundle();

        TermServiceDialogFragment fragment = new TermServiceDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 显示对话框
     *
     * @param fragmentManager
     * @param onAgreementClickListener 同意按钮点击回调
     */
    public static void show(FragmentManager fragmentManager, View.OnClickListener onAgreementClickListener) {
        //创建fragment
        TermServiceDialogFragment fragment = newInstance();

        fragment.onAgreementClickListener = onAgreementClickListener;

        //显示
        //TAG只是用来查找Fragment的
        //我们这里不需要查找
        //所以值可以随便写
        fragment.show(fragmentManager, "TermServiceDialogFragment");
    }

    @Override
    protected void initViews() {
        super.initViews();

        //点击弹窗外边不能关闭
        setCancelable(false);
        contentView = findViewById(R.id.content);
        primaryView = findViewById(R.id.primary);
        disagreeView = findViewById(R.id.disagree);

        SuperTextUtil.setLinkColor(contentView, getActivity().getColor(R.color.link));
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        //仅作演示
        //DefaultPreferenceUtil.getInstance(getHostActivity());

        Spanned content = Html.fromHtml(getString(R.string.term_service_privacy_content));

        SpannableStringBuilder result = SuperTextUtil.setHtmlLinkClick(content, new SuperTextUtil.OnLinkClickListener() {
            @Override
            public void onLinkClick(String data) {
                Log.d(TAG, "onLinkClick: " + data);
            }
        });
        contentView.setText(result);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        primaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                onAgreementClickListener.onClick(v);
            }
        });
        disagreeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                SuperProcessUtil.killApp();
            }
        });
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dialog_term_service, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        //修改宽度，默认比AlertDialog.Builder显示对话框宽度窄，看着不好看
        //参考：https://stackoverflow.com/questions/12478520/how-to-set-dialogfragments-width-and-height
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();

        params.width = (int) (ScreenUtil.getScreenWith(getContext()) * 0.9);
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }
}
