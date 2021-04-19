package com.week7.wilayahapi.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinsiDto {
    private String kodeProvinsi, namaProvinsi;
    private List<KabupatenDto> kabupaten = new ArrayList<>();
}
