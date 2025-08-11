package com.Shipmnts.Project.repository;

import com.Shipmnts.Project.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    Store findByStoreLocation(String name);
}
