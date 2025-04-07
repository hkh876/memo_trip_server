package com.memo.trip.controller;

import com.memo.trip.error.ErrorCode;
import com.memo.trip.exception.MemoException;
import com.memo.trip.model.*;
import com.memo.trip.service.MemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/memo")
public class MemoRestController {
    private final MemoService memoService;

    @PostMapping("/create")
    public void createMemo(@ModelAttribute @Valid MemoReqDto reqDto) throws IOException {
        memoService.createMemo(reqDto);
    }

    @GetMapping("/country/count")
    public List<CountryCountResDto> getCountryCount() {
        return memoService.getCountryCount();
    }

    @GetMapping("/city/count")
    public List<CityCountResDto> getCityCount() {
        return memoService.getCityCount();
    }

    @GetMapping("/list")
    public List<MemoListResDto> getMemoList(@RequestParam(name = "cityCode") String cityCode) {
        return memoService.getMemoList(cityCode);
    }

    @GetMapping("/info")
    public MemoInfoResDto getMemoInfo(@RequestParam(name = "id") Long id) {
        return memoService.getMemoInfo(id);
    }

    @PutMapping("/update")
    public void updateMemo(@RequestBody @Valid MemoUpdateReqDto reqDto) {
        memoService.updateMemo(reqDto);
    }

    @DeleteMapping("/delete")
    public void deleteMemo(@RequestParam(name = "id") Long id) throws IOException {
        memoService.deleteMemo(id);
    }

    @GetMapping("/picture/list")
    public List<PictureResDto> getPictureList(@RequestParam(name = "memoId") Long memoId) {
        return memoService.getPictureList(memoId);
    }

    @GetMapping("/picture/preview")
    public ResponseEntity<Resource> getPicturePreview(@RequestParam(name = "id") Long id) throws IOException {
        Path path = memoService.getPicturePath(id);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new MemoException(ErrorCode.NOT_FOUND_FILE_ERROR);
        }

        String contentType = Files.probeContentType(path);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }

    @DeleteMapping("/picture/delete")
    public void deletePicture(@RequestParam(name = "id") Long id) throws IOException {
        memoService.deletePicture(id);
    }

    @PostMapping("/picture/upload")
    public void uploadPicture(@ModelAttribute @Valid PictureUploadReqDto reqDto) throws IOException {
        memoService.uploadPicture(reqDto);
    }
}
