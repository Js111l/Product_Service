package com.ecom.productservice.service;

import com.ecom.productservice.dao.entities.ProductImport;
import com.ecom.productservice.dao.repository.ProductImportRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class ProductImportService {
    private static final Integer CHUNK_SIZE = 100;
    private String importUUID;
    private Boolean stopImportJobFlag;
    private final ProductImportRepository productImportRepository;

    public ProductImportService(ProductImportRepository productImportRepository) {
        this.productImportRepository = productImportRepository;
    }

    public void processFile(final MultipartFile usersCsv, final String currentUser, final String importUuid) {
        final var csvParser = getParser(usersCsv);
        final var spliterator = csvParser.spliterator();
        if (!csvParser.iterator().hasNext()) {
            throw new RuntimeException("CSV file is empty.");
        }
        final var csvRecordNumber = new AtomicInteger(0);
        var productImport = this.productImportRepository.save(this.getNewProductImport(importUuid, currentUser));
        StreamSupport.stream(spliterator, true).forEach(record -> {
            this.processRecord(record, productImport, csvRecordNumber);
        });

//        final var userImport = this.importService.createNewUserImport(currentUser, importUuid);
//        this.processRecords(csvRecords, company, userImport);
    }

    private ProductImport getNewProductImport(final String importUUID, final String userName) {
        var productImport = new ProductImport();
        productImport.setTotalImported(0);
        productImport.setTotalNotImported(0);
        productImport.setUuid(importUUID);
        productImport.setUserName(userName);
        return productImport;
    }

    public void stopImportJob(final String uuid) {
        this.importUUID = uuid;
        this.stopImportJobFlag = true;
    }

    private void processRecord(final CSVRecord csvRecord,
                               final ProductImport productImport,
                               final AtomicInteger csvRecordNumber) {
        this.stopImportJobFlag = false;

        if (this.stopImportJobFlag != null && this.stopImportJobFlag && Objects.equals(productImport.getUuid(), this.importUUID)) {
            //throw new LogicalRuntimeException(ErrorKey.SERVER_ERROR);
        }
        csvRecordNumber.incrementAndGet();
        try {
            //this.importService.processRecord(csvRecord, company, userImport, csvRecordNumber);
        } catch (Exception ex) {
            //this.importService.processFailedImport(csvRecord, userImport, csvRecordNumber);
        }
        log.info("Finished processing csv record");
    }

    //
    private CSVParser getParser(final MultipartFile usersCsv) {
        final var format = CSVFormat.DEFAULT
                .builder()
                .setDelimiter(",")
                .build();
        try (Reader reader = new BufferedReader(new InputStreamReader(usersCsv.getInputStream()))) {
            return new CSVParser(reader, format);
        } catch (IOException e) {
            //log.debug("Could not read from file {}", e.getMessage());
            //throw new LogicalRuntimeException(ErrorKey.COULD_NOT_READ_CSV_FILE);
            throw new RuntimeException("");
        }
    }

    //
    private void validateCsv(List<CSVRecord> records) {
        records.parallelStream().forEach(record -> {
            if (record.size() != 3) {
                //throw new LogicalRuntimeException(ErrorKey.INVALID_CSV_FORMAT);
            }
        });
    }
}
