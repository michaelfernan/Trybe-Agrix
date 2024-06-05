package com.betrybe.agrix.repository;

import com.betrybe.agrix.model.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  /**
   * Find by farm id list.
   *
   * @param farmId the farm id
   * @return the list
   */
  List<Crop> findByFarmId(Long farmId);

  /**
   * Find by harvest date between.
   *
   * @param start the start date
   * @param end   the end date
   * @return the list of crops
   */
  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
