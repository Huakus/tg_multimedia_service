package com.tourguide.multimedia.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.tourguide.multimedia.service.StorageService;
import com.tourguide.multimedia.util.FileValidatorFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleCloudStorageServiceImpl implements StorageService {

    private static final String BUCKET_NAME = "tg-multimedia-bucket";
    
    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        // Initialize a Cloud Storage client
        Storage storage = StorageOptions.getDefaultInstance().getService();

        validateFile(multipartFile, storage);

        // generates a name based on the md5Digest to avoid duplicated files
        byte[] fileBytes = multipartFile.getBytes();
        String blobName = DigestUtils.md5DigestAsHex(fileBytes);

        // Create a blob id
        BlobId blobId = BlobId.of(BUCKET_NAME, blobName);

        // Create a blob info
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        // Upload the file to Google Cloud Storage
        storage.create(blobInfo, multipartFile.getBytes());

        // Make the blob public
        //blob.createAcl(com.google.cloud.storage.Acl.of(com.google.cloud.storage.Acl.User.ofAllUsers(), com.google.cloud.storage.Acl.Role.READER));

        // Return the blobName
        return blobName;
    }

    private void validateFile(MultipartFile multipartFile, Storage storage) throws IOException {
        FileValidatorFactory.getValidator(multipartFile).validate(multipartFile, storage);
    }
}
