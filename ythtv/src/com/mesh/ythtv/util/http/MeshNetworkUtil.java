package com.mesh.ythtv.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.mesh.ythtv.util.MeshLog;

/**
 * 网络相关工具类
 * 
 * @author mesh
 * 
 */
public class MeshNetworkUtil {

//	private final String TAG = "MeshNetworkUtil";

	/**
	 * 判断手机是否联网/当前网络是否可用
	 * 
	 * @return true--联网 false--无网络
	 */
	public static Boolean haveInternet(Context context) {
		boolean isActive = false;
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.isAvailable();
		}
		// isActive = mNetworkInfo.isConnectedOrConnecting();
		return isActive;
	}

	/**
	 * 判断WIFI是否可用
	 * 
	 * @param context
	 * @return
	 */
	public boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断手机网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断网络类型是手机流量还是WIFI
	 * 
	 * @param context
	 * @return 0-Mobile;1-WIFI
	 */
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * 获取当前网络连接类型
	 * @param context
	 * @return
	 */
	public static String getConnectedTypeName(Context context) {
		String typeName = "";
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isConnected()) {
				int type = mNetworkInfo.getType();
				int subType = mNetworkInfo.getSubtype();
				if (type == ConnectivityManager.TYPE_WIFI) {// 0-Mobile
															// Network;1-Wifi;
					typeName = "WIFI";
				} else if (type == ConnectivityManager.TYPE_MOBILE) {
					switch (subType) {
					case TelephonyManager.NETWORK_TYPE_1xRTT:
						typeName = "1xRTT";
						break;
					case TelephonyManager.NETWORK_TYPE_CDMA:
						typeName = "CDMA";
						break;
					case TelephonyManager.NETWORK_TYPE_EDGE:
						typeName = "EDGE";
						break;
					case TelephonyManager.NETWORK_TYPE_EVDO_0:
						typeName = "EVDO_0";
						break;
					case TelephonyManager.NETWORK_TYPE_EVDO_A:
						typeName = "EVDO_A";
						break;
					case TelephonyManager.NETWORK_TYPE_GPRS:
						typeName = "GPRS";
						break;
					case TelephonyManager.NETWORK_TYPE_HSDPA:
						typeName = "HSDPA";
						break;
					case TelephonyManager.NETWORK_TYPE_HSPA:
						typeName = "HSPA";
						break;
					case TelephonyManager.NETWORK_TYPE_HSUPA:
						typeName = "HSUPA";
						break;
					case TelephonyManager.NETWORK_TYPE_UMTS:
						typeName = "UMTS";
						break;
					// NOT AVAILABLE YET IN API LEVEL 7
					case TelephonyManager.NETWORK_TYPE_EHRPD:
						typeName = "EHRPD";
						break;
					case TelephonyManager.NETWORK_TYPE_EVDO_B:
						typeName = "EVDO_B";
						break;
					case TelephonyManager.NETWORK_TYPE_HSPAP:
						typeName = "HSPAP";
						break;
					case TelephonyManager.NETWORK_TYPE_IDEN:
						typeName = "IDEN";
						break;
					case TelephonyManager.NETWORK_TYPE_LTE:
						typeName = "LTE";
						break;
					// Unknown
					case TelephonyManager.NETWORK_TYPE_UNKNOWN:
						typeName = "UNKNOWN";
						break;
					default:
						typeName = "MESH";// 没有进入分类的情况
						break;
					}
				}
				typeName = typeName+ "|" + subType;
			}
		}
		return typeName ;
	}

	/**
	 * 通过ping的方式判断某个指定的服务器地址是否可以正确连接， 比如：配置了服务器地址，可判断当前所处的网络环境下能否正确连接到该服务器
	 * 
	 * @param ipStr
	 * @return
	 */
	public static final boolean pingIP(String ipStr) {
		if (null == ipStr)
			return false;
		String result = null;
		try {
			Process p = Runtime.getRuntime().exec("ping -n 3 -w 100 " + ipStr);// ping3次(ping
																				// -c
																				// 3
																				// -w
																				// 100)
			// 读取ping的内容，可不加。
			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer stringBuffer = new StringBuffer();
			String content = "";
			while ((content = in.readLine()) != null) {
				stringBuffer.append(content);
			}
			MeshLog.i("[ping result content]" + stringBuffer.toString());
			// PING的状态
			int status = p.waitFor();
			if (status == 0) {
				result = "正确连接到 " + ipStr + " ";
				return true;
			} else {
				result = "连接到 " + ipStr + " 失败，请检查！";
			}
		} catch (IOException e) {
			result = "连接网络时GG了：IOException";
		} catch (InterruptedException e) {
			result = "连接网络时GG了：InterruptedException";
		} finally {
			MeshLog.i(result);
		}
		return false;
	}

	/**
	 * 判断网络条件状况，如设置智能选择的时候，可以设置当处于网络条件较好时加载图片
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectedFast(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return (info != null && info.isConnected() && isConnectionFast(
				info.getType(), info.getSubtype()));
	}

	/**
	 * 根据网络类型判断网络状况是否良好
	 * 
	 * @param type
	 * @param subType
	 * @return
	 */
	private static boolean isConnectionFast(int type, int subType) {
		if (type == ConnectivityManager.TYPE_WIFI) {
			MeshLog.i("CONNECTED VIA WIFI");
			return true;
		} else if (type == ConnectivityManager.TYPE_MOBILE) {
			switch (subType) {
			case TelephonyManager.NETWORK_TYPE_1xRTT:
				return false; // ~ 50-100 kbps
			case TelephonyManager.NETWORK_TYPE_CDMA:
				return false; // ~ 14-64 kbps
			case TelephonyManager.NETWORK_TYPE_EDGE:
				return false; // ~ 50-100 kbps
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
				return true; // ~ 400-1000 kbps
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
				return true; // ~ 600-1400 kbps
			case TelephonyManager.NETWORK_TYPE_GPRS:
				return false; // ~ 100 kbps
			case TelephonyManager.NETWORK_TYPE_HSDPA:
				return true; // ~ 2-14 Mbps
			case TelephonyManager.NETWORK_TYPE_HSPA:
				return true; // ~ 700-1700 kbps
			case TelephonyManager.NETWORK_TYPE_HSUPA:
				return true; // ~ 1-23 Mbps
			case TelephonyManager.NETWORK_TYPE_UMTS:
				return true; // ~ 400-7000 kbps
				// NOT AVAILABLE YET IN API LEVEL 7
			case TelephonyManager.NETWORK_TYPE_EHRPD:
				return true; // ~ 1-2 Mbps
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
				return true; // ~ 5 Mbps
			case TelephonyManager.NETWORK_TYPE_HSPAP:
				return true; // ~ 10-20 Mbps
			case TelephonyManager.NETWORK_TYPE_IDEN:
				return false; // ~25 kbps
			case TelephonyManager.NETWORK_TYPE_LTE:
				return true; // ~ 10+ Mbps
				// Unknown
			case TelephonyManager.NETWORK_TYPE_UNKNOWN:
				return false;
			default:
				return false;
			}
		} else {
			return false;
		}
	}

}
