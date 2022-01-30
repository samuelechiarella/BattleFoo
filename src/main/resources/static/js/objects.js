function Team(teamName, logo){
	this.teamName = teamName;
	this.logo = logo;
}

function User(username, firstName, lastName, email, password){
	this.username = username;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
}

function UserLogIn(username, password){
	this.username = username;
	this.password = password;
}

function Organization(organizationId, organizationName, description, creatorId) {
	this.organizationId = organizationId;
	this.organizationName = organizationName;
	this.description = description;
	this.creatorId = creatorId;
}

function Tournament(name,date,description,rules,schedule,gameName,sponsor,prizes,logo){
	this.name = name;
	this.date = date;
	this.description = description;
	this.rules = rules;
	this.schedule = schedule;
	this.gameName = gameName;
	this.sponsor = sponsor;
	this.prized = prizes;
	this.logo = logo;
}