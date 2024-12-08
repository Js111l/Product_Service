package com.ecom.productservice.service;

import com.ecom.productservice.exception.ApiExceptionType;
import com.ecom.productservice.exception.ApplicationRuntimeException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class AsyncImportExecutor {
    private final ProductImportService productImportService;

    @Async
    public CompletableFuture<ResponseEntity<Object>> importProducts(final MultipartFile file, final String currentUser) {
        final var importUuid = UUID.randomUUID().toString();
        return CompletableFuture.runAsync(() -> this.productImportService.processFile(file, currentUser, importUuid)
                )
                .orTimeout(3600, TimeUnit.SECONDS)
                .thenApply(x -> ResponseEntity.of(Optional.empty()))//empty response
                .exceptionally(ex -> {
                    this.productImportService.stopImportJob(importUuid);
                    throw new ApplicationRuntimeException(ApiExceptionType.SERVER_ERROR);
                });
    }
}
