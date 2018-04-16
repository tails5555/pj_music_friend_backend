package net.kang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Integer>{
	Optional<Link> findByCreateUserIdAndMainMusicIdAndSubMusicId(int createUserId, int mainMusicId, int subMusicId);
	List<Link> findByCreateUserIdAndMainMusicId(int createUserId, int mainMusicId);
	List<Link> findByCreateUserIdAndSubMusicId(int createUserId, int subMusicId);
	void deleteByCreateUserIdAndMainMusicId(int createUserId, int mainMusicId);
	void deleteByCreateUserIdAndSubMusicId(int createUserId, int subMusicId);
}
