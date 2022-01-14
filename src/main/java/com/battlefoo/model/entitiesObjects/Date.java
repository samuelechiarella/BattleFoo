package com.battlefoo.model.entitiesObjects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Date {
	
	private int month;
	private int day;
	private int year;
	
	public String getDate() {
		String m = "", d = "";
		if(month < 10)	
			m = "0" + month;
		if(day < 10)	
			d = "0" + day;
		return m + "/" + d + "/" + year;
	}
}
