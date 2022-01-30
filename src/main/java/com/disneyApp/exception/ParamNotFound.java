package com.disneyApp.exception;

@SuppressWarnings("serial")
public class ParamNotFound extends RuntimeException{
    public ParamNotFound(String errors) {
        super(errors);
    }
}
