package com.ms.user.exceptions;

public record ApiError(String code,
                       String message) {
}
