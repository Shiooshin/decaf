package com.pet.decaf.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class ContentEntity {
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contentDate;
}
