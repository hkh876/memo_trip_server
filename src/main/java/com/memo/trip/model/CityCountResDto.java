package com.memo.trip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityCountResDto {
    private String cityCode;    // 도시 코드
    private Long count; // 개수

    @Builder
    public CityCountResDto(String cityCode, Long count) {
        this.cityCode = cityCode;
        this.count = count;
    }
}
