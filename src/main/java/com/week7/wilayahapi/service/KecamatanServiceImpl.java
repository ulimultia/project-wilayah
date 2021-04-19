package com.week7.wilayahapi.service;

import java.util.List;

import com.week7.wilayahapi.model.dto.KecamatanDto;
import com.week7.wilayahapi.model.entity.Kabupaten;
import com.week7.wilayahapi.model.entity.Kecamatan;
import com.week7.wilayahapi.repository.KabupatenRepository;
import com.week7.wilayahapi.repository.KecamatanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KecamatanServiceImpl implements KecamatanService{
    @Autowired 
    private KecamatanRepository kecRepo;
    @Autowired
    private KabupatenRepository kabRepo;

    @Override
    public Kecamatan inputKecamatan(KecamatanDto dto) {
        Kecamatan kecamatan = new Kecamatan();
        Kabupaten kabupaten = kabRepo.findByKodeKabupaten(dto.getKodeKabupaten());

        kecamatan.setKodeKecamatan(dto.getKodeKecamatan());
        kecamatan.setNamaKecamatan(dto.getNamaKecamatan());
        kecamatan.setKabupaten(kabupaten);
        kecamatan.setIsDeleted(0);// status 0 = belum dihapus, status 1 = sudah dihapus
        kecRepo.save(kecamatan);

        return kecamatan;
    }

    @Override
    public List<Kecamatan> getAllKecamatan() {
        return kecRepo.findByIsDeleted(0);
    }

    @Override
    public Kecamatan getByKodKecamatan(String kodeKecamatan) {
        return kecRepo.findByKodeKecamatan(kodeKecamatan);
    }

    @Override
    public Kecamatan ediKecamatan(String kodeKecamatan, KecamatanDto dto) {
        Kabupaten kabupaten = kabRepo.findByKodeKabupaten(dto.getKodeKabupaten());
        Kecamatan kecamatan = kecRepo.findByKodeKecamatan(kodeKecamatan);

        kecamatan.setKodeKecamatan(dto.getKodeKecamatan());
        kecamatan.setNamaKecamatan(dto.getNamaKecamatan());
        kecamatan.setKabupaten(kabupaten);
        kecRepo.save(kecamatan);

        return kecamatan;
    }

    @Override
    public Kecamatan removeKecamatan(String kodeKecamatan) {
        Kecamatan kecamatan = kecRepo.findByKodeKecamatan(kodeKecamatan);
        kecamatan.setIsDeleted(1);
        kecRepo.save(kecamatan);

        return kecamatan;
    }

    @Override
    public Kecamatan getById(Integer id) {
        return kecRepo.findById(id).get();
    }

    @Override
    public List<Kecamatan> getByKabupaten(String kodeKabupaten) {
        Kabupaten kabupaten = kabRepo.findByKodeKabupaten(kodeKabupaten);
        return kecRepo.findByKabupaten(kabupaten);
    }
    
}
