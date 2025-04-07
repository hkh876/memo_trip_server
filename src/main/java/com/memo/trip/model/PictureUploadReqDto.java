package com.memo.trip.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PictureUploadReqDto {
    @Min(value = 1, message = "아이디 값은 1이상 입니다.")
    private Long memoId;
    private MultipartFile[] attachFiles;
}
