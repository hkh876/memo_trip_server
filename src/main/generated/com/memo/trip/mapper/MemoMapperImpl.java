package com.memo.trip.mapper;

import com.memo.trip.domain.Memo;
import com.memo.trip.domain.Picture;
import com.memo.trip.model.MemoListResDto;
import com.memo.trip.model.MemoReqDto;
import com.memo.trip.model.PictureDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-26T18:07:48+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class MemoMapperImpl implements MemoMapper {

    @Override
    public Memo toMemoDomain(MemoReqDto reqDto) {
        if ( reqDto == null ) {
            return null;
        }

        Memo.MemoBuilder memo = Memo.builder();

        memo.title( reqDto.getTitle() );
        memo.contents( reqDto.getContents() );
        memo.countryCode( reqDto.getCountryCode() );
        memo.cityCode( reqDto.getCityCode() );
        memo.eventDate( reqDto.getEventDate() );

        return memo.build();
    }

    @Override
    public List<MemoListResDto> toMemoListResDto(List<Memo> memo) {
        if ( memo == null ) {
            return null;
        }

        List<MemoListResDto> list = new ArrayList<MemoListResDto>( memo.size() );
        for ( Memo memo1 : memo ) {
            list.add( memoToMemoListResDto( memo1 ) );
        }

        return list;
    }

    protected PictureDto pictureToPictureDto(Picture picture) {
        if ( picture == null ) {
            return null;
        }

        PictureDto.PictureDtoBuilder pictureDto = PictureDto.builder();

        pictureDto.filePath( picture.getFilePath() );
        pictureDto.fileName( picture.getFileName() );
        pictureDto.memo( picture.getMemo() );

        return pictureDto.build();
    }

    protected List<PictureDto> pictureListToPictureDtoList(List<Picture> list) {
        if ( list == null ) {
            return null;
        }

        List<PictureDto> list1 = new ArrayList<PictureDto>( list.size() );
        for ( Picture picture : list ) {
            list1.add( pictureToPictureDto( picture ) );
        }

        return list1;
    }

    protected MemoListResDto memoToMemoListResDto(Memo memo) {
        if ( memo == null ) {
            return null;
        }

        MemoListResDto memoListResDto = new MemoListResDto();

        memoListResDto.setId( memo.getId() );
        memoListResDto.setTitle( memo.getTitle() );
        memoListResDto.setContents( memo.getContents() );
        memoListResDto.setCityCode( memo.getCityCode() );
        memoListResDto.setEventDate( memo.getEventDate() );
        memoListResDto.setPictures( pictureListToPictureDtoList( memo.getPictures() ) );

        return memoListResDto;
    }
}
