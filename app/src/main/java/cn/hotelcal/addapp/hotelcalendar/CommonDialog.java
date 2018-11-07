package cn.hotelcal.addapp.hotelcalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;

/**
 * @author matt@uto360.com
 * @time 2017-05-18 8:42
 * @describe 通用dialog
 * @see #(boolean, View)  可以设置全屏
 * 第一参数为true 是启用全屏，同时如果全屏时不遮盖状态栏和标题栏，第二个参数就是标题栏view   如果false 或者view为空 是启用全屏不包括状态栏
 * @see #setMessageContentView(View)  设置dialog 的中间布局就是除了title和底部按钮的剩余布局
 * @see #setContentView(View)  设置除了底部按钮布局外的布局
 * @see #setContentRootView(View) 设置整个内容布局 完全自定义， 不使用已有的title ，message，底部按钮
 */
public class CommonDialog {

    private final static int BUTTON_BOTTOM = 9;
    private final static int BUTTON_TOP = 9;

    private boolean mCancel;
    private Context mContext;
    private AlertDialog mAlertDialog;
    private Builder mBuilder;
    private View mContentView;//除了底部按钮外的view
//    private int mContentViewResId;//除了底部按钮外的view
    private View mContentRootView;//
//    private int mContentRootViewResId;//设置自定义样式的view
    private int mTitleResId;
    private int mAnimationId;
    private View rootView;//自定义布局view
    private CharSequence mTitle;
    private View mTitleBarView;
    private int mMessageResId;
    private int mGravity;
    private CharSequence mMessage;
    private Button mPositiveButton;
    private LinearLayout.LayoutParams mLayoutParams;
    private Button mNegativeButton;
    private boolean mHasShow = false;
    private int mBackgroundResId = -1;
    private int dialogHeight = 0;
    private Drawable mBackgroundDrawable;
    private View mMessageContentView;
    private int mMessageContentViewResId;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private int pId = -1, nId = -1;
    private String pText, nText;
    View.OnClickListener pListener, nListener;

    public void setThemeResId(int themeResId) {
        this.themeResId = themeResId;
    }

    private @StyleRes int themeResId;

    public CommonDialog setFullScreenWithoutStateBar(boolean fullScreen) {
        mFullScreenWithoutStateTitleBar = fullScreen;
//        this.mTitleBarView = titleBarView;
//        if (mBuilder != null) {
//            mBuilder.setFullScreenWithoutStateTitleBar(fullScreen);
//            mBuilder.setTitleBarView(titleBarView);
//        }
        return this;
    }
    public CommonDialog setDialogHeight(int h) {
        dialogHeight = h;
        return this;
    }
    private boolean mFullScreenWithoutStateTitleBar;
    public CommonDialog(Context context) {
        this.mContext = context;
        Utils.init(context);
    }

    public CommonDialog(Context context, @LayoutRes int rootView) {
        this.rootView = LayoutInflater.from(context).inflate(rootView,null);
        this.mContext = context;
        Utils.init(context);
    }
    public CommonDialog(Context context, @LayoutRes int rootView, @StyleRes int themeResId) {
        this.rootView = LayoutInflater.from(context).inflate(rootView,null);
        this.mContext = context;
        this.themeResId = themeResId;
        Utils.init(context);
    }

