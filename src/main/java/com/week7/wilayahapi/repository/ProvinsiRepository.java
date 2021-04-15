package com.week7.wilayahapi.repository;

import java.util.List;

import com.week7.wilayahapi.model.entity.Provinsi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinsiRepository extends JpaRepository<Provinsi, Integer>{
    // cari berdasarkan kode provinsi
    public Provinsi findByKodeProvinsi(String kodeProvinsi);

    @Query(value = "SELECT kode_provinsi FROM tbl_provinsi", nativeQuery = true)
    public List<Provinsi> getDataProvinsiAktif();
}
