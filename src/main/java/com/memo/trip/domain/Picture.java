package com.memo.trip.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "picture")
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Picture extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 파일 경로
    @Column(name = "file_path", length = 300, nullable = false)
    private String filePath;

    // 파일 명
    @Column(name = "file_name", length = 100, nullable = false)
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_id")
    private Memo memo;

    @Builder
    public Picture(Long id, String filePath, String fileName, Memo memo) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.memo = memo;
    }
}
