package net.kang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Integer>{

}
