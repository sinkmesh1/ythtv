package com.mesh.ythtv.util;

import android.util.Log;

/**
 * Log ͳһ������
 * 
 * @author SinkMesh
 * @version 1.0
 */
public class MeshLog {
	public static boolean isDebug = true;// �Ƿ���Ҫ��ӡbug��������application��onCreate���������ʼ��
	private static final String DEFAULT_TAG = "YTHMESH";

	// �����ĸ���Ĭ��TAG�ĺ���
	public static void i(String msg) {
		if (isDebug)
			Log.i(DEFAULT_TAG, msg);
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(DEFAULT_TAG, msg);
	}

	public static void d(String msg, Throwable tr){
		if (isDebug) {
			Log.d(DEFAULT_TAG, msg, tr);
		}
	}
	
	public static void e(String msg) {
		if (isDebug)
			Log.e(DEFAULT_TAG, msg);
	}

	public static void e(String msg, Throwable tr){
		if (isDebug) {
			Log.e(DEFAULT_TAG, msg, tr);
		}
	}
	public static void v(String msg) {
		if (isDebug)
			Log.v(DEFAULT_TAG, msg);
	}

	// �����Ǵ����Զ���TAG�ĺ���
	public static void i(String TAG, String msg) {
		if (isDebug)
			if (null == TAG || TAG.length() < 1) {
				Log.i(TAG, msg);
			} else {
				Log.i(DEFAULT_TAG, msg);
			}
	}

	public static void d(String TAG, String msg) {
		if (isDebug)
			if (null == TAG || TAG.length() < 1) {
				Log.d(TAG, msg);
			} else {
				Log.d(DEFAULT_TAG, msg);
			}
	}

	public static void e(String TAG, String msg) {
		if (isDebug)
			if (null == TAG || TAG.length() < 1) {
				Log.e(TAG, msg);
			} else {
				Log.e(DEFAULT_TAG, msg);
			}
	}

	public static void v(String TAG, String msg) {
		if (isDebug)
			if (null == TAG || TAG.length() < 1) {
				Log.v(TAG, msg);
			} else {
				Log.v(DEFAULT_TAG, msg);
			}
	}
}
