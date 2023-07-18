package com.brzas.project.alternative.repository;

import com.brzas.project.alternative.models.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, Long> {
}
