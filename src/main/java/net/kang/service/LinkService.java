package net.kang.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kang.domain.Link;
import net.kang.domain.Music;
import net.kang.domain.Populate;
import net.kang.domain.User;
import net.kang.getModel.GraphEdge;
import net.kang.repository.LinkRepository;
import net.kang.repository.PopulateRepository;
import net.kang.repository.UserRepository;
import net.kang.utils.JaccardSimilarity;
import net.kang.utils.MakeLabel;

// 참고 사항 : Populate의 변수를 double로 하면 계산 이상이 나서 INT로 바뀌었음. 그리고 Link에서 User와 MainMusic, SubMusic을 저장하도록 수정하였습니다. 추후 보고하겠습니다.
@Service
public class LinkService {
	@Autowired UserRepository userRepository;
	@Autowired LinkRepository linkRepository;
	@Autowired PopulateRepository populateRepository;

	@Transactional
	public void insertLink(String userId, int musicId) {
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return;

        Optional<Populate> populate = populateRepository.findByMusicId(musicId);
		Populate tmpPopulate = populate.orElse(new Populate());
		if(!tmpPopulate.equals(new Populate())) {
			int tmpScore = tmpPopulate.getScore();
			tmpPopulate.setScore(tmpScore+10); // 처음으로 추가한 음악에 대해서는 10점을 올려서 추가를 한다.
			populateRepository.save(tmpPopulate);
		}

        List<Music> musicList = user.getPlayList();
        if(musicList.size()<=1) return; // 음악 하나에 대해서는 관계를 형성할 수 없어서 리턴을 한다.

        // 음악 2개 이상부터 각 음악을 순회하면서 링킹 작업을 한다.
        for(int k=0;k<musicList.size();k++) {
        	for(int l=0;l<musicList.size();l++) {
        		if(k!=l) {
	        		Optional<Link> link = linkRepository.findByCreateUserIdAndMainMusicIdAndSubMusicId(user.getId(), musicList.get(k).getId(), musicList.get(l).getId());
	        		Link tmpLink=link.orElse(new Link());
	        		if(tmpLink.equals(new Link())) {
	        			tmpLink.setCreateUser(user);
	        			tmpLink.setMainMusic(musicList.get(k));
	        			tmpLink.setSubMusic(musicList.get(l));
	        			linkRepository.save(tmpLink);
	        		}
        		}
        	}
        }

        // 링킹 작업에 대해 완료를 한다면 각 음악에 대한 관계 점수 1점씩 부가를 한다.
        List<Link> mainLink = linkRepository.findByCreateUserIdAndMainMusicId(user.getId(), musicId); // 메인 링크를 가져와서 서브 음악들에 대한 인기도를 1점 내린다.
        for(Link link : mainLink) {
        	Optional<Populate> subPopulate = populateRepository.findByMusicId(link.getSubMusic().getId());
    		Populate tmpSubPopulate = subPopulate.orElse(new Populate());
    		if(!tmpSubPopulate.equals(new Populate())) {
    			int tmpSubScore = tmpSubPopulate.getScore();
    			tmpSubPopulate.setScore(tmpSubScore+1); // 추가 하는 음악과 관계가 되는 음악에 대해 1점 감점한다.
    			populateRepository.save(tmpSubPopulate);
    		}
        }

        List<Link> subLink = linkRepository.findByCreateUserIdAndSubMusicId(user.getId(), musicId); // 서브 링크를 가져와서 메인 음악들에 대한 인기도를 1점 내린다.
        for(Link link : subLink) {
        	Optional<Populate> mainPopulate = populateRepository.findByMusicId(link.getSubMusic().getId());
        	Populate tmpMainPopulate = mainPopulate.orElse(new Populate());
    		if(!tmpMainPopulate.equals(new Populate())) {
    			int tmpMainScore = tmpMainPopulate.getScore();
    			tmpMainPopulate.setScore(tmpMainScore+1); // 추가 하는 음악과 관계를 형성한 음악에 대해 1점 감점한다.
    			populateRepository.save(tmpMainPopulate);
    		}
        }
	}

