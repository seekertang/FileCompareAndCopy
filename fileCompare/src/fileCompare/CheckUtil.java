package fileCompare;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Date;

public class CheckUtil {
	
	public static void nioTransferCopy(File source, File target) {
		target.setLastModified(source.lastModified());
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = new FileInputStream(source);
			outputStream = new FileOutputStream(target);
			in = inputStream.getChannel();
			out = outputStream.getChannel();
			in.transferTo(0, in.size(), out);
			System.out.println(new Date(source.lastModified()));
			System.out.println(new Date(target.lastModified()));
		} catch (Exception e) {
		}
		finally {
			try {
				in.close();
				inputStream.close();
				out.close();
				outputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte[] buffer = new byte[4096];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInteger = new BigInteger(1, digest.digest());
			return bigInteger.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public static String getFileSha1(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte[] buffer = new byte[4096];
		int len;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInteger = new BigInteger(1, digest.digest());
			return bigInteger.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	
}
