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

function Organization(organizationName, banner) {
	this.organizationName = organizationName;
	this.banner = banner;
}

function Tournament(name,date,description,rules,schedule,gameName,sponsor,prizes,logo,numOfAttendees){
	this.name = name;
	this.date = date;
	this.description = description;
	this.rules = rules;
	this.schedule = schedule;
	this.gameName = gameName;
	this.sponsor = sponsor;
	this.prized = prizes;
	this.logo = logo;
	this.numOfAttendees = numOfAttendees;
}