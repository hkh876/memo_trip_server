package com.memo.trip.exception;

import com.memo.trip.error.ErrorCode;

public class FileException extends BaseException {
    public FileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
