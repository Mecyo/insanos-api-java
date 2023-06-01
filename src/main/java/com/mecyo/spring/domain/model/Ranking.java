package com.mecyo.spring.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.mecyo.spring.domain.validation.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class Ranking {

	@Id
	@NotNull(groups = ValidationGroups.RankingId.class)
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nickname;
	
	@NotNull
	private String admin;
	
    private Integer firstWeek = 0;
    private Integer secondWeek = 0;
    private Integer thirdWeek = 0;
    private Integer fourthWeek = 0;
    private Integer decksUseds = 0;
    @Transient
    private Integer totalPoints = 0;
	
	public Integer getTotalPoints() {
		return this.firstWeek + this.secondWeek + this.thirdWeek + this.fourthWeek;
	}
	
	public Ranking(String name) {
		this.nickname = name;
	}

	public void setWeekPoints(Integer week, Integer points) {
		switch (week) {
		case 1:
			this.firstWeek = points;
			break;
		case 2:
			this.secondWeek = points;
			break;
		case 3:
			this.thirdWeek = points;
			break;
		case 4:
			this.fourthWeek = points;
			break;
		default:
			break;
		}
	}
}
