package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.model.Crop;
import com.betrybe.agrix.model.Farm;
import com.betrybe.agrix.model.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * The type Crop service.
 */
@Service
public class CropService {

  @Autowired
  private CropRepository cropRepository;

  @Autowired
  private FarmRepository farmRepository;

  @Autowired
  private FertilizerRepository fertilizerRepository;

  /**
   * Create crop crop dto.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop dto
   */
  public CropDto createCrop(Long farmId, Crop crop) {
    Farm farm = farmRepository.findById(farmId).orElseThrow(
        () -> new RuntimeException("Fazenda não encontrada!"));
    crop.setFarm(farm);
    Crop savedCrop = cropRepository.save(crop);
    return convertToDto(savedCrop);
  }

  /**
   * Gets crops by farm.
   *
   * @param farmId the farm id
   * @return the crops by farm
   */
  public List<CropDto> getCropsByFarm(Long farmId) {
    if (!farmRepository.existsById(farmId)) {
      throw new RuntimeException("Fazenda não encontrada!");
    }
    List<Crop> crops = cropRepository.findByFarmId(farmId);
    return crops.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  public CropDto getCropById(Long id) {
    Crop crop = cropRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Plantação não encontrada!"));
    return convertToDto(crop);
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<CropDto> getAllCrops() {
    List<Crop> crops = cropRepository.findAll();
    return crops.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  /**
   * Search crops by harvest date list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  public List<CropDto> searchCropsByHarvestDate(LocalDate start, LocalDate end) {
    List<Crop> crops = cropRepository.findByHarvestDateBetween(start, end);
    return crops.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  /**
   * Add fertilizer to crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  public void addFertilizerToCrop(Long cropId, Long fertilizerId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(
        () -> new RuntimeException("Plantação não encontrada!"));
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId).orElseThrow(
        () -> new RuntimeException("Fertilizante não encontrado!"));

    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  /**
   * Gets fertilizers by crop.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop
   */
  public List<Fertilizer> getFertilizersByCrop(Long cropId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(
        () -> new RuntimeException("Plantação não encontrada!"));
    return List.copyOf(crop.getFertilizers());
  }

  private CropDto convertToDto(Crop crop) {
    CropDto cropDto = new CropDto();
    cropDto.setId(crop.getId());
    cropDto.setName(crop.getName());
    cropDto.setPlantedArea(crop.getPlantedArea());
    cropDto.setFarmId(crop.getFarm().getId());
    cropDto.setPlantedDate(crop.getPlantedDate());
    cropDto.setHarvestDate(crop.getHarvestDate());
    return cropDto;
  }
}
