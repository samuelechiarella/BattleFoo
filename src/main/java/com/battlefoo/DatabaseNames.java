package com.battlefoo;

public class DatabaseNames {
	public static final String URL_POSTGRES = "jdbc:postgresql://postgres:5432/postgres";
	public class Tables {
		public class Games {
			public static final String TABLE_NAME = "games";
			public static final String COLUMN_NAME = "name";
			public static final String COLUMN_GENRE = "genre";
		}
		
		public class Managers {
			public static final String TABLE_NAME = "managers";
			public static final String COLUMN_MANAGER_ID = "manager_id";
			public static final String COLUMN_NICKNAME = "nickname";
		}
		
		public class Players {
			public static final String TABLE_NAME =  "players";
			public static final String COLUMN_PLAYER_ID = "player_id";
			public static final String COLUMN_NICKNAME = "nickname";
		}
		
		public class Teams {
			public static final String TABLE_NAME = "teams";
			public static final String COLUMN_TEAM_NAME = "team_name";
			public static final String COLUMN_LOGO = "logo";
			public static final String COLUMN_DESCRIPTION = "description";
			public static final String COLUMN_LEADER_ID = "leader_id";
			public static final String COLUMN_TOURNAMENT_ID = "tournament_id";
		}
		
		public class TeamsMembers {
			public static final String TABLE_NAME = "teams_members";
			public static final String COLUMN_PLAYER_ID = "player_id";
			public static final String COLUMN_TEAM_NAME = "team_name";
		}
		
		public class Tournaments {
			public static final String TABLE_NAME = "tournaments";
			public static final String COLUMN_TOURNAMENT_ID = "tournament_id";
			public static final String COLUMN_NAME = "name";
			public static final String COLUMN_DATE = "date";
			public static final String COLUMN_DESCRIPTION = "description";
			public static final String COLUMN_RULES = "rules";
			public static final String COLUMN_SCHEDULE = "schedule";
			public static final String COLUMN_TEAM_NAME = "team_name";
			public static final String COLUMN_GAME_NAME = "game_name";
			public static final String COLUMN_SPONSOR = "sponsor";
			public static final String COLUMN_PRIZES = "prizes";
			public static final String COLUMN_LOGO = "logo";
			public static final String COLUMN_MANAGER_ID = "manager_id";
		}
		
		public class Users {
			public static final String TABLE_NAME = "users";
			public static final String COLUMN_NICKNAME = "nickname";
			public static final String COLUMN_FIRST_NAME = "firstname";
			public static final String COLUMN_LAST_NAME = "lastname";
			public static final String COLUMN_EMAIL = "email";
			public static final String COLUMN_PASSWORD = "password";
		}
	}
}
