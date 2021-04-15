package com.week7.wilayahapi.repository;

import com.week7.wilayahapi.model.entity.Kelurahan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelurahanRepository extends JpaRepository<Kelurahan, Integer>{
    // cari data kelurahan berdasarkan kodenya
    public Kelurahan findByKodeKelurahan(String kodeKelurahan);
}
