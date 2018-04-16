package net.kang.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kang.domain.User;
import net.kang.getModel.GraphNode;
import net.kang.postModel.UserForm;
import net.kang.repository.UserRepository;
import net.kang.utils.Encryption;
@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public User login(String loginId, String password) {

        Optional<User> tmUser = userRepository.findByUserId(loginId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return null;
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

    public List<GraphNode> getGraphNode(){
    	List<GraphNode> graphNodes=new ArrayList<GraphNode>();
    	List<User> users = userRepository.findAll();
    	for(User u : users) {
    		GraphNode g = new GraphNode(u.getId(), u.getName());
    		graphNodes.add(g);
    	}
    	return graphNodes;
    }
}

