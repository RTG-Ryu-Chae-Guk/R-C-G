package com.rcg.citydata.controller;

import com.rcg.citydata.dto.CityDataDto;
import com.rcg.citydata.service.CityDataBatchService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/city")
public class CityDataBatchController {

  private final CityDataBatchService batchService;

  public CityDataBatchController(CityDataBatchService batchService) {
    this.batchService = batchService;
  }

  @GetMapping("/{areaCd}")
  @Operation(summary = "areaCd에 따른 데이터 조회")
  public ResponseEntity<CityDataDto> getByAreaCd(@PathVariable String areaCd) {
    try {
      CityDataDto dto = batchService.loadByAreaCd(areaCd);
      return ResponseEntity.ok(dto);
    } catch (IOException e) {
      return ResponseEntity.status(500).build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/by-name/{areaNm}")
  @Operation(summary = "areaNm에 따른 데이터 조회")
  public ResponseEntity<CityDataDto> getByAreaNm(@PathVariable String areaNm) {
    try {
      CityDataDto dto = batchService.loadByAreaNm(areaNm);
      return ResponseEntity.ok(dto);
    } catch (IOException e) {
      return ResponseEntity.status(500).build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
