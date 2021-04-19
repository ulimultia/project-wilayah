package com.week7.wilayahapi.service;

import java.util.List;

import com.week7.wilayahapi.model.dto.ProvinsiDto;
import com.week7.wilayahapi.model.dto.WilayahDto;
import com.week7.wilayahapi.model.entity.Provinsi;
import com.week7.wilayahapi.repository.ProvinsiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService{
    @Autowired
    private ProvinsiRepository provinsiRepo;

    @Override
    public Provinsi insertProvinsi(ProvinsiDto dto) {
        Provinsi provinsi = new Provinsi();

        provinsi.setKodeProvinsi(dto.getKodeProvinsi());
        provinsi.setNamaProvinsi(dto.getNamaProvinsi());
        provinsi.setIsDeleted(0); // status 0 = belum dihapus, status 1 = sudah dihapus
        provinsiRepo.save(provinsi);
        
        return provinsi;
    }

    @Override
    public List<Provinsi> getAllProvinsi() {
        List<Provinsi> provinsis = provinsiRepo.findByIsDeleted(0);
        return provinsis;
    }

    @Override
    public Provinsi getByKodeProvinsi(String kodeProvinsi) {
        return provinsiRepo.findByKodeProvinsi(kodeProvinsi);
    }

    @Override
    public Provinsi editProvinsi(String kodeProvinsi, ProvinsiDto dto) {
        Provinsi provinsi = provinsiRepo.findByKodeProvinsi(kodeProvinsi);

        provinsi.setKodeProvinsi(dto.getKodeProvinsi());
        provinsi.setNamaProvinsi(dto.getNamaProvinsi());
        provinsiRepo.save(provinsi);
        
        return provinsi;
    }

    @Override
    public Provinsi removeProvinsi(String kodeProvinsi) {
        Provinsi provinsi = provinsiRepo.findByKodeProvinsi(kodeProvinsi);
        provinsi.setIsDeleted(1); // status 1 = sudah dihapus
        provinsiRepo.save(provinsi);
        
        return provinsi;
    }

    @Override
    public List<WilayahDto> getAllWilayah() {
        List<WilayahDto> wilayah = null;


        return wilayah;
    }

    @Override
    public Provinsi getById(Integer id) {
        return provinsiRepo.findById(id).get();
    }
    
}
