package com.week7.wilayahapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KecamatanDto {
    private String kodeKecamatan, namaKecamatan;
    private String kodeKabupaten;
}
