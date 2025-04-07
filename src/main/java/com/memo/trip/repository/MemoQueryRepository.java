package com.memo.trip.repository;

import com.memo.trip.model.CityCountResDto;
import com.memo.trip.model.CountryCountResDto;

import java.util.List;

public interface MemoQueryRepository {
    List<CountryCountResDto> countAllCountryCode();
    List<CityCountResDto> countAllCityCode();
}
