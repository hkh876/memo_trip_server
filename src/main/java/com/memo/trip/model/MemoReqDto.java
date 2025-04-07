package com.memo.trip.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MemoReqDto {
    @NotEmpty(message = "제목은 필수 값 입니다.")
    private String title;

    @NotEmpty(message = "행정 코드는 필수 값 입니다.")
    private String countryCode;

    @NotEmpty(message = "도시 코드는 필수 값 입니다.")
    private String cityCode;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    private String contents;
    private MultipartFile[] attachFiles;
    private List<PictureDto> pictures;

    @Builder
    public MemoReqDto(String title, String countryCode, String cityCode, LocalDate eventDate) {
        this.title = title;
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.eventDate = eventDate;
    }
}
