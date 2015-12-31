package com.example.hongweiyu.demo;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @author yhw(email:861574834@qq.com)
 * @date 2015-12-30 19:30
 * @package com.example.hongweiyu.demo
 * @description ChatWindow  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class ChatWindow extends PopupWindow implements View.OnClickListener {

    public enum ChAT_TYPE{
        VIDEO, VOICE, VOICE_CALL, MESSAGE
    }

    protected Animation enterAnim;
    @SuppressWarnings("unused")
    protected Animation exitAnim;

    private boolean isAnim = true;

    private LinearLayout mWindowContainer;
    private ImageView mVideo, mVoice, mVoiceCall, mMessage;

    private OnChatChoiceCallBack onChatChoiceCallBack;

    public OnChatChoiceCallBack getOnChatChoiceCallBack() {
        return onChatChoiceCallBack;
    }

    public void setOnChatChoiceCallBack(OnChatChoiceCallBack onChatChoiceCallBack) {
        this.onChatChoiceCallBack = onChatChoiceCallBack;
    }

    public ChatWindow(Context context) {
        super(context);
        enterAnim = AnimationUtils.loadAnimation(context, R.anim.side_bottom_enter);
        exitAnim = AnimationUtils.loadAnimation(context, R.anim.side_bottom_exit);
        ColorDrawable dw = new ColorDrawable(0x80000000);
        setBackgroundDrawable(dw);
        setFocusable(true);

        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        initView(context);
    }

    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 初始化试图
     */
    private void initView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_conversation, null);
        mVideo = ((ImageView) root.findViewById(R.id.img_video));
        mVoice = ((ImageView) root.findViewById(R.id.img_voice));
        mVoiceCall = ((ImageView) root.findViewById(R.id.img_voice_call));
        mMessage = ((ImageView) root.findViewById(R.id.img_message));
        mWindowContainer = ((LinearLayout) root.findViewById(R.id.window_container));

        mVideo.setOnClickListener(this);
        mVoice.setOnClickListener(this);
        mVoiceCall.setOnClickListener(this);
        mMessage.setOnClickListener(this);

        setContentView(root);
    }

    public void setAnim(boolean isAnim) {
        this.isAnim = isAnim;
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        showEnterAnim();
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        showEnterAnim();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        showEnterAnim();
    }

    private void showEnterAnim() {
        if (!isAnim) {
            return;
        }
        View view = ((ViewGroup) getContentView()).getChildAt(0);
        view.startAnimation(enterAnim);
    }

    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);
        final ViewGroup root = (ViewGroup) contentView;
        contentView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = root.getChildAt(0).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.img_video:
                onChatChoiceCallBack.onChatChoiceCallBack(ChAT_TYPE.VIDEO);
                break;
            case R.id.img_voice:
                onChatChoiceCallBack.onChatChoiceCallBack(ChAT_TYPE.VOICE);
                break;
            case R.id.img_voice_call:
                onChatChoiceCallBack.onChatChoiceCallBack(ChAT_TYPE.VOICE_CALL);
                break;
            case R.id.img_message:
                onChatChoiceCallBack.onChatChoiceCallBack(ChAT_TYPE.MESSAGE);
                break;
            default:
                break;
        }
    }

    public interface OnChatChoiceCallBack{
        void onChatChoiceCallBack(ChAT_TYPE chAT_type);
    }
}
