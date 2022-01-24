package com.disneyApp.exception;

@SuppressWarnings("serial")
public class NotFound extends RuntimeException{
    public NotFound(String errors) {
        super(errors);
    }
}
