package com.memo.trip.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemoUpdateReqDto {
    @Min(value = 1, message = "아이디 값은 1이상 입니다.")
    private Long id;

    @NotEmpty(message = "제목은 필수 값 입니다.")
    private String title;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    private String contents;
}
