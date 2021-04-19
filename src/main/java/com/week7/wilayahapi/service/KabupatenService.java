package com.week7.wilayahapi.service;

import java.util.List;

import com.week7.wilayahapi.model.dto.KabupatenDto;
import com.week7.wilayahapi.model.entity.Kabupaten;

public interface KabupatenService {
    public Kabupaten insertKabupaten(KabupatenDto dto);
    public List<Kabupaten> getAllKabupaten();
    public List<Kabupaten> getByProvinsi(String kodeProvinsi);
    public Kabupaten getByKodeKabupaten(String kodeKabupaten);
    public Kabupaten ediKabupaten(String kodeKabupaten, KabupatenDto dto);
    public Kabupaten remoKabupaten(String kodeKabupaten);
    public Kabupaten getById(Integer id);
}
