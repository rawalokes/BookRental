package com.infodev.bookrental.components;

import com.infodev.bookrental.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author rawalokes
 * Date:2/24/22
 * Time:1:27 PM
 */
@Component
@Slf4j
public class FileComponents {
    public ResponseDto filePath(MultipartFile multipartFile) {
        String folderPath = System.getProperty("user.home") + File.separator + "BookRent";
        File folderFile = new File(folderPath);
        if (!folderFile.exists()) {
            folderFile.mkdirs();
        } else {
            log.info("already exits");
        }
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (fileExtension.equalsIgnoreCase("jpg") ||
                fileExtension.equalsIgnoreCase("png")) {
            UUID uuid = UUID.randomUUID();
            String storedFilepath = folderPath + File.separator + uuid + "_" + multipartFile.getOriginalFilename();
            File fileToStore = new File(storedFilepath);
            return ResponseDto.builder()
                    .responseStatus(true)
                    .response(storedFilepath)
                    .build();

        } else {
            return ResponseDto.builder()
                    .responseStatus(false)
                    .response("Invalid Extension")
                    .build();
        }
    }
}
