package com.week7.wilayahapi.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_provinsi")
public class Provinsi implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2)
    private String kodeProvinsi;

    @Column
    private String namaProvinsi;

    @Column(length = 1)
    private Integer isDeleted;

    // @OneToMany(mappedBy = "provinsi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // private Set<Kabupaten> kabupatens;
}
