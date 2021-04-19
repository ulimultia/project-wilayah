package com.week7.wilayahapi.repository;

import java.util.List;
import java.util.Optional;

import com.week7.wilayahapi.model.entity.Kabupaten;
import com.week7.wilayahapi.model.entity.Kecamatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KecamatanRepository extends JpaRepository<Kecamatan, Integer>{
    // cari berdasarkan kode kecamatan
    @Query(value = "select * from tbl_kecamatan where is_deleted = 0 and kode_kecamatan = ?", nativeQuery = true)
    public Kecamatan findByKodeKecamatan(String kodeKecamatan);

    // tampil semua berdasarkan is deletednya
    // @Query(value = "select * from tbl_kecamatan where is_deleted = 0", nativeQuery = true)
    public List<Kecamatan> findByIsDeleted(Integer isDeleted);

    public List<Kecamatan> findByKabupaten(Kabupaten kabupaten);
    
    @Query(value = "select * from tbl_kecamatan where is_deleted = 0 and id = ?", nativeQuery = true)
    public Optional<Kecamatan> findById(Integer id);
}
