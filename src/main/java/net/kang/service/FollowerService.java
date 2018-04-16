package net.kang.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kang.domain.Follower;
import net.kang.domain.User;
import net.kang.getModel.FollowerComponent;
import net.kang.getModel.FollowingComponent;
import net.kang.getModel.RecommendComponent;
import net.kang.repository.FollowerRepository;
import net.kang.repository.UserRepository;
import net.kang.utils.JaccardSimilarity;

@Service
public class FollowerService {
	@Autowired UserRepository userRepository;
	@Autowired FollowerRepository followerRepository;
	public List<FollowerComponent> getFollowerComponent(String userId){
		List<FollowerComponent> followerList=new ArrayList<FollowerComponent>();
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return null;
        List<Follower> followers = followerRepository.findBySubUser(user);
        for(Follower f : followers) {
        	User mainUser = f.getMainUser();
        	FollowerComponent fc=new FollowerComponent();
        	fc.setId(mainUser.getId());
        	fc.setName(mainUser.getName());
        	fc.setPlayList(mainUser.getPlayList());
        	double jaccard = JaccardSimilarity.jaccardCalcular(user.getPlayList(), mainUser.getPlayList());
        	fc.setScore(((int)(100 * jaccard)));
        	fc.setFollowerDate(f.getFolDate());
        	followerList.add(fc);
        }
        Collections.sort(followerList);
        return followerList;
	}

	public List<FollowingComponent> getFollowingComponent(String userId){
		List<FollowingComponent> followingList=new ArrayList<FollowingComponent>();
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return null;
        List<Follower> followings = followerRepository.findByMainUser(user);
        for(Follower fing : followings) {
        	User subUser = fing.getSubUser();
        	FollowingComponent fc=new FollowingComponent();
        	fc.setId(subUser.getId());
        	fc.setName(subUser.getName());
        	fc.setPlayList(subUser.getPlayList());
        	double jaccard = JaccardSimilarity.jaccardCalcular(user.getPlayList(), subUser.getPlayList());
        	fc.setScore(((int)(100 * jaccard)));
        	fc.setFollowingDate(fing.getFolDate());
        	followingList.add(fc);
        }
        Collections.sort(followingList);
        return followingList;
	}

	public List<RecommendComponent> getRecommendComponent(String userId){
		List<RecommendComponent> recommendList = new ArrayList<RecommendComponent>();
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return null;

        List<User> users = userRepository.findAll();
        users.remove(user);

        List<Follower> followings = followerRepository.findByMainUser(user);
        for(Follower fing : followings) {
        	users.remove(fing.getSubUser());
        }

        List<Follower> followers = followerRepository.findBySubUser(user);
        List<User> followerUsers = new ArrayList<User>();
        for(Follower f : followers) {
        	followerUsers.add(f.getMainUser());
        }

        for(User u : users) {
        	RecommendComponent rc = new RecommendComponent();
        	rc.setId(u.getId());
        	rc.setName(u.getName());
        	double jaccard = JaccardSimilarity.jaccardCalcular(user.getPlayList(), u.getPlayList());
        	rc.setScore(((int)(100 * jaccard)));
        	if(followerUsers.contains(u)) {
        		rc.setFollowed(true);
        	}
        	recommendList.add(rc);
        }

        Collections.sort(recommendList);
        return recommendList;
	}

	@Transactional
	public void following(String userId, int subUserId) {
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return;
        Optional<User> tmSubUser = userRepository.findById(subUserId);
        Follower follower = new Follower();
        follower.setMainUser(user);
        follower.setSubUser(tmSubUser.get());
        follower.setFolDate(LocalDateTime.now());
        followerRepository.save(follower);
	}

	@Transactional
	public void unfollowing(String userId, int subUserId) {
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return;
        Optional<User> tmSubUser = userRepository.findById(subUserId);
        followerRepository.deleteByMainUserAndSubUser(user, tmSubUser.get());
	}
}
