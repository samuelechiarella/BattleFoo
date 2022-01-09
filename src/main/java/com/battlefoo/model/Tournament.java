package com.battlefoo.model;

import java.util.Objects;

public class Tournament {
	
	private String tournament_id;
	private String name;
	private Date date;
	private String description;
	private String rules;
	private String schedule;
	private String game_name;
	private String sponsor;
	private String prizes;
	private String logo;
	private long manager_id;
	
	public Tournament(String tournament_id, String name, Date date, String game_name, String logo, long manager_id) {
		super();
		this.tournament_id = tournament_id;
		this.name = name;
		this.date = date;
		this.game_name = game_name;
		this.logo = logo;
		this.manager_id = manager_id;
	}

	public String getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(String tournament_id) {
		this.tournament_id = tournament_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getPrizes() {
		return prizes;
	}

	public void setPrizes(String prizes) {
		this.prizes = prizes;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public long getManager_id() {
		return manager_id;
	}

	public void setManager_id(long manager_id) {
		this.manager_id = manager_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tournament_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournament other = (Tournament) obj;
		return Objects.equals(tournament_id, other.tournament_id);
	}
}
