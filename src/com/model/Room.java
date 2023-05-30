package com.model;

public class Room {
	private int room;
	private int personCount;
	private int section;

	public Room(int room, int personCount, int section) {
		super();
		this.room = room;
		this.personCount = personCount;
		this.section = section;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

}