    public void show() {
        if (!mHasShow) {
            mBuilder = new Builder();
        } else {
            mAlertDialog.show();
        }
        mHasShow = true;
    }
    public CommonDialog setContentRootView(View view) {
        mContentRootView = view;
        if (mBuilder != null) {
            mBuilder.setContentRootView(view);
        }
        return this;
    }
    public CommonDialog setContentRootView(int layoutResId) {
//        mContentRootViewResId = layoutResId;
        mContentRootView = LayoutInflater.from(mContext) .inflate(layoutResId, null);
        if (mBuilder != null) {
            mBuilder.setContentRootView(mContentRootView);
        }
        return this;
    }
    public CommonDialog setContentView(View view) {
        mContentView = view;
        if (mBuilder != null) {
            mBuilder.setContentView(view);
        }
        return this;
    }
//    public CommonDialog setView(View view) {
//        mContentView = view;
//        if (mBuilder != null) {
//            mBuilder.setContentView(view);
//        }
//        return this;
//    }
    public CommonDialog setContentView(int layoutResId) {
//        mContentViewResId = layoutResId;
        mContentView = LayoutInflater.from(mContext) .inflate(layoutResId, null);
        if (mBuilder != null) {
            mBuilder.setContentView(mContentView);
        }
        return this;
    }
    public View getContentView(){
        if(null==mContentView){
            throw new NullPointerException("You must set ContentView first ");
        }
        return this.mContentView;
    }
    public View getRootView(){
        if(null==rootView){
            throw new NullPointerException("You must set RootView first ");
        }
        return this.rootView;
    }
    public View getMessageContentView(){
        if(null==mMessageContentView){
            throw new NullPointerException("You must set MessageContentView first ");
        }
        return this.mMessageContentView;
    }
    public CommonDialog setMessageContentView(View view) {
        mMessageContentView = view;
        mMessageContentViewResId = 0;
        if (mBuilder != null) {
            mBuilder.setMessageContentView(mMessageContentView);
        }
        return this;
    }


    /**
     * Set a custom view resource to be the contents of the dialog.
     *
     * @param layoutResId resource ID to be inflated
     */
    public CommonDialog setMessageContentView(int layoutResId) {
        mMessageContentViewResId = layoutResId;
        mMessageContentView = null;
        if (mBuilder != null) {
            mBuilder.setMessageContentView(layoutResId);
        }
        return this;
    }

    public CommonDialog setBackground(Drawable drawable) {
        mBackgroundDrawable = drawable;
        if (mBuilder != null) {
            mBuilder.setBackground(mBackgroundDrawable);
        }
        return this;
    }


    public CommonDialog setBackgroundResource(int resId) {
        mBackgroundResId = resId;
        if (mBuilder != null) {
            mBuilder.setBackgroundResource(mBackgroundResId);
        }
        return this;
    }


    public void dismiss() {
        mAlertDialog.dismiss();
    }


    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    //mBuilder show 之后才创建
    public CommonDialog setTitle(int resId) {
        mTitleResId = resId;
        if (mBuilder != null) {
            mBuilder.setTitle(resId);
        }
        return this;
    }
    public CommonDialog setAnimation(int resId) {
        mAnimationId = resId;
        return this;
    }
    private CommonDialog setTitleBarView(View titleBarView) {
        this.mTitleBarView = titleBarView;
//        if (mBuilder != null) {
//            mBuilder.setTitleBarView(titleBarView);
//        }
        return this;
    }
    public CommonDialog setTitle(CharSequence title) {
        mTitle = title;
        if (mBuilder != null) {
            mBuilder.setTitle(title);
        }
        return this;
    }
    public CommonDialog setGravity(int  gravity) {
        this.mGravity = gravity;
        return this;
    }
    public CommonDialog setMessage(int resId) {
        mMessageResId = resId;
        if (mBuilder != null) {
            mBuilder.setMessage(resId);
        }
        return this;
    }


    public CommonDialog setMessage(CharSequence message) {
        mMessage = message;
        if (mBuilder != null) {
            mBuilder.setMessage(message);
        }
        return this;
    }


    public CommonDialog setPositiveButton(int resId, final View.OnClickListener listener) {
        this.pId = resId;
        this.pListener = listener;
        return this;
    }


    public Button getPositiveButton() {
        return mPositiveButton;
    }


    public Button getNegativeButton() {
        return mNegativeButton;
    }


    public CommonDialog setPositiveButton(String text, final View.OnClickListener listener) {
        this.pText = text;
        this.pListener = listener;
        return this;
    }


    public CommonDialog setNegativeButton(int resId, final View.OnClickListener listener) {
        this.nId = resId;
        this.nListener = listener;
        return this;
    }


    public CommonDialog setNegativeButton(String text, final View.OnClickListener listener) {
        this.nText = text;
        this.nListener = listener;
        return this;
    }


