package net.kang.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kang.domain.User;
import net.kang.postModel.UserForm;
import net.kang.repository.UserRepository;
import net.kang.utils.Encryption;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public User login(String userId, String password) {

        User user = userRepository.findOneByUserId(userId);
        System.out.println("UserService "+user.getUserId());

        if (user == null) return null;
        String pw = Encryption.encrypt(password, Encryption.MD5);
        if (user.getPassword().equals(pw) == false) return null;

        return user;
    }


    public void save(UserForm userForm) {
    	User newUser=new User();
    	newUser.setName(userForm.getName());
    	newUser.setUserId(userForm.getUserId());
    	newUser.setPassword(Encryption.encrypt(userForm.getPassword1(), Encryption.MD5));
    	userRepository.save(newUser);
    }
}

