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
		List<User> anotherUserList = userRepository.findAll();
		anotherUserList.remove(user); // 그래프는 사용자를 시점을 통해 그려지기 때문에 사용자를 일단 빼도록 한다.
		List<GraphEdge> graphEdgeList = new ArrayList<GraphEdge>(); // 그래프 간선 저장
		List<Music> myPlayList = user.getPlayList(); // 본인의 음악 목록 설정
		if(anotherUserList.size()>1) { // 관계는 1명 이상이 있을 때 형성이 된다.
			// 1단계. 본인과 타인들을 통한 연결을 매칭시킨다.
			for(User au : anotherUserList) {
				List<Music> auPlayList = au.getPlayList(); // 타인의 음악을 가져온다.
				if(auPlayList.size()>0) { // 타인의 음악이 0보다 클 때 실행
					double jaccard = JaccardSimilarity.jaccardCalcular(myPlayList, auPlayList);
					auPlayList.removeAll(myPlayList); // 타인의 음악에서 본인의 음악을 배제한다.
					if(jaccard > 0.0) {
						if(auPlayList.size()>0) // 타인의 음악이 본인의 음악들 중에서 포함이 안 되어 있다면...
							graphEdgeList.add(new GraphEdge(user.getId(), au.getId(), Integer.toString((int)(100 * jaccard)), (int)(10 * jaccard), MakeLabel.makeLabel(auPlayList)));
						else // 타인의 음악이 본인의 음악을 모두 포함한다면
							graphEdgeList.add(new GraphEdge(user.getId(), au.getId(), Integer.toString((int)(100 * jaccard)), (int)(10 * jaccard), "본인이 포함되거나 같은 음악을 듣습니다."));
					}
				}
			}

			// 2단계. 타인과 타인들의 연결을 매칭시킨다.
			for(User au1 : anotherUserList) {
				for(User au2 : anotherUserList) {
					if(!au1.equals(au2) && (au1.getId() < au2.getId())) { // 타인1과 타인2이 같지 않은 경우에만 실행을 한다. 그리고 상관 관계는 ID의 오름차순만 봐주도록 한다.
						List<Music> au1PlayList = au1.getPlayList();
						List<Music> au2PlayList = au2.getPlayList(); // 각 타인1과 타인2의 음악을 가져온다.
						double jaccard = JaccardSimilarity.jaccardCalcular(au1PlayList, au2PlayList);
						au2PlayList.removeAll(myPlayList); // 우선은 타인2에서 접속 사용자의 음악을 배제한다.
						au2PlayList.removeAll(au1PlayList); // 그 다음 타인1의 음악을 배제한다.
						if(jaccard > 0.0) {
							if(au2PlayList.size()>0) // 타인2의 음악이 타인1의 음악들 중에서 포함이 안 되어 있다면...
								graphEdgeList.add(new GraphEdge(au1.getId(), au2.getId(), Integer.toString((int)(100 * jaccard)), (int)(10 * jaccard), MakeLabel.makeLabel(au2PlayList)));
							else // 타인2의 음악이 타인1의 음악을 모두 포함한다면
								graphEdgeList.add(new GraphEdge(au1.getId(), au2.getId(), Integer.toString((int)(100 * jaccard)), (int)(10 * jaccard), "타인 끼리 포함되거나 같은 음악을 듣습니다."));
						}
					}
				}
			}
		}
		return graphEdgeList;
	}
}
