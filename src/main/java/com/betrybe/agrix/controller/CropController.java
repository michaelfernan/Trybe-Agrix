package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.model.Crop;
import com.betrybe.agrix.model.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/")
public class CropController {

  @Autowired
  private CropService cropService;

  /**
   * Create crop response entity.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the response entity
   */
  @PostMapping("/farms/{farmId}/crops")
  public ResponseEntity<Object> createCrop(@PathVariable Long farmId, @RequestBody Crop crop) {
    try {
      CropDto createdCrop = cropService.createCrop(farmId, crop);
      return new ResponseEntity<>(createdCrop, HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets crops by farm.
   *
   * @param farmId the farm id
   * @return the crops by farm
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<Object> getCropsByFarm(@PathVariable Long farmId) {
    try {
      List<CropDto> crops = cropService.getCropsByFarm(farmId);
      return new ResponseEntity<>(crops, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @GetMapping("/farms/{farmId}/crops/{id}")
  public ResponseEntity<Object> getCropById(@PathVariable Long id) {
    try {
      CropDto crop = cropService.getCropById(id);
      return new ResponseEntity<>(crop, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Add fertilizer to crop response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   */
  @PostMapping("/crops/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<Object> addFertilizerToCrop(
      @PathVariable Long cropId, @PathVariable Long fertilizerId) {
    try {
      cropService.addFertilizerToCrop(cropId, fertilizerId);
      return new ResponseEntity<>(
          "Fertilizante e plantação associados com sucesso!", HttpStatus.CREATED);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Gets fertilizers by crop.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop
   */
  @GetMapping("/crops/{cropId}/fertilizers")
  public ResponseEntity<Object> getFertilizersByCrop(
      @PathVariable Long cropId) {
    try {
      List<Fertilizer> fertilizers = cropService.getFertilizersByCrop(cropId);
      return new ResponseEntity<>(fertilizers, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Search crops response entity.
   *
   * @param start the start
   * @param end   the end
   * @return the response entity
   */
  @GetMapping("/crops/search")
  public ResponseEntity<Object> searchCrops(
      @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
    try {
      if (start.isAfter(end)) {
        return new ResponseEntity<>(
            "Data de início não pode ser após a data de término", HttpStatus.BAD_REQUEST);
      }
      List<CropDto> crops = cropService.searchCropsByHarvestDate(start, end);
      return new ResponseEntity<>(crops, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }
}
