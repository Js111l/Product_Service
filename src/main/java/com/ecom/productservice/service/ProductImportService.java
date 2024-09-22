package com.ecom.productservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductImportService {
    public void processFile(MultipartFile file, String currentUser, String importUuid) {
        throw new IllegalStateException("NOT IMPLEMENTED");//TODO
    }

    public void stopImportJob(String importUuid) {
        throw new IllegalStateException("NOT IMPLEMENTED"); //TODO
    }
}
