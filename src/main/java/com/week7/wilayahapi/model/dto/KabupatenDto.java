package com.week7.wilayahapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KabupatenDto {
    private String kodeKabupaten, namaKabupaten;
    private String kodeProvinsi;
}
