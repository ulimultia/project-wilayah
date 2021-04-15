package com.week7.wilayahapi.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_kabupaten")
public class Kabupaten implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 4)
    private String kodeKabupaten;

    @Column
    private String namaKabupaten;

    @Column(length = 1)
    private Integer isDeleted;

    // relasi many to one dengan provinsi
    @ManyToOne
    @JoinColumn(name = "kodeProvinsi", referencedColumnName = "kodeProvinsi")
    private Provinsi provinsi;

    // relasi one to many dengan kecamatan
    // @OneToMany(mappedBy = "kabupaten")
    // private List<Kecamatan> kecamatans;
   
}
