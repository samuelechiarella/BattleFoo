package com.battlefoo.model.entitiesObjects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class Organization {
	
	@NonNull
	private Long organizationId;
	
	@NonNull
	private String organizationName;
	
	private String description;
	
	@NonNull
	private Long creatorId;
}
