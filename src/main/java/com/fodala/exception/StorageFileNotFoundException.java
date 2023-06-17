package com.fodala.exception;

import java.io.Serial;

public class StorageFileNotFoundException extends StorageException {

    @Serial
    private static final long serialVersionUID = 6473323188518665969L;

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}