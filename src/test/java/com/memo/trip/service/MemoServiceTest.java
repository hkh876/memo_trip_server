package com.memo.trip.service;

import com.memo.trip.constant.Profiles;
import com.memo.trip.domain.Memo;
import com.memo.trip.model.MemoListResDto;
import com.memo.trip.model.MemoReqDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@ActiveProfiles(Profiles.TEST)
@SpringBootTest
public class MemoServiceTest {
    @Autowired
    private MemoService memoService;

    @Test
    @DisplayName("메모 정보 목록 조회 테스트")
    public void getMemoListTest() throws IOException {
        // Given
        MemoReqDto reqDto = MemoReqDto.builder()
                .title("테스트 제목")
                .countryCode("50")
                .cityCode("50111")
                .eventDate(LocalDate.now())
                .build();
        Memo createdMemo = memoService.createMemo(reqDto);

        // When
        List<MemoListResDto> results = memoService.getMemoList(createdMemo.getCityCode());

        // Then
        Assertions.assertThat(results.size()).isEqualTo(1);
    }
}
