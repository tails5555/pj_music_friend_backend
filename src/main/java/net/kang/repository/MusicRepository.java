package net.kang.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.Music;
public interface MusicRepository extends JpaRepository<Music, Integer>{

}
