package com.week7.wilayahapi.service;

import java.util.List;

import com.week7.wilayahapi.model.dto.ProvinsiDto;
import com.week7.wilayahapi.model.dto.WilayahDto;
import com.week7.wilayahapi.model.entity.Provinsi;

public interface ProvinsiService {
    public Provinsi insertProvinsi(ProvinsiDto dto);
    public List<Provinsi> getAllProvinsi();
    public Provinsi getByKodeProvinsi(String kodeProvinsi);
    public Provinsi editProvinsi(String kodeProvinsi, ProvinsiDto dto);
    public Provinsi removeProvinsi(String kodeProvinsi);
    public Provinsi getById(Integer id);
}
