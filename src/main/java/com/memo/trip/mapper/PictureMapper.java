package com.memo.trip.mapper;

import com.memo.trip.domain.Picture;
import com.memo.trip.model.PictureDto;
import com.memo.trip.model.PictureResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PictureMapper {
    @Mapping(target = "id", ignore = true)
    Picture dtoToPictureDomain(PictureDto dto);

    List<PictureResDto> picturesToPictureListResDto(List<Picture> pictures);
}
