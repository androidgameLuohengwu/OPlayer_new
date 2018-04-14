package com.nmbb.oplayer.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.os.Environment;
import android.util.Log;

/**
 * SD卡工具类
 * @author wk
 *
 */
public class SDcardUtils {
	/**
	 * 获取外置SD卡路径，不存在返回null
	 * 
	 * @return
	 */
	public static String getExternalSDCardPath() {
		String cmd = "cat /proc/mounts";
		Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
		try {
			Process p = run.exec(cmd);// 启动另一个进程来执行命令
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				// 获得命令执行后在控制台的输出信息
				Log.i("CommonUtil:getSDCardPath", lineStr);
				if (lineStr.contains("sdcard")
						&& lineStr.contains(".android_secure")) {
					String[] strArray = lineStr.split(" ");
					if (strArray != null && strArray.length >= 5) {
						String result = strArray[1].replace("/.android_secure",
								"");
						return result;
					}
				}
				// 检查命令是否执行失败。
				if (p.waitFor() != 0 && p.exitValue() == 1) {
					// p.exitValue()==0表示正常结束，1：非正常结束
					Log.e("CommonUtil:getSDCardPath", "命令执行失败!");
				}
			}
			inBr.close();
			in.close();
		} catch (Exception e) {
			Log.e("CommonUtil:getSDCardPath", e.toString());
		}
		return null;
	}
	
	/**
	 * 获取内置SD卡路径
	 * @return
	 */
	public static String getInternalSDCardPath(){
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}
}
