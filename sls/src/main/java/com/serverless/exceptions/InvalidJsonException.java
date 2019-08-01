package com.serverless.exceptions;

public class InvalidJsonException extends RuntimeException {
    public InvalidJsonException(String json) {
        super(String.format("Could not parse json: %s ", json));
    }
}
