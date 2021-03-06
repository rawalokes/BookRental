package com.infodev.bookrental.components;

import com.infodev.bookrental.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

/**
 * @author rawalokes
 * Date:3/5/22
 * Time:11:44 AM
 */
@Slf4j
@Component
public class FileComponents {

    public ResponseDto storeFile(MultipartFile multipartFile) throws  IOException {

        String directoryPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "WICC";
        File directoryFile = new File(directoryPath);
        if (!directoryFile.exists()){
            directoryFile.mkdirs();
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
                    .build();
        }
    }
    public String returnFileAsBase64(String filePath) {
        File file = new File(filePath);
        try{
            byte[] bytes = Files.readAllBytes(file.toPath());
            String base64EncodedImage = "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
            return base64EncodedImage;
        }catch (IOException exception){

            return null;
        }
    }


}