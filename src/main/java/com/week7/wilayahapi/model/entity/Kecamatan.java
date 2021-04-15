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
@Table(name = "tbl_kecamatan")
public class Kecamatan implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 6)
    private String kodeKecamatan;

    @Column
    private String namaKecamatan;

    @Column(length = 1)
    private Integer isDeleted;

    @ManyToOne
    @JoinColumn(name = "kodeKabupaten", referencedColumnName = "kodeKabupaten")
    private Kabupaten kabupaten;

    // @OneToMany(mappedBy = "kecamatan")
    // private List<Kelurahan> kelurahans;


}
