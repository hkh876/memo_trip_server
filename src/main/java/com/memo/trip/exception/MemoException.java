package com.memo.trip.exception;

import com.memo.trip.error.ErrorCode;

public class MemoException extends BaseException {
    public MemoException(ErrorCode errorCode) {
        super(errorCode);
    }
}
