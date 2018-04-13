package net.kang.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.Populate;

public interface PopulateRepository extends JpaRepository<Populate, Integer>{
	Optional<Populate> findByMusicId(int musicId);
}
