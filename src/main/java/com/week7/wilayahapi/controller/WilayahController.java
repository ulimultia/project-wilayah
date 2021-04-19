package com.week7.wilayahapi.controller;

import java.util.ArrayList;
import java.util.List;

import com.week7.wilayahapi.model.dto.KabupatenDto;
import com.week7.wilayahapi.model.dto.KecamatanDto;
import com.week7.wilayahapi.model.dto.KelurahanDto;
import com.week7.wilayahapi.model.dto.MessagesDto;
import com.week7.wilayahapi.model.dto.ProvinsiDto;
import com.week7.wilayahapi.model.dto.WilayahDto;
import com.week7.wilayahapi.model.entity.Kabupaten;
import com.week7.wilayahapi.model.entity.Kecamatan;
import com.week7.wilayahapi.model.entity.Kelurahan;
import com.week7.wilayahapi.model.entity.Provinsi;
import com.week7.wilayahapi.service.KabupatenService;
import com.week7.wilayahapi.service.KecamatanService;
import com.week7.wilayahapi.service.KelurahanService;
import com.week7.wilayahapi.service.ProvinsiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class WilayahController {
    @Autowired
    private ProvinsiService provService;
    @Autowired
    private KabupatenService kabService;
    @Autowired
    private KecamatanService kecService;
    @Autowired 
    private KelurahanService kelService;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try {
            List<ProvinsiDto> provinsiDtos = new ArrayList<>();
            List<Provinsi> provinsis = provService.getAllProvinsi();
            for (Provinsi provinsi : provinsis) {
                // list yang digunakan untuk menampung dto kabupaten yang ada diprovinsi terkait
                List<KabupatenDto> kabupatenDtos = new ArrayList<>();
                // list yang digunakan untuk menampung daftar kabupaten yang ada diprovinsi terkait
                List<Kabupaten> kabupatens = kabService.getAllKabupaten();
                // perulangan untuk meamsukan entity dari kabupaten ke list kabupaten dto
                for (Kabupaten kabupaten : kabupatens) {
                    // list untuk menampung dto kecamatan yang ada dikabupaten tersebut
                    List<KecamatanDto> kecamatanDtos = new ArrayList<>();
                    // menampung daftar kecamatan berdasarkan kode kabupaten yang sedang aktif
                    List<Kecamatan> kecamatans = kecService.getByKabupaten(kabupaten.getKodeKabupaten());
                    // perulangan untuk memasukan tiap kecamatan dari entity ke kecamatan dto
                    for(Kecamatan kecamatan : kecamatans){
                        // buat list untuk menampung data kelurahan yang memiliki kode kecamatan yang sama dengan kode kecamatan yang sedang aktif di looping tersebut
                        List<KelurahanDto> kelurahanDtos = new ArrayList<>();
                        // perulangan untuk mencari kelurahan dengan kode kecamatan terkait
                        List<Kelurahan> kelurahans = kelService.getByKecamatan(kecamatan.getKodeKecamatan());
                        // perualngan untuk memasukan tiap data kelurahan entity ke dalam list kelurahahn dto
                        for (Kelurahan kelurahan : kelurahans){
                            // objek baru untuk menyimpan data dari entity ke dto kelurahan 
                            KelurahanDto dto = new KelurahanDto();
                            dto.setKodeKelurahan(kelurahan.getKodeKelurahan());
                            dto.setNamaKelurahan(kelurahan.getNamaKelurahan());
                            dto.setKodeKecamatan(kecamatan.getKodeKecamatan());
                            // menyimpan data dari dto ke dalam list kelurarahn dto
                            kelurahanDtos.add(dto);
                        }
    
                        // objek kecamatan yang akan diinpukan ke dalam list kecamatan di kelas dto kabupaten
                        KecamatanDto kecDto = new KecamatanDto();
                        kecDto.setKodeKecamatan(kecamatan.getKodeKecamatan());
                        kecDto.setNamaKecamatan(kecamatan.getNamaKecamatan());
                        kecDto.setKodeKabupaten(kabupaten.getKodeKabupaten());
                        kecDto.setKelurahan(kelurahanDtos);
                        // menyimpan data dari dto ke dalam list kecamatan dto
                        kecamatanDtos.add(kecDto);
                    }
    
                    // objek kabupaten yang akan diinputkan ke dalam list kabupaten di kelas dto provinsi
                    KabupatenDto kabDto = new KabupatenDto();
                    kabDto.setKodeKabupaten(kabupaten.getKodeKabupaten());
                    kabDto.setNamaKabupaten(kabupaten.getNamaKabupaten());
                    kabDto.setKodeProvinsi(kabupaten.getProvinsi().getKodeProvinsi());
                    kabDto.setKecamatan(kecamatanDtos);
                    // menyimpan data dari dto ke list kabupaten dto
                    kabupatenDtos.add(kabDto);
                }
    
                // objek provinsi yang akan diinputkan ke dalam list provinsi di kelas dto wilayah
                ProvinsiDto provDto = new ProvinsiDto();
                provDto.setKodeProvinsi(provinsi.getKodeProvinsi());
                provDto.setNamaProvinsi(provinsi.getNamaProvinsi());
                provDto.setKabupaten(kabupatenDtos);
                // menyimpan data dari dto ke list provinsi dto
                provinsiDtos.add(provDto);
            }
    
            MessagesDto<List<ProvinsiDto>> result = new MessagesDto<>();
            result.setStatus(HttpStatus.OK.value());
            result.setMessage("success");
            result.setData(provinsiDtos);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            MessagesDto<WilayahDto> error = new MessagesDto<>();
            error.setStatus(HttpStatus.BAD_REQUEST.value());
            error.setMessage(e.getMessage());
            error.setData(null);
            return ResponseEntity.badRequest().body(error);
        }
    }
}
