package fileCompare;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
	private static Set<String> md5string = new HashSet<>();

	public static void main(String[] args) {
		try {
			Files.walkFileTree(Paths.get("D:","rrr"), new SimpleFileVisitor<Path>(){
				
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {  
			        //System.out.println("正在访问：" + dir + "目录");  
			        return FileVisitResult.CONTINUE;  
			    }
				
				@Override
				 public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {  
			            // TODO Auto-generated method stub  
			            // return super.visitFile(file, attrs);
						if (file.toString().contains("duplicate")) {
							System.out.println("\t正在访问" + file.toString() + "文件");
							String md5 = CheckUtil.getFileMD5(file.toFile());
							if (!md5string.add(md5)) {
								System.err.println(file + ": Duplicate");
							}
							System.out.println("\t\tMD5: " + md5);
							Files.copy(file, Paths.get("D:", "rrr","copied.txt"), StandardCopyOption.COPY_ATTRIBUTES);
							//CheckUtil.nioTransferCopy(file.toFile(), new File("D:/rrr/new.txt"));
						}
			            
			            return FileVisitResult.CONTINUE; // 没找到继续找  
				 }
					
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
}
