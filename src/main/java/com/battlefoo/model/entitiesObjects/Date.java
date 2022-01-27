package com.battlefoo.model.entitiesObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private Integer month;
	private Integer day;
	private Integer year;
	
	public Date(String date) {
		Pattern p = Pattern.compile("((?:\\d){2})\\/((?:\\d){2})\\/((?:\\d){4})");
		Matcher m = p.matcher(date);
		if(m.matches()) {
			this.month =  Integer.valueOf(m.group(1));
			this.day =  Integer.valueOf(m.group(2));
			this.year =  Integer.valueOf(m.group(3));
		}
		else {
			this.month = -1;
			this.day = -1;
			this.year = -1;
		}
	}
	
	public String getDate() {
		String m = "", d = "";
		if(month < 10)	
			m = "0" + month;
		if(day < 10)	
			d = "0" + day;
		return m + "/" + d + "/" + year;
	}
}
