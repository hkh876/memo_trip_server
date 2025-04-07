package com.memo.trip.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "memo")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    // 제목
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    // 내용
    @Column(name = "contents", length = 1000)
    private String contents;

    // 행정 코드
    @Column(name = "country_code", length = 2, nullable = false)
    private String countryCode;

    // 도시 코드
    @Column(name = "city_code", length = 5, nullable = false)
    private String cityCode;

    // 해당 날짜
    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    // 이미지 파일 목록
    @OneToMany(mappedBy = "memo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("createdAt DESC")
    private List<Picture> pictures;

    @Builder
    public Memo(Long id, String title, String contents, String countryCode, String cityCode, LocalDate eventDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.eventDate = eventDate;
    }

    public void update(String title, String contents, LocalDate eventDate) {
        if (!Objects.equals(this.title, title)) {
            this.title = title;
        }

        if (!Objects.equals(this.contents, contents)) {
            this.contents = contents;
        }

        if (!Objects.equals(this.eventDate, eventDate)) {
            this.eventDate = eventDate;
        }
    }
}
