package cn.slkj.taxi.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;
	}

	public static boolean notEmpty(String s) {
		return (s != null) && (!"".equals(s)) && (!"null".equals(s));
	}

	public static boolean isEmpty(String s) {
		return (s == null) || ("".equals(s)) || ("null".equals(s));
	}

	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();

				return new Date();
			}
		}
		return null;
	}

	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return "";
	}

	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date now = new Date();
			Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / 86400000L;
			long hour = times / 3600000L - day * 24L;
			long min = times / 60000L - day * 24L * 60L - hour * 60L;
			long sec = times / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;

			StringBuffer sb = new StringBuffer();
			if (hour > 0L) {
				sb.append(hour + "小时前");
			} else if (min > 0L) {
				sb.append(min + "分钟前");
			} else {
				sb.append(sec + "秒前");
			}
			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultTimes;
	}

	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""));
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean checkKey(String paraname, String FKEY) {
		paraname = paraname == null ? "" : paraname;
		return MD5.md5(paraname + DateUtil.getDays() + ",taxi,").equals(FKEY);
	}

	public static String readTxtFile(String fileP) {
		try {
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""));
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if ((file.isFile()) && (file.exists())) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				if ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}

	public static String txt2String(String path) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""));
		filePath = filePath.replaceAll("file:/", "");
		filePath = filePath.replaceAll("%20", " ");
		filePath = filePath.trim() + path.trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		File file = new File(filePath);
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 读取一个文本 一行一行读取
	 *
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static List<String> readFile02(String path) throws IOException {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""));
		filePath = filePath.replaceAll("file:/", "");
		filePath = filePath.replaceAll("%20", " ");
		filePath = filePath.trim() + path.trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		// 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
		List<String> list = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(filePath);
		// 防止路径乱码 如果utf-8 乱码 改GBK eclipse里创建的txt 用UTF-8，在电脑上自己创建的txt 用GBK
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		while ((line = br.readLine()) != null) {
			// 如果 t x t文件里的路径 不包含---字符串 这里是对里面的内容进行一个筛选
			if (line.lastIndexOf("---") < 0) {
				list.add(line);
			}
		}
		br.close();
		isr.close();
		fis.close();
		return list;
	}
}
