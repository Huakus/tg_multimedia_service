package com.tourguide.multimedia.validator;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Storage;

public interface FileValidator {
    void validate(MultipartFile file, Storage storage) throws IOException;
}
