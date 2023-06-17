package com.fodala.exception;

import java.io.Serial;

public class StorageException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 483984420594336679L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
