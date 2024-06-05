package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.ErrorResponse;
import com.betrybe.agrix.model.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import java.util.Optional;
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
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  @Autowired
  private FertilizerService fertilizerService;

  /**
   * Create fertilizer response entity.
   *
   * @param fertilizer the fertilizer
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer savedFertilizer = fertilizerService.createFertilizer(fertilizer);
    return new ResponseEntity<>(savedFertilizer, HttpStatus.CREATED);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping
  public ResponseEntity<List<Fertilizer>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();
    return new ResponseEntity<>(fertilizers, HttpStatus.OK);
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> getFertilizerById(@PathVariable Long id) {
    Optional<Fertilizer> fertilizer = fertilizerService.getFertilizerById(id);
    if (fertilizer.isPresent()) {
      return new ResponseEntity<>(fertilizer.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(new ErrorResponse(
          "Fertilizante não encontrado!"), HttpStatus.NOT_FOUND);
    }
  }
}
