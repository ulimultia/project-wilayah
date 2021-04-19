package com.week7.wilayahapi.repository;

import java.util.List;
import java.util.Optional;

import com.week7.wilayahapi.model.entity.Provinsi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinsiRepository extends JpaRepository<Provinsi, Integer>{
    // cari berdasarkan kode provinsi
    @Query(value = "SELECT * FROM tbl_provinsi WHERE is_deleted = 0 AND kode_provinsi = ?", nativeQuery = true)
    public Provinsi findByKodeProvinsi(String kodeProvinsi);

    // tampil semua berdasarkan is deletednya
    // @Query(value = "SELECT * FROM tbl_provinsi WHERE is_deleted = 0", nativeQuery = true)
    public List<Provinsi> findByIsDeleted(Integer isDeleted);

    public Optional<Provinsi> findById(Integer id);
}
