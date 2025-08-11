package com.Shipmnts.Project.repository;

import com.Shipmnts.Project.model.Item;
import com.Shipmnts.Project.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByNameAndPlan(String name, Plan plan);

    @Query("Select AVG(i.halfPrice) from Item i where category=:category AND plan=:plan")
    Long getAverageByCategoryInHalfLength(@Param("category") String category,@Param("plan") Plan plan);

    @Query("Select AVG(i.fullPrice) from Item i where category=:category AND plan=:plan")
    Long getAverageByCategoryInFullLength(@Param("category") String category,@Param("plan") Plan plan);

}
