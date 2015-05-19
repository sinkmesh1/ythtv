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
 * Toastͳһ������
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
	 * ��ʱ����ʾToast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLong(Context context, CharSequence message) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// ��̬�����Զ����Toast����
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(message);
		mToast.setDuration(Toast.LENGTH_SHORT);// ����֪ͨ��ʾʱ��
		mToast.setView(view);// �󶨵�toast
		mToast.show();
	}

	/**
	 * ��ʱ����ʾToast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// ��̬�����Զ����Toast����
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(message);
		mToast.setDuration(Toast.LENGTH_SHORT);// ����֪ͨ��ʾʱ��
		mToast.setView(view);// �󶨵�toast
		mToast.show();
	}

	/**
	 * �Զ�����ʾToastʱ��
	 * 
	 * @param context
	 * @param message
	 * @param duration
	 */
	public static void show(Context context, CharSequence message, int duration) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// ��̬�����Զ����Toast����
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(message);
		mToast.setDuration(duration);// ����֪ͨ��ʾʱ��
		mToast.setView(view);// �󶨵�toast
		mToast.show();
	}

	/**
	 * ��Ļ������ʾ��ʱ��Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showInCenter(Context context, CharSequence message) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// ��̬�����Զ����Toast����
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(message);
		mToast.setDuration(Toast.LENGTH_LONG);// ����֪ͨ��ʾʱ��
		mToast.setView(view);// �󶨵�toast
		mToast.setGravity(Gravity.CENTER, 0, 0);

		mToast.show();
	}

	/**
	 * ������ʾToast��������ʾΪ��ɫ�Ӵ�
	 * @param context
	 * @param message
	 */
	public static void showWarnTips(Context context, CharSequence message) {
		mHandler.removeCallbacks(crun);
		mToast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(
				R.layout.util_base_toast_main, null);// ��̬�����Զ����Toast����
		TextView text = (TextView) view.findViewById(R.id.util_toast_main_textview);
		text.setText(Html.fromHtml("<font color='#CC0000'><big>" + message + "</big></font>"));
		mToast.setDuration(Toast.LENGTH_LONG);// ����֪ͨ��ʾʱ��
		mToast.setView(view);// �󶨵�toast
		mToast.show();
	}
	
	/**
	 * �����Ҫ��������������toast
	 */
	public static void hideToast() {
		mHandler.removeCallbacks(crun);
	}
}
