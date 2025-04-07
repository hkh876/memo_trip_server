package com.memo.trip.mapper;

import com.memo.trip.domain.Picture;
import com.memo.trip.model.PictureDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-26T14:43:49+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PictureMapperImpl implements PictureMapper {

    @Override
    public Picture toPictureDomain(PictureDto dto) {
        if ( dto == null ) {
            return null;
        }

        Picture.PictureBuilder picture = Picture.builder();

        picture.filePath( dto.getFilePath() );
        picture.fileName( dto.getFileName() );
        picture.memo( dto.getMemo() );

        return picture.build();
    }
}
