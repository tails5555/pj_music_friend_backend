package net.kang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.Follower;
import net.kang.domain.User;

public interface FollowerRepository extends JpaRepository<Follower, Integer>{
	List<Follower> findByMainUser(User user);
	List<Follower> findBySubUser(User user);
	void deleteByMainUserAndSubUser(User mainUser, User subUser);
}
