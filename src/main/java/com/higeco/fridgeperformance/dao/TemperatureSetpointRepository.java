package com.higeco.fridgeperformance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemperatureSetpointRepository extends JpaRepository<TemperatureSetpoint, Long> {

    Optional<TemperatureSetpoint> findByFridgeId(String fridgeId);
}
