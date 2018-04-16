package net.kang.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import net.kang.domain.User;
public interface UserRepository extends JpaRepository<User, Integer>{
	User findOneByUserId(String userId);

}
