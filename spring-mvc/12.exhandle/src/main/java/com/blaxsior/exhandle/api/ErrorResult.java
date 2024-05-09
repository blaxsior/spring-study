package com.blaxsior.exhandle.api;

public record ErrorResult(
        int code,
        String message
) {}