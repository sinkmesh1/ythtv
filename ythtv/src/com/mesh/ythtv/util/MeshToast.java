package com.mesh.ythtv.util;

import com.mesh.ythtv.R;

import android.content.Context;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Toast统一管理类
 * 
 * @author Mesh
 * 
 */
public class MeshToast {

	private static Toast mToast;

	private static Handler mHandler = new Handler();
    private static Runnable crun = new Runnable() {
        public void run() {
        	if(null != mToast)
        		mToast.cancel();
        }
    };
    
	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// 动态加载自定义的Toast布局
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(message);
		mToast.setDuration(Toast.LENGTH_SHORT);// 设置通知显示时长
		mToast.setView(view);// 绑定到toast
		mToast.show();
	}

	/**
	 * 短时间显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// 动态加载自定义的Toast布局
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(message);
		mToast.setDuration(Toast.LENGTH_SHORT);// 设置通知显示时长
		mToast.setView(view);// 绑定到toast
		mToast.show();
	}

	/**
	 * 自定义显示Toast时间
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message, int duration) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// 动态加载自定义的Toast布局
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(message);
		mToast.setDuration(duration);// 设置通知显示时长
		mToast.setView(view);// 绑定到toast
		mToast.show();
	}

	/**
	 * 屏幕中央显示短时间Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showInCenter(Context context, CharSequence message) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// 动态加载自定义的Toast布局
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(message);
		mToast.setDuration(Toast.LENGTH_LONG);// 设置通知显示时长
		mToast.setView(view);// 绑定到toast
		mToast.setGravity(Gravity.CENTER, 0, 0);

		mToast.show();
	}

	/**
	 * 警告提示Toast：字体显示为红色加粗
	 * @param context
	 * @param message
	 */
	public static void showWarnTips(Context context, CharSequence message) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// 动态加载自定义的Toast布局
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(Html.fromHtml("<font color='#CC0000'><big>" + message + "</big></font>"));
		mToast.setDuration(Toast.LENGTH_LONG);// 设置通知显示时长
		mToast.setView(view);// 绑定到toast
		mToast.show();
	}
	
	/**
	 * 如果需要可主动调用隐藏toast
	 */
	public static void hideToast() {
		mHandler.removeCallbacks(crun);
	}
}
