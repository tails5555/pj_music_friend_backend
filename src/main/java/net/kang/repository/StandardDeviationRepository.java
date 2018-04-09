package net.kang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.StandardDeviation;

public interface StandardDeviationRepository extends JpaRepository<StandardDeviation, Integer>{
	StandardDeviation findTopByOrderByLatestDateDesc();
}
