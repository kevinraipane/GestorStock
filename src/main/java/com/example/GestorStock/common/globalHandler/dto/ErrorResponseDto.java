package com.example.GestorStock.common.globalHandler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDto(
    String message,
    @JsonFormat(pattern = "HH:mm:ss dd-MM-yyyy")
    LocalDateTime localDateTime,
    Integer status
) {}
