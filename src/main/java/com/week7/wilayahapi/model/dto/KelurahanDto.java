package com.week7.wilayahapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KelurahanDto {
    private String kodeKelurahan, namaKelurahan;
    private String kodeKecamatan;
}
