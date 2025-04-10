package com.memo.trip.service;

import com.memo.trip.error.ErrorCode;
import com.memo.trip.exception.FileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    public void saveFile(String directoryPath, String fileName, MultipartFile file) throws IOException {
        Path path = Paths.get(directoryPath);

        // 디렉터리 생성
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }

        Path savePath = path.toAbsolutePath().resolve(fileName).normalize();
        Files.copy(file.getInputStream(), savePath);
    }

    public void deleteFile(String filePath, String fileName) throws IOException {
        Path path = Paths.get(filePath).resolve(fileName).normalize();

        // 파일 존재 확인
        if (!Files.exists(path)) {
            throw new FileException(ErrorCode.NOT_FOUND_FILE_ERROR);
        }

        Files.delete(path);
    }
}
