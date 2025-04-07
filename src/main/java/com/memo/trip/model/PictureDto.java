package com.memo.trip.model;

import com.memo.trip.domain.Memo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PictureDto {
    private String filePath;
    private String fileName;
    private Memo memo;

    @Builder
    public PictureDto(String filePath, String fileName, Memo memo) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.memo = memo;
    }
}
