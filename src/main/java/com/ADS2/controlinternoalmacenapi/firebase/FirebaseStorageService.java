package com.ADS2.controlinternoalmacenapi.firebase;

import com.ADS2.controlinternoalmacenapi.util.FileUtils;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    @Autowired
    private Storage storage;

    @Autowired
    private FileUtils fileUtils;

    private final String BUCKET_NAME = "control-interno-almacen.appspot.com";
    private final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/%s?alt=media";

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        fileName = UUID.randomUUID().toString().concat(fileUtils.getExtensionFile(fileName));

        File file = fileUtils.convertToFile(multipartFile, fileName);

        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/msword").build();

        this.storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String mediaUrl = String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        file.delete();

        return mediaUrl;
    }
}
