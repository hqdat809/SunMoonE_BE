package com.example.ChamSocDinhDuong.exception;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime time, HttpStatusCode status, String message, String details) {
}