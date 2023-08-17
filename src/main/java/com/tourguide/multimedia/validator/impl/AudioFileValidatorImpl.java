package com.tourguide.multimedia.validator.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Storage;
import com.tourguide.multimedia.util.FileUtil;
import com.tourguide.multimedia.validator.FileValidator;

public class AudioFileValidatorImpl implements FileValidator {

    private static final List<String> ALLOWED_AUDIO_FORMATS = Arrays.asList("mp3");
    private static final long MAX_AUDIO_SIZE = 50 * 1024 * 1024; // 50MB for instance

    @Override
    public void validate(MultipartFile file, Storage storage) throws IOException {
        // Audio specific validation logic
        if (!ALLOWED_AUDIO_FORMATS.contains(FileUtil.getFileExtension(file.getOriginalFilename()))) {
            throw new IllegalArgumentException("Invalid audio format");
        }
        if (file.getSize() > MAX_AUDIO_SIZE) {
            throw new IllegalArgumentException("Audio size exceeds the limit");
        }
    }
}
