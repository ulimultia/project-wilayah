package com.week7.wilayahapi.repository;

import java.util.List;
import java.util.Optional;

import com.week7.wilayahapi.model.entity.Kecamatan;
import com.week7.wilayahapi.model.entity.Kelurahan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KelurahanRepository extends JpaRepository<Kelurahan, Integer>{
    // cari data kelurahan berdasarkan kodenya
    @Query(value = "select * from tbl_kelurahan where is_deleted = 0 AND kode_kelurahan = ?", nativeQuery = true)
    public Kelurahan findByKodeKelurahan(String kodeKelurahan);
    
    // tampil semua berdasarkan is deletednya
    // @Query(value = "select * from tbl_kelurahan where is_deleted = 0", nativeQuery = true)
    public List<Kelurahan> findByIsDeleted(Integer isDeleted);

    public List<Kelurahan> findByKecamatan(Kecamatan kecamatan); 

    public Optional<Kelurahan> findById(Integer id);

}
