package com.tourguide.multimedia.validator.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Storage;
import com.tourguide.multimedia.util.FileUtil;
import com.tourguide.multimedia.validator.FileValidator;

public class ImageFileValidatorImpl implements FileValidator {

    private static final List<String> ALLOWED_IMAGE_FORMATS = Arrays.asList("jpg", "jpeg", "png");
    private static final long MAX_IMAGE_SIZE = 10 * 1024 * 1024; // 10MB for instance

    @Override
    public void validate(MultipartFile file, Storage storage) throws IOException {
        // Image specific validation logic
        if (!ALLOWED_IMAGE_FORMATS.contains(FileUtil.getFileExtension(file.getOriginalFilename()))) {
            throw new IllegalArgumentException("Invalid image format");
        }
        if (file.getSize() > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("Image size exceeds the limit");
        }
    }
}
