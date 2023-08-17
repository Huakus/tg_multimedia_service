package com.tourguide.multimedia.util;

import org.springframework.web.multipart.MultipartFile;

import com.tourguide.multimedia.validator.FileValidator;
import com.tourguide.multimedia.validator.impl.AudioFileValidatorImpl;
import com.tourguide.multimedia.validator.impl.ImageFileValidatorImpl;

public class FileValidatorFactory {

    public static FileValidator getValidator(MultipartFile file) {
        String extension = FileUtil.getFileExtension(file.getOriginalFilename());
        switch (extension) {
            case "jpg":
            case "jpeg":
            case "png":
                return new ImageFileValidatorImpl();
            case "mp3":
                return new AudioFileValidatorImpl();
            default:
                throw new IllegalArgumentException("File type not supported");
        }
    }
}
