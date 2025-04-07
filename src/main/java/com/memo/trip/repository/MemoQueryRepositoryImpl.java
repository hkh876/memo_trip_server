package com.memo.trip.repository;

import com.memo.trip.domain.QMemo;
import com.memo.trip.model.CityCountResDto;
import com.memo.trip.model.CountryCountResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoQueryRepositoryImpl implements MemoQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CountryCountResDto> countAllCountryCode() {
        QMemo memo = QMemo.memo;
        return jpaQueryFactory.select(memo.countryCode, memo.count())
                .from(memo)
                .groupBy(memo.countryCode)
                .fetch()
                .stream()
                .map(result -> CountryCountResDto.builder()
                        .countryCode(result.get(memo.countryCode))
                        .count(result.get(memo.count()))
                        .build())
                .toList();
    }

    @Override
    public List<CityCountResDto> countAllCityCode() {
        QMemo memo = QMemo.memo;
        return jpaQueryFactory.select(memo.cityCode, memo.count())
                .from(memo)
                .groupBy(memo.cityCode)
                .fetch()
                .stream()
                .map(result -> CityCountResDto.builder()
                        .cityCode(result.get(memo.cityCode))
                        .count(result.get(memo.count()))
                        .build())
                .toList();
    }
}
