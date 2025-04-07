package com.memo.trip.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryCountResDto {
    private String countryCode; // 행정 코드
    private Long count;  // 개수

    @Builder
    public CountryCountResDto(String countryCode, Long count) {
        this.countryCode = countryCode;
        this.count = count;
    }
}
