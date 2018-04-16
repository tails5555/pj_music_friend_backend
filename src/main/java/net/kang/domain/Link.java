package net.kang.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames = {"userId", "mainMusicId", "subMusicId"})
}) // 각 사용자 별로 Link를 형성해야 효율적으로 작동할 수 있기 때문에 Link 컬럼에 User를 새로 추가하였음.
public class Link {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	User createUser;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mainMusicId")
	Music mainMusic;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="subMusicId")
	Music subMusic;
}
