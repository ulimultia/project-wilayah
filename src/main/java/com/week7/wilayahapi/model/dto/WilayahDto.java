package com.week7.wilayahapi.model.dto;

import java.util.List;

import com.week7.wilayahapi.model.entity.Kabupaten;
import com.week7.wilayahapi.model.entity.Provinsi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WilayahDto {
    private List<Provinsi> provinsis;
    private List<Kabupaten> kabupatens; 

}
