package com.week7.wilayahapi.service;

import java.util.List;

import com.week7.wilayahapi.model.dto.KabupatenDto;
import com.week7.wilayahapi.model.entity.Kabupaten;
import com.week7.wilayahapi.model.entity.Provinsi;
import com.week7.wilayahapi.repository.KabupatenRepository;
import com.week7.wilayahapi.repository.ProvinsiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KabupatenServiceImpl implements KabupatenService{
    @Autowired 
    private KabupatenRepository kabRepo;
    @Autowired
    private ProvinsiRepository provRepo;

    @Override
    public Kabupaten insertKabupaten(KabupatenDto dto) {
        Kabupaten kabupaten = new Kabupaten();
        Provinsi provinsi = provRepo.findByKodeProvinsi(dto.getKodeProvinsi());

        kabupaten.setKodeKabupaten(dto.getKodeKabupaten());
        kabupaten.setNamaKabupaten(dto.getNamaKabupaten());
        kabupaten.setIsDeleted(0);// status 0 = belum dihapus, status 1 = sudah dihapus

        kabupaten.setProvinsi(provinsi);
        kabRepo.save(kabupaten);

        return kabupaten;
    }

    @Override
    public List<Kabupaten> getAllKabupaten() {
        return kabRepo.findByIsDeleted(0);
    }

    @Override
    public Kabupaten getByKodeKabupaten(String kodeKabupaten) {
        return kabRepo.findByKodeKabupaten(kodeKabupaten);
    }

    @Override
    public Kabupaten ediKabupaten(String kodeKabupaten, KabupatenDto dto) {
        Kabupaten kabupaten = kabRepo.findByKodeKabupaten(kodeKabupaten);
        Provinsi provinsi = provRepo.findByKodeProvinsi(dto.getKodeProvinsi());

        kabupaten.setKodeKabupaten(dto.getKodeKabupaten());
        kabupaten.setNamaKabupaten(dto.getNamaKabupaten());
        kabupaten.setProvinsi(provinsi);
        kabRepo.save(kabupaten);
        
        return kabupaten;
    }

    @Override
    public Kabupaten remoKabupaten(String kodeKabupaten) {
        Kabupaten kabupaten = kabRepo.findByKodeKabupaten(kodeKabupaten);
        kabupaten.setIsDeleted(1);
        kabRepo.save(kabupaten);

        return kabupaten;
    }

    @Override
    public Kabupaten getById(Integer id) {
        return kabRepo.findById(id).get();
    }

    @Override
    public List<Kabupaten> getByProvinsi(String kodeProvinsi) {
        Provinsi provinsi = provRepo.findByKodeProvinsi(kodeProvinsi);
        return kabRepo.findByProvinsi(provinsi);
    }
    
}
