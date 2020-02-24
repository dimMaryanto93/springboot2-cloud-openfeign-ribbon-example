package com.maryanto.dimas.example.proxy;

import com.maryanto.dimas.example.dto.Provinsi;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "spring-cloud-wilayah-service")
@RibbonClient(name = "spring-cloud-wilayah-service")
public interface WilayahServiceProxy {

    @PostMapping("/spring-cloud-wilayah-service/api/v2/testing/{id}/findById")
    ResponseEntity<Provinsi> findEmployeeById(
            @PathVariable("id") String id,
            @RequestParam("authentication") String auth,
            @RequestBody Provinsi provinsi);

}
