package com.gft.parte1;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gft.parte1.service.FileService;

@SpringBootApplication
public class Parte1Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Parte1Application.class);

    @Autowired
    private FileService fileService;
    
	@Value("${json.files}")
	private String jsonFiles;	

    public static void main(String[] args) {
        SpringApplication.run(Parte1Application.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("Starting Application...");

        log.info("Files processed: "  + Stream.of(jsonFiles.split(","))
        .parallel().map(str -> fileService.processFile(str))
        .count());        

    }

}