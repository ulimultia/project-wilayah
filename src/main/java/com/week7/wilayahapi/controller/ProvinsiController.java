package com.week7.wilayahapi.controller;

import java.util.List;

import com.week7.wilayahapi.model.dto.MessagesDto;
import com.week7.wilayahapi.model.dto.ProvinsiDto;
import com.week7.wilayahapi.model.entity.Provinsi;
import com.week7.wilayahapi.service.ProvinsiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wilayah-api-v1")
public class ProvinsiController {
    @Autowired
    private ProvinsiService provinsiService;

    // CREATE provinsi
    @PostMapping("/provinsi/create")
    public ResponseEntity<?> createProvinsi(@RequestBody ProvinsiDto dto){
        try {
            if(dto.getKodeProvinsi().length() != 2){
                MessagesDto<ProvinsiDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Provinsi harus 2 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getNamaProvinsi().isEmpty() == true){
                MessagesDto<ProvinsiDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Nama Provinsi tidak boleh kosong ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            // jika isi dto sudah sesuai ketentuan
            MessagesDto<Provinsi> result = new MessagesDto<>();
            Provinsi provinsi = provinsiService.insertProvinsi(dto);

            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Provinsi " + provinsi.getNamaProvinsi() + " berhasil ditambahkan ...!");
            result.setData(provinsi);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            MessagesDto<ProvinsiDto> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Data gagal ditambahkan ...! Error: " + e.getMessage());
            result.setData(dto);
            return ResponseEntity.badRequest().body(result);
        }
    }

    // READ provinsi
    // Get all provinsi
    @GetMapping("/provinsi/all")
    public ResponseEntity<?> getAllProvinsi(){
        try {
            List<Provinsi> provinsis = provinsiService.getAllProvinsi();
            MessagesDto<List<Provinsi>> result = new MessagesDto<>();

            if(provinsis.isEmpty() == false){
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data berjasil didapatkan!");
                result.setData(provinsis);
            }
            else{
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data tidak ditemukan!");
                result.setData(provinsis);
            }

            return ResponseEntity.ok(result);
    
        } catch (Exception e) {
            MessagesDto<Provinsi> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // Get by kode provinsi
    @GetMapping("/provinsi/{kodeProvinsi}")
    public ResponseEntity<?> getByKodeProvinasi(@PathVariable String kodeProvinsi){
        try {
            MessagesDto<Provinsi> result = new MessagesDto<>();
            Provinsi provinsi = provinsiService.getByKodeProvinsi(kodeProvinsi);

            if(provinsi != null){ // jika data provinsi ditemukan
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data provinsi berhasil didapatkan ... !");
                result.setData(provinsi);
    
                return ResponseEntity.ok(result);
            }
            else{ // jika data provinsi tidak ditemukan
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data provinsi tidak ditemukan ... !");
                result.setData(provinsi);
    
                return ResponseEntity.badRequest().body(result);
            }

        } catch (Exception e) {
            MessagesDto<Provinsi> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // DAFTAR WILAYAH === get all data wilayah with group
    @GetMapping("/wilayah")
    public ResponseEntity<?> getAllWilayahAktif(){
        try {
            List<Provinsi> provinsis = provinsiService.getAllProvinsi();
            MessagesDto<List<Provinsi>> result = new MessagesDto<>();

            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Data berjasil didapatkan!");
            result.setData(provinsis);

            return ResponseEntity.ok(result);
    
        } catch (Exception e) {
            MessagesDto<Provinsi> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // UPDATE provinsi
    @PutMapping("/provinsi/update/{kodeProvinsi}")
    public ResponseEntity<?> updateProvinsi(@PathVariable String kodeProvinsi, @RequestBody ProvinsiDto dto){
        try {
            // mengecek isi kode provinsi dari dto
            if(dto.getKodeProvinsi().length() != 2){
                MessagesDto<ProvinsiDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Provinsi harus 2 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            // mengecek nama provinsi dari dto
            if(dto.getNamaProvinsi().isEmpty() == true){
                MessagesDto<ProvinsiDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Nama Provinsi tidak boleh kosong ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            // jika lolos pengecekan
            MessagesDto<Provinsi> result = new MessagesDto<>();
            Provinsi provinsi = provinsiService.editProvinsi(kodeProvinsi, dto);

            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Provinsi " + dto.getNamaProvinsi() + " berhasil diubah menjadi " + provinsi.getNamaProvinsi()
            + " ...!");
            result.setData(provinsi);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<ProvinsiDto> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Data gagal diubah ... ! Error: " + e.getMessage());
            result.setData(dto);
            return ResponseEntity.badRequest().body(result);
        }
    }

    // DELETE provinsi
    @DeleteMapping("/provinsi/delete/{kodeProvinsi}")
    public ResponseEntity<?> deleteProvinsi(@PathVariable String kodeProvinsi){
        try {
            MessagesDto<Provinsi> result = new MessagesDto<>();
            Provinsi provinsi = provinsiService.removeProvinsi(kodeProvinsi);

            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Provinsi " + provinsi.getNamaProvinsi() + " berhasil dihapus ... !");
            result.setData(provinsi);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<Provinsi> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