    public CommonDialog setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        if (mBuilder != null) {
            mBuilder.setCanceledOnTouchOutside(mCancel);
        }
        return this;
    }


    public CommonDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }


    private class Builder {

        private TextView mTitleView;
        private ViewGroup mMessageContentRoot;
//        private ViewGroup mContentRootView;
//        private ViewGroup mContentView;
        private TextView mMessageView;
        private Window mAlertDialogWindow;
        private LinearLayout mButtonLayout;
//        private View titleBarView;

        public void setFullScreenWithoutStateTitleBar(boolean fullScreen) {
            isFullScreenWithoutStateTitleBar = fullScreen;
        }
        private boolean isFullScreenWithoutStateTitleBar;
        @SuppressWarnings("deprecation")
        private Builder() {
            if(themeResId<=0){
                themeResId = R.style.CommonDialogStyle;//默认全屏样式
            }
//                setFullScreenWithoutStateTitleBar(mFullScreenWithoutStateTitleBar);
//                if(mTitleBarView!=null){
//                    setTitleBarView(mTitleBarView);
//                }


            if(rootView != null){
                mAlertDialog = new AlertDialog.Builder(mContext,themeResId).create();
                mAlertDialogWindow = mAlertDialog.getWindow();

                mAlertDialogWindow.setWindowAnimations(R.style.dialogAnim);
                if(mAnimationId>0){
                    mAlertDialogWindow.setWindowAnimations(mAnimationId);
                }
                mAlertDialog.show();
                mAlertDialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                mAlertDialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);
                //isFullScreenWithoutStateTitleBar 为 true同时也得设置的样式为全屏  全屏不包括状态栏和标题栏  false 就根据样式显示
                //&& mMessageContentView==null
                mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);
                mAlertDialogWindow.setContentView(rootView);
                if(mFullScreenWithoutStateTitleBar){

                    Rect rect = new Rect();
                    WindowManager.LayoutParams params = mAlertDialog.getWindow().getAttributes();
                    ((Activity)mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    int statusBarHeight = rect.top;
                    params.width = ScreenUtils.getScreenWidth();
                    params.height = ScreenUtils.getScreenHeight()- statusBarHeight;
//                  params.alpha = 0.8f;
//                    params.dimAmount = 0.6f;//设置模糊
                    if(mGravity>0){
                        params.gravity= mGravity;
                    }else{
                        params.gravity= Gravity.BOTTOM;
                    }

                    mAlertDialogWindow.setAttributes(params);
                } else {
//                    Rect rect = new Rect();
                    WindowManager.LayoutParams params = mAlertDialog.getWindow().getAttributes();
//                    ((Activity)mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//                    ((Activity)mContext).getWindow().getDecorView().setPadding(0,0,0,0);
//                    int statusBarHeight = rect.top;
                    params.width = ScreenUtils.getScreenWidth();
//                    params.height = ScreenUtils.getScreenHeight()- statusBarHeight;
                    if(dialogHeight!=0){
                        params.height = dialogHeight;
                    }else{
                        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    }

//                  params.alpha = 0.8f;
//                    params.dimAmount = 0.6f;//设置模糊
                    if(mGravity>0){
                        params.gravity= mGravity;
                    }else{
                        params.gravity= Gravity.CENTER;
                    }

                    mAlertDialogWindow.setAttributes(params);

                }

            }else{
                mAlertDialog = new AlertDialog.Builder(mContext).create();
                mAlertDialogWindow = mAlertDialog.getWindow();
                mAlertDialogWindow.setWindowAnimations(R.style.dialogAnim);
                mAlertDialog.show();
                mAlertDialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                mAlertDialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);

                View contentLayout = LayoutInflater.from(mContext).inflate(R.layout.layout_materialdialog,null);
                contentLayout.setFocusable(true);
                contentLayout.setFocusableInTouchMode(true);
                mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);
                mAlertDialogWindow.setContentView(contentLayout);
                mTitleView = (TextView) mAlertDialogWindow.findViewById(R.id.title);
                mMessageView = (TextView) mAlertDialogWindow.findViewById(R.id.message);
                mButtonLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.buttonLayout);
                mPositiveButton = (Button) mButtonLayout.findViewById(R.id.btn_p);
                mNegativeButton = (Button) mButtonLayout.findViewById(R.id.btn_n);
                mMessageContentRoot = (ViewGroup) mAlertDialogWindow.findViewById(R.id.message_content_root);
                if (mContentView != null) {
                    LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(
                            R.id.contentView);
                    linearLayout.removeAllViews();
                    linearLayout.addView(mContentView);
                }
                if (mTitleResId != 0) {
                    setTitle(mTitleResId);
                }
                if (mTitle != null) {
                    setTitle(mTitle);
                }
                if (mTitle == null && mTitleResId == 0) {
                    mTitleView.setVisibility(View.GONE);
                }
                if (mMessageResId != 0) {
                    setMessage(mMessageResId);
                }
                if (mMessage != null) {
                    setMessage(mMessage);
                }
                if (pId != -1) {
                    mPositiveButton.setVisibility(View.VISIBLE);
                    mPositiveButton.setText(pId);
                    mPositiveButton.setOnClickListener(pListener);
                    if (isLollipop()) {
                        mPositiveButton.setBackgroundResource(android.R.color.transparent);
                    }
                }
                if (nId != -1) {
                    mNegativeButton.setVisibility(View.VISIBLE);
                    mNegativeButton.setText(nId);
                    mNegativeButton.setOnClickListener(nListener);
                    if (isLollipop()) {
                        mNegativeButton.setBackgroundResource(android.R.color.transparent);
                    }
                }
                if (!isNullOrEmpty(pText)) {
                    mPositiveButton.setVisibility(View.VISIBLE);
                    mPositiveButton.setText(pText);
                    mPositiveButton.setOnClickListener(pListener);
                    if (isLollipop()) {
                        mPositiveButton.setBackgroundResource(android.R.color.transparent);
                    }
                }

                if (!isNullOrEmpty(nText)) {
                    mNegativeButton.setVisibility(View.VISIBLE);
                    mNegativeButton.setText(nText);
                    mNegativeButton.setOnClickListener(nListener);
                    if (isLollipop()) {
                        mNegativeButton.setBackgroundResource(android.R.color.transparent);
                    }
                }
                if (isNullOrEmpty(pText) && pId == -1) {
                    mPositiveButton.setVisibility(View.GONE);
                }
                if (isNullOrEmpty(nText) && nId == -1) {
                    mNegativeButton.setVisibility(View.GONE);
                }
                if (mBackgroundResId != -1) {
                    mContentRootView.setBackgroundResource(mBackgroundResId);
                }
                if (mBackgroundDrawable != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mContentRootView.setBackground(mBackgroundDrawable);
                    }else{
                        mContentRootView.setBackgroundDrawable(mBackgroundDrawable);
                    }
                }
                if (mMessageContentView != null) {
                    this.setMessageContentView(mMessageContentView);
                } else if (mMessageContentViewResId != 0) {
                    this.setMessageContentView(mMessageContentViewResId);
                }
            }

            mAlertDialog.setCanceledOnTouchOutside(mCancel);
            if (mOnDismissListener != null) {
                mAlertDialog.setOnDismissListener(mOnDismissListener);
            }
        }
