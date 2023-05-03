package com.thomaspufahl.apiportfolio.Tool.Storage;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService implements StorageManager {

    @Value("${media.location}")
    private String mediaLocation;
    private Path rootLocation;
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) { throw new RuntimeException("Failed to store empty file."); }
            String filename = UUID.randomUUID() + "_" + Objects.requireNonNull(file.getOriginalFilename()).toLowerCase().replaceAll(" ", "-");
            Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file" + filename);
        }
    }
}

//    @PostMapping("/upload/{image_id}/{section}")
//    public Map<String, String> uploadFile(
//            @RequestParam("file")MultipartFile multipartFile,
//            @PathVariable Integer image_id,
//            @PathVariable Integer section
//    ) {
//        String path = storageManager.store(multipartFile);
//        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
//        String url = ServletUriComponentsBuilder
//                .fromHttpUrl(host)
//                .path("/image/")
//                .path(path)
//                .toUriString();
//        Image image = new Image();
//        if (section == 1) {
//            image.setBanner(url);
//            imageManager.editById(image_id, image);
//        } else if (section == 2) {
//            image.setAvatar(url);
//            imageManager.editById(image_id, image);
//        }
//        return Map.of("url", url);
//    }

//    @GetMapping("/{filename:.+}")
//    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
//        Resource file = storageManager.loadAsResource(filename);
//        String contentType = Files.probeContentType(file.getFile().toPath());
//
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.CONTENT_TYPE, contentType)
//                .body(file);
//    }