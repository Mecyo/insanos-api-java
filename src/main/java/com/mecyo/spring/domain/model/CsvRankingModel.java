package com.mecyo.spring.domain.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvRankingModel {
	@CsvBindByName
	private Integer season_id;
	@CsvBindByName
	private String season_key;
	@CsvBindByName
	private String player_name;
	@CsvBindByName
	private Integer player_contribution;
	@CsvBindByName
	private Integer player_decks_used;
	
	
	public Integer getWeek() {
		if(this.season_key.contains("-")) {
			return Integer.parseInt(this.season_key.split("-")[1]);
		}
		
		return null;
	}
}
