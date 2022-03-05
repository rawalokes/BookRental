package com.infodev.bookrental.components;

import com.infodev.bookrental.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author rawalokes
 * Date:3/5/22
 * Time:11:44 AM
 */
@Slf4j
@Component
public class Fsc {

    public ResponseDto storeFile(MultipartFile multipartFile) throws IOException, IOException {

        String directoryPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "WICC";
        File directoryFile = new File(directoryPath);
        if (!directoryFile.exists()){
            directoryFile.mkdirs();
        }
        else {
            log.info("Folder already exists.");
        }

        String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png")
                || ext.equalsIgnoreCase("jpeg")){
            UUID uuid = UUID.randomUUID();
            String filePath = directoryPath + File.separator + uuid + "_" + multipartFile.getOriginalFilename();
            File fileToStore = new File(filePath);
            multipartFile.transferTo(fileToStore);

            return ResponseDto.builder()
                    .responseStatus(true)
                    .response(filePath)
                    .build();
        }
        else {
            return ResponseDto.builder()
                    .responseStatus(false)
                    .response("invalid extension type").build();
        }
    }

}