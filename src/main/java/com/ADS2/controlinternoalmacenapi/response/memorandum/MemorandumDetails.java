package com.ADS2.controlinternoalmacenapi.response.memorandum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemorandumDetails {
    private Long id;
    private String title;
    private String documentUrl;
    private String createdAt;
    private String status;
}
