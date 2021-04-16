package com.week7.wilayahapi.controller;

import java.util.List;

import com.week7.wilayahapi.model.dto.KecamatanDto;
import com.week7.wilayahapi.model.dto.MessagesDto;
import com.week7.wilayahapi.model.entity.Kecamatan;
import com.week7.wilayahapi.service.KecamatanService;

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
public class KecamatanController {
    @Autowired
    private KecamatanService kecService;

    // CREATE kecamatan
    @PostMapping("/kecamatan/create")
    public ResponseEntity<?> createKecamatan(@RequestBody KecamatanDto dto){
        try {
            if(dto.getKodeKabupaten().length() != 4){
                MessagesDto<KecamatanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kabupaten harus 4 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getKodeKecamatan().length() != 6){
                MessagesDto<KecamatanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kecamatan harus 6 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getNamaKecamatan().isEmpty() == true){
                MessagesDto<KecamatanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Nama Kecamatan tidak boleh kosong ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            MessagesDto<Kecamatan> result = new MessagesDto<>();
            Kecamatan kecamatan = kecService.inputKecamatan(dto);

            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Kecamatan " + kecamatan.getNamaKecamatan() + " berhasil ditambahkan ...!");
            result.setData(kecamatan);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<KecamatanDto> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Data gagal ditambahkan ...! Error: " + e.getMessage());
            result.setData(dto);
            return ResponseEntity.badRequest().body(result);
        }
    }

    // READ kecamatan
    // get all kecamatan
    @GetMapping("/kecamatan/all")
    public ResponseEntity<?> getAllKecamatan(){
        try {
            MessagesDto<List<Kecamatan>> result = new MessagesDto<>();
            List<Kecamatan> kecamatans = kecService.getAllKecamatan();

            if(kecamatans.isEmpty() == false){ // jika data kecamatan ditemukan
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data kecamatan berhasil didapatkan ... !");
                result.setData(kecamatans);
    
                return ResponseEntity.ok(result);
            }
            else{ // jika data kecamatan tidak ditemukan
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data kecamatan tidak ditemukan ... !");
                result.setData(kecamatans);
    
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            MessagesDto<Kecamatan> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // get by kode kecamatan
    @GetMapping("/kecamatan/{kodeKecamatan}")
    public ResponseEntity<?> getByKodeKecamatan(@PathVariable String kodeKecamatan){
        try {
            MessagesDto<Kecamatan> result = new MessagesDto<>();
            Kecamatan kecamatan = kecService.getByKodKecamatan(kodeKecamatan);

            if(kecamatan != null){ // jika data kecamatan ditemukan
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data kecamatan berhasil didapatkan ... !");
                result.setData(kecamatan);
    
                return ResponseEntity.ok(result);
            }
            else{ // jika data kecamatan tidak ditemukan
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data kecamatan tidak ditemukan ... !");
                result.setData(kecamatan);
    
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            MessagesDto<Kecamatan> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // UPDATE kecamatan
    @PutMapping("/kecamatan/update/{kodeKecamatan}")
    public ResponseEntity<?> updateKecamatan(@PathVariable String kodeKecamatan, @RequestBody KecamatanDto dto){
        try {
            if(dto.getKodeKabupaten().length() != 4){
                MessagesDto<KecamatanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kabupaten harus 4 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getKodeKecamatan().length() != 6){
                MessagesDto<KecamatanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kecamatan harus 6 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getNamaKecamatan().isEmpty() == true){
                MessagesDto<KecamatanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Nama Kecamatan tidak boleh kosong ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            MessagesDto<Kecamatan> result = new MessagesDto<>();
            Kecamatan kecamatan = kecService.ediKecamatan(kodeKecamatan, dto);

            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Kecamatan " + kecamatan.getNamaKecamatan() + " berhasil diubah ...!");
            result.setData(kecamatan);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<KecamatanDto> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Data gagal diubah ...! Error: " + e.getMessage());
            result.setData(dto);
            return ResponseEntity.badRequest().body(result);
        }
    }

    // DELETE kecematan
    @DeleteMapping("/kecamatan/delete/{kodeKecamatan}")
    public ResponseEntity<?> deleteKecamatan(@PathVariable String kodeKecamatan){
        try {
            MessagesDto<Kecamatan> result = new MessagesDto<>();
            Kecamatan kecamatan = kecService.removeKecamatan(kodeKecamatan);

            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Kecamatan " + kecamatan.getNamaKecamatan() + " berhasil dihapus ... !");
            result.setData(kecamatan);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<Kecamatan> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
