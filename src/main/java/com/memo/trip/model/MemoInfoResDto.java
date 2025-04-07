package com.memo.trip.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemoInfoResDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDate eventDate;
}
