package net.kang.domain;

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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@Column(unique=true)
	String userId;

	@JsonIgnore
	String password;

	String name;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name="userandmusic",
	   joinColumns=@JoinColumn(name="userId"),
	   inverseJoinColumns=@JoinColumn(name="musicId"),
	   uniqueConstraints={
			   @UniqueConstraint(columnNames = {"userId", "musicId"})
	   })
	List<Music> playList;

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY)
	List<Follower> followers;
}
