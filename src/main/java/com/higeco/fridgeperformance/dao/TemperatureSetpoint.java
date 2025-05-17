package com.higeco.fridgeperformance.dao;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "temperature_setpoint")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemperatureSetpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fridge_id", nullable = false)
    private String fridgeId;

    @Column(name = "temperature", nullable = false)
    private Integer temperature;

}