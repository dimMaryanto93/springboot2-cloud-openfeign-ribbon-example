package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.Provinsi;
import com.maryanto.dimas.example.proxy.WilayahServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/testing")
public class TestingProxyApi {

    @Autowired
    private WilayahServiceProxy serviceProxy;

    @GetMapping("/test")
    public ResponseEntity<?> testing(){
        ResponseEntity<Provinsi> propinsiResponse = this.serviceProxy.findEmployeeById("111", "dimas", new Provinsi("32", "Jawa Barat"));
        return ResponseEntity.ok(propinsiResponse.getBody());
    }
}
