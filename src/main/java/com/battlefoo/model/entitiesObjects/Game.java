package com.battlefoo.model.entitiesObjects;

import java.util.Objects;

public class Game {
	
	private String name;
	private String genre;
	
	public Game(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(genre, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		return Objects.equals(genre, other.genre) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Game [name=" + name + ", genre=" + genre + "]";
	}
}
