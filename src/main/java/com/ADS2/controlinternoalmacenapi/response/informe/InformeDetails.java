package com.ADS2.controlinternoalmacenapi.response.informe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformeDetails {
    private Long id;
    private String title;
    private String documentUrl;
    private String createdAt;
}
