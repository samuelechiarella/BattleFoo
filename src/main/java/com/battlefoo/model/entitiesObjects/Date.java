package com.battlefoo.model.entitiesObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Date {
	
	@NonNull
	private Integer month;
	@NonNull
	private Integer day;
	@NonNull
	private Integer year;
	
	private String dateString;
	
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
		this.dateString = this.getDate();
	}
	
	public String getDate() {
		String m = "", d = "";
		
		if(month < 10)	
			m = "0" + String.valueOf(month);
		else
			m = String.valueOf(month);
		
		if(day < 10)	
			d = "0" + day;
		else
			d = String.valueOf(day);
		
		return m + "/" + d + "/" + year;
	}
}
