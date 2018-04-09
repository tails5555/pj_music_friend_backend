package net.kang.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.Music;
public interface MusicRepository extends JpaRepository<Music, Integer>{
	List<Music> findByOrderByPopulateScoreDesc();
}
