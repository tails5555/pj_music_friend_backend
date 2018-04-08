package net.kang.domain;

import javax.persistence.Column;
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
		@UniqueConstraint(columnNames = {"mainMusicId", "subMusicId"})
})
public class Link {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mainMusicId")
	Music mainMusic;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="subMusicId")
	Music subMusic;

	@Column(name="linkCount")
	int score;
}
