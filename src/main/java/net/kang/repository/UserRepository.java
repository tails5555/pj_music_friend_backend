package net.kang.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.User;
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserId(String userId);

	User findOneByLoginId(String loginId);
}
