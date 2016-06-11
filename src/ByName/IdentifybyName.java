package ByName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifybyName {
	static List<File> fileList = new ArrayList<File>();
	static List<String> fileNameList = new ArrayList<String>();
	static String directoryPath = "D:\\checkedDataSet\\handmade";

	public static void main(String[] args) {
		fileList.clear();
		fileNameList.clear();
		File directory = new File(directoryPath);
		if (directory.exists()) {
			makeFileList(directory);
		} else {
			System.out.println("target directory does not exist.");
			System.exit(0);
		}
		removePrefix();
		int count = 0;
		for (int i = 0; i < fileList.size(); i++) {
			if (!matchSableCC(fileNameList.get(i)))
				count++;
		}
		System.out.println(count);
	}

	private static void makeFileList(File file) {
		if (file.isDirectory()) {
			File[] innerFiles = file.listFiles();
			for (File tmp : innerFiles) {
				makeFileList(tmp);
			}
		} else if (file.isFile()) {
			if (file.getName().endsWith(".java")) {
				fileList.add(file);
			}
		}
	}

	public static void removePrefix() {
		for (int i = 0; i < fileList.size(); i++) {
			String[] strAry = fileList.get(i).getName().split("_", 2);//ファイル名の前の数字を除去
			String[] strAry2 = strAry[1].split("\\."); //.javaを除去
			fileNameList.add(strAry2[0]);
		}
	}

	public static boolean matchANTLR(String str) {
		boolean match = false;
		Matcher[] m = new Matcher[4];
		for (int i = 0; i < m.length; i++) {
			m[i] = RegConst.antlrp[i].matcher(str);
			if (m[i].find())
				match = true;
		}
		return match;
	}

	public static boolean matchJavaCC(String str) {
		boolean match = false;
		Matcher[] m = new Matcher[12];
		for (int i = 0; i < m.length; i++) {
			m[i] = RegConst.javaccp[i].matcher(str);
			if (m[i].find())
				match = true;
		}
		return match;
	}

	public static boolean matchJFlex(String str) {
		boolean match = false;
		Matcher m = RegConst.jflexp.matcher(str);
		if (m.find())
			match = true;
		return match;
	}

	public static boolean matchSableCC(String str) {
		boolean match = false;
		Matcher[] m = new Matcher[15];
		for (int i = 0; i < m.length; i++) {
			m[i] = RegConst.sableccp[i].matcher(str);
			if (m[i].find())
				match = true;
		}
		return match;
	}
}
