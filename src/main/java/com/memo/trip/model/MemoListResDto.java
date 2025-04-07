package com.memo.trip.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MemoListResDto {
    private Long id;
    private String title;
    private String contents;
    private String cityCode;
    private LocalDate eventDate;
    private List<PictureResDto> pictures;
}
