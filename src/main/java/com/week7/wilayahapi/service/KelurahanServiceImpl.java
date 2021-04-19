package com.week7.wilayahapi.service;

import java.util.List;

import com.week7.wilayahapi.model.dto.KelurahanDto;
import com.week7.wilayahapi.model.entity.Kecamatan;
import com.week7.wilayahapi.model.entity.Kelurahan;
import com.week7.wilayahapi.repository.KecamatanRepository;
import com.week7.wilayahapi.repository.KelurahanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KelurahanServiceImpl implements KelurahanService{
    @Autowired
    private KelurahanRepository kelRepo;
    @Autowired
    private KecamatanRepository kecRepo;

    @Override
    public Kelurahan inputKelurahan(KelurahanDto dto) {
        Kelurahan kelurahan = new Kelurahan();
        Kecamatan kecamatan = kecRepo.findByKodeKecamatan(dto.getKodeKecamatan());

        kelurahan.setKodeKelurahan(dto.getKodeKelurahan());
        kelurahan.setNamaKelurahan(dto.getNamaKelurahan());
        kelurahan.setKecamatan(kecamatan);
        kelurahan.setIsDeleted(0);// status 0 = belum dihapus, status 1 = sudah dihapus
        kelRepo.save(kelurahan);

        return kelurahan;
    }

    @Override
    public List<Kelurahan> getAllKelurahan() {
        return kelRepo.findByIsDeleted(0);
    }

    @Override
    public Kelurahan getByKodKelurahan(String kodeKelurahan) {
        return kelRepo.findByKodeKelurahan(kodeKelurahan);
    }

    @Override
    public Kelurahan ediKelurahan(String kodeKelurahan, KelurahanDto dto) {
        Kecamatan kecamatan = kecRepo.findByKodeKecamatan(dto.getKodeKecamatan());
        Kelurahan kelurahan = kelRepo.findByKodeKelurahan(kodeKelurahan);

        kelurahan.setKodeKelurahan(dto.getKodeKelurahan());
        kelurahan.setNamaKelurahan(dto.getNamaKelurahan());
        kelurahan.setKecamatan(kecamatan);
        kelRepo.save(kelurahan);

        return kelurahan;
    }

    @Override
    public Kelurahan removeKelurahan(String kodeKelurahan) {
        Kelurahan kelurahan = kelRepo.findByKodeKelurahan(kodeKelurahan);
        kelurahan.setIsDeleted(1);
        kelRepo.save(kelurahan);

        return kelurahan;
    }

    @Override
    public Kelurahan getById(Integer id) {
        return kelRepo.findById(id).get();
    }

    @Override
    public List<Kelurahan> getByKecamatan(String kodeKecamatan) {
        Kecamatan kecamatan = kecRepo.findByKodeKecamatan(kodeKecamatan);
        return kelRepo.findByKecamatan(kecamatan);
    }
    
}