	@Transactional
	public void deleteLink(String userId, int musicId) {
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return;

        Optional<Populate> populate = populateRepository.findByMusicId(musicId);
		Populate tmpPopulate = populate.orElse(new Populate());
		if(!tmpPopulate.equals(new Populate())) {
			int tmpScore = tmpPopulate.getScore();
			tmpPopulate.setScore(tmpScore-10); // 초기에는 삭제하는 음악에 대해서 10점 감점한다.
			populateRepository.save(tmpPopulate);
		}

		if(user.getPlayList().size()==0) return; // 음악을 삭제하고 난 이후에는 관계에 대해서 굳이 볼 필요가 없어서 리턴을 한다.

        List<Link> mainLink = linkRepository.findByCreateUserIdAndMainMusicId(user.getId(), musicId); // 메인 링크를 가져와서 서브 음악들에 대한 인기도를 1점 내린다.
        for(Link link : mainLink) {
        	Optional<Populate> subPopulate = populateRepository.findByMusicId(link.getSubMusic().getId());
    		Populate tmpSubPopulate = subPopulate.orElse(new Populate());
    		if(!tmpSubPopulate.equals(new Populate())) {
    			int tmpSubScore = tmpSubPopulate.getScore();
    			tmpSubPopulate.setScore(tmpSubScore-1); // 삭제 하는 음악과 관계가 되는 음악에 대해 1점 감점한다.
    			populateRepository.save(tmpSubPopulate);
    		}
        }

        List<Link> subLink = linkRepository.findByCreateUserIdAndSubMusicId(user.getId(), musicId); // 서브 링크를 가져와서 메인 음악들에 대한 인기도를 1점 내린다.
        for(Link link : subLink) {
        	Optional<Populate> mainPopulate = populateRepository.findByMusicId(link.getSubMusic().getId());
        	Populate tmpMainPopulate = mainPopulate.orElse(new Populate());
    		if(!tmpMainPopulate.equals(new Populate())) {
    			int tmpMainScore = tmpMainPopulate.getScore();
    			tmpMainPopulate.setScore(tmpMainScore-1); // 삭제 하는 음악과 관계를 형성한 음악에 대해 1점 감점한다.
    			populateRepository.save(tmpMainPopulate);
    		}
        }

        // 최종적으로 링크를 삭제한다.
        linkRepository.deleteByCreateUserIdAndMainMusicId(user.getId(), musicId);
        linkRepository.deleteByCreateUserIdAndSubMusicId(user.getId(), musicId);
	}

	public List<GraphEdge> getGraphEdges(String userId){
		Optional<User> tmUser = userRepository.findByUserId(userId);
        User user=tmUser.orElse(new User());
        if (user.equals(new User())) return new ArrayList<GraphEdge>();
		List<User> userList = userRepository.findAll();
		List<GraphEdge> graphEdgeList = new ArrayList<GraphEdge>();
		List<Music> myMusic = user.getPlayList();
		for(int k=0;k<userList.size();k++) {
			for(int l=0;l<userList.size();l++) { // 단방향으로만 그리면 되니깐 l은 k보다 클 때에 추가만 해 주면 된다.
				if(k!=l) {
					if(userList.get(k).getId() == user.getId() && myMusic.size()>0) { // k가 현재 사용자와 같은 경우에. 그리고 현재 사용자의 음악이 0인 경우에는 그려봤자 무용지물이니 통과.
						List<Music> anoMusic = userList.get(l).getPlayList(); // 나와 타인의 관계에서 음악을 가져옴.
						if(userList.get(l).getId() != user.getId() && anoMusic.size()>0) { // l가 현재 사용자랑 다른 경우에만 적용을 함. 대신 타인도 노래가 있는지 확인 필수.
							double jaccard = JaccardSimilarity.jaccardCalcular(myMusic, anoMusic);
							anoMusic.removeAll(myMusic);
							if(jaccard>0.0) {
								if(anoMusic.size()>0)
									graphEdgeList.add(new GraphEdge(user.getId(), userList.get(l).getId(), (int)(10 * jaccard), MakeLabel.makeLabel(anoMusic)));
								else
									graphEdgeList.add(new GraphEdge(user.getId(), userList.get(l).getId(), (int)(10 * jaccard), "본인이 포함되거나 같은 음악을 듣습니다."));
							}
						}
					}else{ // 타인1과 타인2이 음악을 가지고 있는 경우
						List<Music> ano01Music = userList.get(k).getPlayList();
						if(userList.get(l).getId() != user.getId() && ano01Music.size()>0) { // 타인1이 음악을 가지고 있는 경우에만 선을 형성.
							List<Music> ano02Music = userList.get(l).getPlayList();
							if(ano02Music.size()>0) { // 타인2의 음악의 크기가 0보다 큰 경우에만 해당 시킨다.
								double jaccard = JaccardSimilarity.jaccardCalcular(ano01Music, ano02Music);
								ano01Music.addAll(myMusic);
								ano02Music.removeAll(ano01Music); // 타인이 현재 사용자 이외에 듣는 음악 목록들을 가져오되 내 음악도 비어서 추가를 해도 큰 상관은 없다.
								if(jaccard>0.0) {
									if(ano02Music.size()>0)
										graphEdgeList.add(new GraphEdge(userList.get(k).getId(), userList.get(l).getId(), (int)(10 * jaccard), MakeLabel.makeLabel(ano02Music)));
									else
										graphEdgeList.add(new GraphEdge(userList.get(k).getId(), userList.get(l).getId(), (int)(10 * jaccard), "타인끼리 포함되거나 같은 음악을 듣습니다."));
								}
							}
						}
					}
				}
			}
		}
		return graphEdgeList;
	}
}
