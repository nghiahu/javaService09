package com.example.javaservice09.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {
    Map<String, String> config = new HashMap<>(ObjectUtils.asMap(
            "cloud_name","dimxrq8bs",
            "api_key" , "636233979283876",
            "api_secret","t2Te_smeTLPl5oVvS7Ow95fckRs"
    ));

    private final Cloudinary cloudinary = new Cloudinary(config);

    public String uploadImage(MultipartFile file) {
        try {
            Map<String, Object> upload = cloudinary.uploader().upload(file.getBytes(),ObjectUtils.emptyMap());
            return upload.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
