package com.zendaimoney.Dokodemo.util;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtils {
	protected static final Log logger = LogFactory.getLog(FileUtils.class);
	private static final String APPLICATION_TEST_PROPERTIES = "/application.test.properties";

	/**
	 * 获取层级为i的当前Class的CallerClass
	 * 
	 * @param i
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static Class<?> getCallerClass(int i) {
		return sun.reflect.Reflection.getCallerClass(i);
	}

	/**
	 * 
	 * 取得调用者所在类路径中的文件的绝对路径 如getFilePath("/xxx.txt")表示取得classes根目录下xxx.txt的绝对路径
	 * 所有放在src/main/resources目录下的资源文件都会自动复制到classes目录下
	 * 
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String getFilePath(String filePath) {
		try {
			return new File(sun.reflect.Reflection.getCallerClass(2)
					.getResource(filePath).toURI()).getAbsolutePath();
		} catch (URISyntaxException ex) {
			throw new RuntimeException(ex);
		} catch (NullPointerException ex) {
			logger.warn("The file main/resources" + filePath + " is not exist!");
		}
		return null;
	}

	/**
	 * 读取配置文件(application.test.properties)的内容
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		InputStream inputStream = FileUtils.class
				.getResourceAsStream(APPLICATION_TEST_PROPERTIES);
		if (inputStream == null) {
			logger.info(APPLICATION_TEST_PROPERTIES + "文件不存在！");
			return null;
		}
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return properties;
	}

	public static List<String> chooseFile(String downloadFileDir,
			String chooseFiles) {
		List<String> fileList = new ArrayList<String>();
		String[] downFileArry = chooseFiles.split(",");
		if (!downloadFileDir.endsWith(File.separator)) {
			downloadFileDir = downloadFileDir + File.separator;
		}
		File dirFile = new File(downloadFileDir);
		// 如果fileDir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			logger.debug("要删除的文件夹【" + downloadFileDir + "】不存在，不需要删除操作！");
			return fileList;
		}
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			boolean flag = false;
			String fileName = files[i].getName();
			for (int j = 0; j < downFileArry.length; j++) {
				String downloadFileName = downFileArry[j];
				if (fileName.equals(downloadFileName)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				fileList.add(fileName);
			}
		}
		return fileList;
	}

	/**
	 * 删除目录下的所有文件
	 * 
	 * @param fileDir
	 * @return
	 */
	public static boolean deleteDirectory(String fileDir) {
		// 如果fileDir不以文件分隔符结尾，自动添加文件分隔符
		if (!fileDir.endsWith(File.separator)) {
			fileDir = fileDir + File.separator;
		}
		File dirFile = new File(fileDir);
		// 如果fileDir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			logger.debug("删除目录失败" + fileDir + "目录不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			logger.debug("删除目录失败");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			logger.debug("删除目录" + fileDir + "成功！");
			return true;
		} else {
			logger.debug("删除目录" + fileDir + "失败！");
			return false;
		}
	}

	/**
	 * 判断目录存在不存在？如果不存在，则创建之
	 * 
	 * @param path
	 */
	public static void exist(String directory) {
		try {
			if (!new File(directory).isDirectory()) {
				new File(directory).mkdir();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	private static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			logger.debug("删除单个文件" + fileName + "失败！");
			return false;
		}
	}

	/**
	 * 写入文件
	 * 
	 * @param in
	 * @param filePath
	 */
	public static void writeFile(InputStream in, String filePath) {
		try {
			String path = filePath.substring(0, filePath.lastIndexOf("/"));
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			int BUFFER_SIZE = 1024;
			byte[] buf = new byte[BUFFER_SIZE];
			int size = 0;
			bis = new BufferedInputStream(in);
			fos = new FileOutputStream(filePath, false);
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
