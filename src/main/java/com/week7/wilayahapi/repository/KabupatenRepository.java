package com.week7.wilayahapi.repository;

import com.week7.wilayahapi.model.entity.Kabupaten;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KabupatenRepository extends JpaRepository<Kabupaten, Integer>{
    //  cari berdasarkan kode kabupaten
    public Kabupaten findByKodeKabupaten(String kodeKabupaten);
}
