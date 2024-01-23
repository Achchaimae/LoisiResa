package com.achchaimae.loisiresa.Domain.message.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class MessageRespDTO {
    private int id;
    private String content;
    private LocalTime time;
}
