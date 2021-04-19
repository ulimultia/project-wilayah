package com.week7.wilayahapi.controller;

import java.util.List;

import com.week7.wilayahapi.model.dto.KelurahanDto;
import com.week7.wilayahapi.model.dto.MessagesDto;
import com.week7.wilayahapi.model.entity.Kelurahan;
import com.week7.wilayahapi.service.KelurahanService;

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
public class KelurahanController {
    @Autowired
    private KelurahanService kelService;

    // CREATE kelurahan
    @PostMapping("/kelurahan/create")
    public ResponseEntity<?> createKelurahan(@RequestBody KelurahanDto dto){
        try {
            if(dto.getKodeKecamatan().length() != 6 ){
                MessagesDto<KelurahanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kecamatan harus 6 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getKodeKelurahan().length() != 10){
                MessagesDto<KelurahanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kelurahan harus 10 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getNamaKelurahan().isEmpty() == true){
                MessagesDto<KelurahanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Nama Kelurahan tidak boleh kosong ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            MessagesDto<Kelurahan> result = new MessagesDto<>();
            Kelurahan kelurahan = kelService.inputKelurahan(dto);

            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Kelurahan " + kelurahan.getNamaKelurahan() + " berhasil ditambahkan ...!");
            result.setData(kelurahan);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<KelurahanDto> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Data gagal ditambahkan ...! Error: " + e.getMessage());
            result.setData(dto);
            return ResponseEntity.badRequest().body(result);
        }
    }

    // READ kelurahan
    // get all kelurahan
    @GetMapping("/kelurahan/all")
    public ResponseEntity<?> getAllKelurahan(){
        try {
            MessagesDto<List<Kelurahan>> result = new MessagesDto<>();
            List<Kelurahan> kelurahans = kelService.getAllKelurahan();

            if(kelurahans.isEmpty() == false ){ // jika data kelurahan ditemukan
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data kelurahan berhasil didapatkan ... !");
                result.setData(kelurahans);
    
                return ResponseEntity.ok(result);
            }
            else{ // jika data kelurahan tidak ditemukan
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data kelurahan tidak ditemukan ... !");
                result.setData(kelurahans);
    
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            MessagesDto<Kelurahan> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // get by id
    @GetMapping("/kelurahan/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        try {
            MessagesDto<Kelurahan> result = new MessagesDto<>();
            Kelurahan kelurahan = kelService.getById(id);

            if(kelurahan != null){ // jika data kelurahan ditemukan
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data kelurahan berhasil didapatkan ... !");
                result.setData(kelurahan);
    
                return ResponseEntity.ok(result);
            }
            else{ // jika data kelurahan tidak ditemukan
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data kelurahan tidak ditemukan ... !");
                result.setData(kelurahan);
    
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            MessagesDto<Kelurahan> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
    // get by kode kelurahan
    @GetMapping("/kelurahan/{kodeKelurahan}")
    public ResponseEntity<?> getByKodeKelurahan(@PathVariable String kodeKelurahan){
        try {
            MessagesDto<Kelurahan> result = new MessagesDto<>();
            Kelurahan kelurahan = kelService.getByKodKelurahan(kodeKelurahan);

            if(kelurahan != null){ // jika data kelurahan ditemukan
                result.setStatus(HttpStatus.OK.value());
                result.setMessage("Data kelurahan berhasil didapatkan ... !");
                result.setData(kelurahan);
    
                return ResponseEntity.ok(result);
            }
            else{ // jika data kelurahan tidak ditemukan
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Data kelurahan tidak ditemukan ... !");
                result.setData(kelurahan);
    
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            MessagesDto<Kelurahan> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    // UPDATE kelurahan
    @PutMapping("/kelurahan/update/{kodeKelurahan}")
    public ResponseEntity<?> updateKelurahan (@PathVariable String kodeKelurahan, @RequestBody KelurahanDto dto){
        try {
            if(dto.getKodeKecamatan().length() != 6){
                MessagesDto<KelurahanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kecamatan harus 6 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getKodeKelurahan().length() != 10){
                MessagesDto<KelurahanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Kode Kelurahan harus 10 digit ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            if(dto.getNamaKelurahan().isEmpty() == true){
                MessagesDto<KelurahanDto> result = new MessagesDto<>();

                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Nama Kelurahan tidak boleh kosong ... !");
                result.setData(dto);

                return ResponseEntity.badRequest().body(result);
            }

            MessagesDto<Kelurahan> result = new MessagesDto<>();
            Kelurahan kelurahan = kelService.ediKelurahan(kodeKelurahan, dto);

            result.setStatus(HttpStatus.CREATED.value());
            result.setMessage("Kelurahan " + kelurahan.getNamaKelurahan() + " berhasil diubah ...!");
            result.setData(kelurahan);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<KelurahanDto> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Data gagal diubah ...! Error: " + e.getMessage());
            result.setData(dto);
            return ResponseEntity.badRequest().body(result);
        }
    }

    // DELETE kelurahan
    @DeleteMapping("/kelurahan/delete/{kodeKelurahan}")public ResponseEntity<?> deleteKelurahan(@PathVariable String kodeKelurahan){
        try {
            MessagesDto<Kelurahan> result = new MessagesDto<>();
            Kelurahan kelurahan = kelService.removeKelurahan(kodeKelurahan);

            result.setStatus(HttpStatus.OK.value());
            result.setMessage("Kelurahan " + kelurahan.getNamaKelurahan() + " berhasil dihapus ... !");
            result.setData(kelurahan);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            MessagesDto<Kelurahan> result = new MessagesDto<>();
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }
}
