package com.week7.wilayahapi.controller;

import java.util.List;

import com.week7.wilayahapi.model.dto.KabupatenDto;
import com.week7.wilayahapi.model.dto.MessagesDto;
import com.week7.wilayahapi.model.entity.Kabupaten;
import com.week7.wilayahapi.service.KabupatenService;

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
public class KabupatenController {
    @Autowired
    private KabupatenService kabService;

    // CREATE kabupaten
    @PostMapping("/kabupaten/create")
    public ResponseEntity<?> createKabupaten(@RequestBody KabupatenDto dto){
        try {
            if(dto.getKodeProvinsi().length() != 2){
                MessagesDto<KabupatenDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Provinsi harus 2 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getKodeKabupaten().length() != 4){
                MessagesDto<KabupatenDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kabupaten harus 4 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getNamaKabupaten().isEmpty() == true){
                MessagesDto<KabupatenDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Nama Kabupaten/Kota tidak boleh kosong ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            MessagesDto<Kabupaten> result = new MessagesDto<>();
            Kabupaten kabupaten = kabService.insertKabupaten(dto);

            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Kabupaten/Kota " + kabupaten.getNamaKabupaten() + " berhasil ditambahkan ...!");
            result.setData(kabupaten);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<KabupatenDto> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Data gagal ditambahkan ...! Error: " + e.getMessage());
            result.setData(dto);
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    // READ kabupaten
    // get all kabupaten
    @GetMapping("/kabupaten/all")
    public ResponseEntity<?> getAllKabupaten(){
        try {
            MessagesDto<List<Kabupaten>> result = new MessagesDto<>();
            List<Kabupaten> kabupatens = kabService.getAllKabupaten();

            if(kabupatens.isEmpty() == false){ // jika data kabupaten ditemukan
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data kabupaten berhasil didapatkan ... !");
                result.setData(kabupatens);
    
                return ResponseEntity.ok(result);
            }
            else{ // jika data kabupaten tidak ditemukan
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data kabupaten tidak ditemukan ... !");
                result.setData(kabupatens);
    
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            MessagesDto<Kabupaten> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // get by kode kabupaten
    @GetMapping("kabupaten/{kodeKabupaten}")
    public ResponseEntity<?> getByKodeKabupaten(@PathVariable String kodeKabupaten){
        try {
            MessagesDto<Kabupaten> result = new MessagesDto<>();
            Kabupaten kabupaten = kabService.getByKodeKabupaten(kodeKabupaten);

            if(kabupaten != null){ // jika data kabupaten ditemukan
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data kabupaten berhasil didapatkan ... !");
                result.setData(kabupaten);
    
                return ResponseEntity.ok(result);
            }
            else{ // jika data kabupaten tidak ditemukan
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data kabupaten tidak ditemukan ... !");
                result.setData(kabupaten);
    
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            MessagesDto<Kabupaten> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // UPDATE kabupaten
    @PutMapping("/kabupaten/update/{kodeKabupaten}")
    public ResponseEntity<?> updateKabupaten(@PathVariable String kodeKabupaten, @RequestBody KabupatenDto dto){
        try {
            if(dto.getKodeProvinsi().length() != 2){
                MessagesDto<KabupatenDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Provinsi harus 2 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getKodeKabupaten().length() != 4){
                MessagesDto<KabupatenDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kabupaten harus 4 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getNamaKabupaten().isEmpty() == true){
                MessagesDto<KabupatenDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Nama Kabupaten/Kota tidak boleh kosong ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            // jika lolos pengecekan
            MessagesDto<Kabupaten> result = new MessagesDto<>();
            Kabupaten kabupaten = kabService.ediKabupaten(kodeKabupaten, dto);

            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Kabupaten " + dto.getNamaKabupaten() + " berhasil diubah ...!");
            result.setData(kabupaten);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<KabupatenDto> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Data gagal diubah ... ! Error: " + e.getMessage());
            result.setData(dto);
            return ResponseEntity.badRequest().body(result);
        }
    }

    // DELETE kabupaten
    @DeleteMapping("/kabupaten/delete/{kodeKabupaten}")
    public ResponseEntity<?> deleteKabupaten(@PathVariable String kodeKabupaten){
        try {
            MessagesDto<Kabupaten> result = new MessagesDto<>();
            Kabupaten kabupaten = kabService.remoKabupaten(kodeKabupaten);

            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Kabupaten " + kabupaten.getNamaKabupaten() + " berhasil dihapus ... !");
            result.setData(kabupaten);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<Kabupaten> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
