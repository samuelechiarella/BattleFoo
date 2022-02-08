package com.battlefoo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Response {
	
	public static final int failure = 500;
	public static final Integer success = 200;
	@NonNull
	private Integer responseCode;
	@NonNull
	private String responseMessage;
	private Object data;
}