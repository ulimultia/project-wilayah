package com.week7.wilayahapi.repository;

import com.week7.wilayahapi.model.entity.Kecamatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Integer>{
    // cari berdasarkan kode kecamatan
    public Kecamatan findByKodeKecamatan(String kodeKecamatan);
    
}
