package com.week7.wilayahapi.service;

import java.util.List;

import com.week7.wilayahapi.model.dto.KelurahanDto;
import com.week7.wilayahapi.model.entity.Kelurahan;

public interface KelurahanService {
    public Kelurahan inputKelurahan(KelurahanDto dto);
    public List<Kelurahan> getAllKelurahan();
    public Kelurahan getByKodKelurahan(String kodeKelurahan);
    public Kelurahan ediKelurahan(String kodeKelurahan, KelurahanDto dto);
    public Kelurahan removeKelurahan(String kodeKelurahan);
    public Kelurahan getById(Integer id);
}
