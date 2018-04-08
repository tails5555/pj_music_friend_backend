package net.kang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.Follower;

public interface FollowerRepository extends JpaRepository<Follower, Integer>{

}