//        public void setTitleBarView(View titleBarView){
//            this.titleBarView = titleBarView;
//        }
        public void setTitle(int resId) {
            mTitleView.setText(resId);
        }

        public void setTitle(CharSequence title) {
            mTitleView.setText(title);
        }


        public void setMessage(int resId) {
            if (mMessageView != null) {
                mMessageView.setText(resId);
            }
        }


        public void setMessage(CharSequence message) {
            if (mMessageView != null) {
                mMessageView.setText(message);
            }
        }


        /**
         * set positive button
         *
         * @param text the name of button
         */
        public void setPositiveButton(String text, final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setTextColor(Color.argb(255, 35, 159, 242));
            button.setText(text);
            button.setGravity(Gravity.CENTER);
            button.setTextSize(14);
            button.setPadding(dip2px(12), 0, dip2px(32), dip2px(BUTTON_BOTTOM));
            button.setOnClickListener(listener);
            mButtonLayout.addView(button);
        }


        /**
         * set negative button
         *
         * @param text the name of button
         */
        public void setNegativeButton(String text, final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setText(text);
            button.setTextColor(Color.argb(222, 0, 0, 0));
            button.setTextSize(14);
            button.setGravity(Gravity.CENTER);
            button.setPadding(0, 0, 0, dip2px(8));
            button.setOnClickListener(listener);
            if (mButtonLayout.getChildCount() > 0) {
                params.setMargins(20, 0, 10, dip2px(BUTTON_BOTTOM));
                button.setLayoutParams(params);
                mButtonLayout.addView(button, 1);
            } else {
                button.setLayoutParams(params);
                mButtonLayout.addView(button);
            }
        }
        /**
         * 设置除了底部按钮外的view
         * */
        public void setContentView(View view) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    mAlertDialogWindow.setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    // show imm
                    InputMethodManager imm
                            = (InputMethodManager) mContext.getApplicationContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            });
            LinearLayout l = (LinearLayout) mAlertDialogWindow.findViewById(R.id.contentView);
            if(l!=null){
                l.removeAllViews();
                l.addView(view);
            }

            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof EditText) {
                        EditText editText = (EditText) viewGroup.getChildAt(i);
                        editText.setFocusable(true);
                        editText.requestFocus();
                        editText.setFocusableInTouchMode(true);
                    }
                }
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(
                            i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView autoCompleteTextView
                                = (AutoCompleteTextView) viewGroup.getChildAt(
                                i);
                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusableInTouchMode(true);
                    }
                }
            }
        }
        public void setContentRootView(View contentRootView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            contentRootView.setLayoutParams(layoutParams);
            if (contentRootView instanceof ListView) {
                setListViewHeightBasedOnChildren((ListView) contentRootView);
            }
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.contentRootView);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentRootView);
            }
            for (int i = 0;
                 i < (linearLayout != null ? linearLayout.getChildCount() : 0);i++) {
                if (linearLayout.getChildAt(i) instanceof AutoCompleteTextView) {
                    AutoCompleteTextView autoCompleteTextView
                            = (AutoCompleteTextView) linearLayout.getChildAt(i);
                    autoCompleteTextView.setFocusable(true);
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.setFocusableInTouchMode(true);
                }
            }
        }



        public void setMessageContentView(View contentView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            contentView.setLayoutParams(layoutParams);
            if (contentView instanceof ListView) {
                setListViewHeightBasedOnChildren((ListView) contentView);
            }
            LinearLayout linearLayout
                    = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.message_content_view);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentView);
            }
            for (int i = 0;
                 i < (linearLayout != null ? linearLayout.getChildCount() : 0);i++) {
                if (linearLayout.getChildAt(i) instanceof AutoCompleteTextView) {
                    AutoCompleteTextView autoCompleteTextView
                            = (AutoCompleteTextView) linearLayout.getChildAt(i);
                    autoCompleteTextView.setFocusable(true);
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.setFocusableInTouchMode(true);
                }
            }
        }


        /**
         * Set a custom view resource to be the contents of the dialog. The
         * resource will be inflated into a ScrollView.
         *
         * @param layoutResId resource ID to be inflated
         */
        public void setMessageContentView(int layoutResId) {
            mMessageContentRoot.removeAllViews();
            // Not setting this to the other content view because user has defined their own
            // layout params, and we don't want to overwrite those.
            LayoutInflater.from(mMessageContentRoot.getContext())
                    .inflate(layoutResId, mMessageContentRoot);
        }


        @SuppressWarnings("deprecation")
        public void setBackground(Drawable drawable) {
            LinearLayout linearLayout
                    = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.contentRootView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                linearLayout.setBackground(drawable);
            }else{
                linearLayout.setBackgroundDrawable(drawable);
            }
        }


        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout
                    = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.contentRootView);
            linearLayout.setBackgroundResource(resId);
        }


        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
    }


    private boolean isNullOrEmpty(String nText) {
        return nText == null || nText.isEmpty();
    }


    /**
     * 动态测量list view item的高度
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight +
                (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}

