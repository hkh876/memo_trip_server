package com.memo.trip.service;

import com.memo.trip.domain.Memo;
import com.memo.trip.domain.Picture;
import com.memo.trip.error.ErrorCode;
import com.memo.trip.exception.MemoException;
import com.memo.trip.mapper.MemoMapper;
import com.memo.trip.mapper.PictureMapper;
import com.memo.trip.model.*;
import com.memo.trip.repository.MemoQueryRepository;
import com.memo.trip.repository.MemoRepository;
import com.memo.trip.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final FileService fileService;
    private final MemoMapper memoMapper;
    private final PictureMapper pictureMapper;
    private final MemoRepository memoRepository;
    private final MemoQueryRepository memoQueryRepository;
    private final PictureRepository pictureRepository;

    @Value("${upload.base}")
    String baseDirectoryPath;

    @Transactional
    public Memo createMemo(MemoReqDto reqDto) throws IOException {
        // 메모 저장
        Memo memo = memoMapper.reqDtoToMemoDomain(reqDto);
        Memo result = memoRepository.save(memo);

        // 파일 처리
        List<Picture> pictures = new ArrayList<>();
        String directoryPath = baseDirectoryPath + "/picture/" + result.getId() + "/";
        MultipartFile[] files = reqDto.getAttachFiles();

        if (files != null) {
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                fileService.saveFile(directoryPath, fileName, file);
                PictureDto pictureDto = PictureDto.builder()
                        .filePath(directoryPath)
                        .fileName(fileName)
                        .memo(result)
                        .build();

                pictures.add(pictureMapper.dtoToPictureDomain(pictureDto));
            }

            // 사진 데이터 저장
            pictureRepository.saveAll(pictures);
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<CountryCountResDto> getCountryCount() {
        return memoQueryRepository.countAllCountryCode();
    }
    
    @Transactional(readOnly = true)
    public List<CityCountResDto> getCityCount() {
        return memoQueryRepository.countAllCityCode();
    }

    @Transactional(readOnly = true)
    public List<MemoListResDto> getMemoList(String cityCode) {
        if (cityCode == null || cityCode.isEmpty()) {
            throw new MemoException(ErrorCode.EMPTY_CITY_CODE_ERROR);
        }

        List<Memo> results = memoRepository.findAllByCityCodeOrderByCreatedAtDesc(cityCode);
        return memoMapper.memosToMemoListResDto(results);
    }

    @Transactional(readOnly = true)
    public MemoInfoResDto getMemoInfo(Long id) {
        Memo result = memoRepository.findById(id).orElseThrow(
            () -> new MemoException(ErrorCode.NOT_FOUND_DATA_ERROR)
        );

        return memoMapper.memoToMemoInfoResDto(result);
    }

    @Transactional
    public void updateMemo(MemoUpdateReqDto reqDto) {
        Memo memo = memoRepository.findById(reqDto.getId()).orElseThrow(
            () -> new MemoException(ErrorCode.NOT_FOUND_DATA_ERROR)
        );

        memo.update(reqDto.getTitle(), reqDto.getContents(), reqDto.getEventDate());
    }

    @Transactional
    public void deleteMemo(Long id) throws IOException {
        // 데이터 조회
        Memo memo = memoRepository.findById(id).orElseThrow(
            () -> new MemoException(ErrorCode.NOT_FOUND_DATA_ERROR)
        );

        // 사진 파일 삭제
        List<Picture> pictures = memo.getPictures();
        for (Picture picture : pictures) {
            fileService.deleteFile(picture.getFilePath(), picture.getFileName());
        }

        // 데이터 삭제
        memoRepository.delete(memo);
    }

    @Transactional(readOnly = true)
    public List<PictureResDto> getPictureList(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
            () -> new MemoException(ErrorCode.NOT_FOUND_DATA_ERROR)
        );

        return pictureMapper.picturesToPictureListResDto(memo.getPictures());
    }

    @Transactional(readOnly = true)
    public Path getPicturePath(Long id) {
        Picture picture = pictureRepository.findById(id).orElseThrow(
            () -> new MemoException(ErrorCode.NOT_FOUND_DATA_ERROR)
        );

        return Paths.get(picture.getFilePath()).resolve(picture.getFileName()).normalize();
    }

    @Transactional
    public void deletePicture(Long id) throws IOException {
        Picture picture = pictureRepository.findById(id).orElseThrow(
            () -> new MemoException(ErrorCode.NOT_FOUND_DATA_ERROR)
        );

        // 파일 삭제
        fileService.deleteFile(picture.getFilePath(), picture.getFileName());

        // 데이터 삭제
        pictureRepository.delete(picture);
    }

    @Transactional
    public void uploadPicture(PictureUploadReqDto reqDto) throws IOException {
        Memo memo = memoRepository.findById(reqDto.getMemoId()).orElseThrow(
            () -> new MemoException(ErrorCode.NOT_FOUND_DATA_ERROR)
        );

        // 파일 처리
        List<Picture> pictures = new ArrayList<>();
        String directoryPath = baseDirectoryPath + "/picture/" + memo.getId() + "/";
        MultipartFile[] files = reqDto.getAttachFiles();

        if (files != null) {
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                fileService.saveFile(directoryPath, fileName, file);
                PictureDto pictureDto = PictureDto.builder()
                        .filePath(directoryPath)
                        .fileName(fileName)
                        .memo(memo)
                        .build();

                pictures.add(pictureMapper.dtoToPictureDomain(pictureDto));
            }

            // 사진 데이터 저장
            pictureRepository.saveAll(pictures);
        }
    }
}
