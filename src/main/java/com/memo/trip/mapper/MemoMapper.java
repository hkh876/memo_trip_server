package com.memo.trip.mapper;

import com.memo.trip.domain.Memo;
import com.memo.trip.model.MemoInfoResDto;
import com.memo.trip.model.MemoListResDto;
import com.memo.trip.model.MemoReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemoMapper {
    @Mapping(target = "id", ignore = true)
    Memo reqDtoToMemoDomain(MemoReqDto reqDto);
    List<MemoListResDto> memosToMemoListResDto(List<Memo> memos);
    MemoInfoResDto memoToMemoInfoResDto(Memo memo);
}
