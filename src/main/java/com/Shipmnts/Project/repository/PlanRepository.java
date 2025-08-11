package com.Shipmnts.Project.repository;

import com.Shipmnts.Project.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
    public Plan findByStoreLocationAndValidFromLessThanEqualAndValidToGreaterThanEqual(String storeLocation,
                                                                                    LocalDate validFrom,
                                                                                    LocalDate validTo);
}