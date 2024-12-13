package com.ochobits.optica.historiaClinica.repository;

import com.ochobits.optica.historiaClinica.entities.HistoriaclinicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaHistoriaclinicaRepository extends JpaRepository<HistoriaclinicaEntity, Void>, JpaSpecificationExecutor<HistoriaclinicaEntity> {

}