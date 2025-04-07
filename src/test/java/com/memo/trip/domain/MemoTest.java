package com.memo.trip.domain;

import com.memo.trip.constant.Profiles;
import com.memo.trip.model.CountryCountResDto;
import com.memo.trip.repository.MemoQueryRepository;
import com.memo.trip.repository.MemoRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@ActiveProfiles(Profiles.TEST)
@SpringBootTest
public class MemoTest {
    @Autowired
    private MemoRepository memoRepository;

    @Autowired
    private MemoQueryRepository memoQueryRepository;

    @Test
    @DisplayName("행정 코드 별 개수 조회 테스트")
    public void getCountByCountryCodeTest() {
        // Given
        List<Memo> memos = List.of(
            Memo.builder()
                .countryCode("51")
                .cityCode("51000")
                .title("테스트 제목1")
                .contents("테스트 내용")
                .eventDate(LocalDate.now())
                .build(),
            Memo.builder()
                    .countryCode("51")
                    .cityCode("51001")
                    .title("테스트 제목2")
                    .contents("테스트 내용")
                    .eventDate(LocalDate.now())
                    .build(),
            Memo.builder()
                    .countryCode("52")
                    .cityCode("52000")
                    .title("테스트 제목3")
                    .contents("테스트 내용")
                    .eventDate(LocalDate.now())
                    .build()
        );
        memoRepository.saveAll(memos);

        // When
        List<CountryCountResDto> counts = memoQueryRepository.countAllCountryCode();

        // Then
        Assertions.assertThat(counts.size()).isEqualTo(2);
        Assertions.assertThat(counts.get(0).getCountryCode()).isEqualTo("51");
        Assertions.assertThat(counts.get(0).getCount()).isEqualTo(2);
        Assertions.assertThat(counts.get(1).getCountryCode()).isEqualTo("52");
        Assertions.assertThat(counts.get(1).getCount()).isEqualTo(1);
    }
}
