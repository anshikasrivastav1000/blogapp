package com.blogApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService{
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
// Random ID
        String randomID = UUID.randomUUID().toString();

// Ensure the file has an extension
        String fileName1;
        if (name != null && name.contains(".")) {
            fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
        } else {
            // Handle files without extensions
            fileName1 = randomID; // Or assign a default extension like ".txt" if needed
        }

// Full path
        String filePath = path + File.separator + fileName1;

// Create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

// File copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws IOException{
        String fullPath = path+File.separator+fileName;
        InputStream is = new FileInputStream(fullPath);

        return is;
    }
}
