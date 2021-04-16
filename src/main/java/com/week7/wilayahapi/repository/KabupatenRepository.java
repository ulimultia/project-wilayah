package com.week7.wilayahapi.repository;

import java.util.List;

import com.week7.wilayahapi.model.entity.Kabupaten;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KabupatenRepository extends JpaRepository<Kabupaten, Integer>{
    //  cari berdasarkan kode kabupaten yang belum dihapus
    @Query(value = "select * from tbl_kabupaten where is_deleted = 0 and kode_kabupaten = ?", nativeQuery = true)
    public Kabupaten findByKodeKabupaten(String kodeKabupaten);

    // tampil semua data yang berdasarkan is deletednya
    // @Query(value = "select * from tbl_kabupaten where is_deleted = 0", nativeQuery = true)
    public List<Kabupaten> findByIsDeleted(Integer isDeleted);

}
