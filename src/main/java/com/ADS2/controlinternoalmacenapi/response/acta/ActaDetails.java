package com.ADS2.controlinternoalmacenapi.response.acta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActaDetails {
    private Long id;
    private String title;
    private String documentUrl;
    private String createdAt;
}
