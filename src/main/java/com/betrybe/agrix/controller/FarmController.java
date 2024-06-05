package com.betrybe.agrix.controller;

import com.betrybe.agrix.model.Farm;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  @Autowired
  private FarmService farmService;

  /**
   * Create farm response entity.
   *
   * @param farm the farm
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
    Farm createdFarm = farmService.createFarm(farm);
    return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public ResponseEntity<List<Farm>> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();
    return new ResponseEntity<>(farms, HttpStatus.OK);
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);
    if (farm == null) {
      return new ResponseEntity<>("Fazenda não encontrada!", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(farm, HttpStatus.OK);
  }
}
