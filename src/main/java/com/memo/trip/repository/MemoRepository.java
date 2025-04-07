package com.memo.trip.repository;

import com.memo.trip.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByCityCodeOrderByCreatedAtDesc(String cityCode);
}
