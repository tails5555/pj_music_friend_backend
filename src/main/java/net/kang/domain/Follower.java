package net.kang.domain;

import java.time.LocalDateTime;

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
		@UniqueConstraint(columnNames = {"mainUserId", "subUserId"})
})
public class Follower {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mainUserId")
	User mainUser;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="subUserId")
	User subUser;

	@Column(name="followerDate", nullable = false)
	LocalDateTime folDate;
}
