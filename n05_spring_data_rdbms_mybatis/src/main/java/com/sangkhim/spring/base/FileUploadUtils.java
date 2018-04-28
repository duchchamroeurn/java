package com.sangkhim.spring.base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs.provider.url.UrlFileName;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {
	
	private static final String		ROOT;
	private static final String 	PROJECT_NAME;
	private static final String 	FOLDER_UPLOAD;
	private static final String		ORIGINAL_FILENAME;
	
	static {
		ROOT 					= System.getProperty("VITOU_HOME");
		PROJECT_NAME			= "n05_spring_data_rdbms_mybatis";
		FOLDER_UPLOAD		 	= "upload";
		ORIGINAL_FILENAME		= "original-filename";		
	}
	
	private FileUploadUtils(){
		
	}
	
	public static String saveFileUploaded(MultipartFile fileUploaded){		
		try {
			byte[] bytes = IOUtils.toByteArray(fileUploaded.getInputStream());
			
			File path = new File(ROOT + File.separator + PROJECT_NAME + File.separator + FOLDER_UPLOAD);			
			if( !path.exists() ) path.mkdirs();
			
			String filename = fileUploaded.getOriginalFilename();
			String ext = FilenameUtils.getExtension(filename);
			String aliasFilename = UUID.randomUUID().toString();
			String filePath = path  + File.separator + aliasFilename + FilenameUtils.EXTENSION_SEPARATOR + ext;
			
			File file = new File(filePath);						
			file.createNewFile();			
			FileOutputStream fos = new FileOutputStream(file);			
			fos.write(bytes);
			fos.close();
			
			BufferedImage originalImage = ImageIO.read(file);
			ImageIO.write(originalImage, "jpg", file);						
			//writeOriginalFilename(file, filename);
			
			return makeFileUploadedUrl( aliasFilename + FilenameUtils.EXTENSION_SEPARATOR + ext );			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}
	
	public static void writeOriginalFilename(File file, String filename) throws IOException {
		UserDefinedFileAttributeView view = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
		view.write( ORIGINAL_FILENAME, Charset.defaultCharset().encode( filename ) );
	}
	
	public static String makeFileUploadedUrl(String filename){
		String filePath = UrlFileName.SEPARATOR_CHAR + FOLDER_UPLOAD  + UrlFileName.SEPARATOR_CHAR + filename;
		return filePath;
	}
	
	public static void deleteFile(String fileName){
		File file = new File(ROOT + File.separator + PROJECT_NAME + File.separator + fileName);
		file.delete();
	}
	
}