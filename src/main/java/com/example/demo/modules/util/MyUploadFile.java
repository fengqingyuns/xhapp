package com.example.demo.modules.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class MyUploadFile {
	 private static final Logger LOGGER = LoggerFactory.getLogger(MyUploadFile.class);
	public static List<String> upload_image(MultipartFile[] files) {
		List<String> list_image = new ArrayList<String>();

		for (int i = 0; i < files.length; i++) {
			if(!files[i].isEmpty()){
			    
				String originalFilename = files[i].getOriginalFilename();
				LOGGER.info("originalFilename : {}",originalFilename);
				originalFilename = System.currentTimeMillis() + originalFilename;
				LOGGER.info("originalFilename : {}",originalFilename);
				try {
					files[i].transferTo(new File(
							//MyPropertiesUtil.getProperty("config/filePath.properties", "img_path") + "\\" + originalFilename));
					MyPropertiesUtil.getProperty("config/filePath.properties", "img_path") + "/" + originalFilename));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list_image.add(originalFilename);
			}
		}

		return list_image;
	}

}
