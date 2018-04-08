package net.kang.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Music {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String title;
	String singer;
	String album;

	@Column(name="releaseDate", nullable = false)
	LocalDateTime release; // 최근 JPA에서는 DATETIME의 인식에 대해서 바뀌었음을 반영하여 Java 8에서 제공하는 이를 쓰는 방법을 채택하였음.

	@JsonIgnore
	@ManyToMany
	@JoinTable(name="userandmusic",
			   joinColumns=@JoinColumn(name="musicId"),
			   inverseJoinColumns=@JoinColumn(name="userId"),
			   uniqueConstraints={
					   @UniqueConstraint(columnNames = {"userId", "musicId"})
			   })
	List<User> favorites;

	@OneToOne(mappedBy="music", fetch=FetchType.LAZY)
	Populate populate;

	@JsonIgnore
	@OneToMany(mappedBy="mainMusic", fetch=FetchType.LAZY)
	List<Link> links;
}
