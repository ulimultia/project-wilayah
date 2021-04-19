package com.week7.wilayahapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_kelurahan")
public class Kelurahan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String kodeKelurahan;

    @Column
    private String namaKelurahan;

    @Column(length = 1)
    private Integer isDeleted;

    @ManyToOne
    @JoinColumn(name = "kodeKecamatan", referencedColumnName = "kodeKecamatan")
    private Kecamatan kecamatan;
}
