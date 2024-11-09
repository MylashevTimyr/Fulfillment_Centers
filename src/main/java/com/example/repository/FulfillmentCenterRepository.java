package com.example.repository;

import com.example.model.FulfillmentCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FulfillmentCenterRepository extends JpaRepository<FulfillmentCenter, Long> {
}