package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.entity.Provinsi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v2/testing")
public class TestingApi {

    @PostMapping("/{id}/findById")
    public ResponseEntity<?> testing(
            @PathVariable("id") String id,
            @RequestParam("authentication") String auth,
            @RequestBody Provinsi provinsi) {
        log.info("{id: {}, auth: {}, stateProvince: {}}", id, auth, provinsi);
        return ResponseEntity.ok(provinsi);
    }
}
