package com.week7.wilayahapi.service;

import java.util.List;

import com.week7.wilayahapi.model.dto.KecamatanDto;
import com.week7.wilayahapi.model.entity.Kecamatan;

public interface KecamatanService {
    public Kecamatan inputKecamatan(KecamatanDto dto);
    public List<Kecamatan> getAllKecamatan();
    public Kecamatan getByKodKecamatan(String kodeKecamatan);
    public Kecamatan ediKecamatan(String kodeKecamatan, KecamatanDto dto);
    public Kecamatan removeKecamatan(String kodeKecamatan);
    public Kecamatan getById(Integer id);
}
