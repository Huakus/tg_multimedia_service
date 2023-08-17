package com.tourguide.multimedia.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tourguide.multimedia.service.StorageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileUrl = storageService.uploadFile(file);
        return ResponseEntity.ok(fileUrl);
    }
}
