package net.kang.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kang.domain.User;
import net.kang.repository.UserRepository;
import net.kang.utils.Encryption;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public User login(String loginId, String password) {
        User user = userRepository.findOneByLoginId(loginId);
        if (user == null) return null;
        String pw = Encryption.encrypt(password, Encryption.MD5);
        if (user.getPassword().equals(pw) == false) return null;
        return user;
    }
}

