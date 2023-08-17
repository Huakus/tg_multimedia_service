package com.tourguide.multimedia.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    public String uploadFile(MultipartFile multipartFile) throws IOException;
}
