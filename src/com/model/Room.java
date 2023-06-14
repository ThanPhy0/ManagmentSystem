package com.model;

import java.time.LocalDate;

public class Room {
	private int room;
	private int personCount;
	private int section;
	private int activeStatus;
	private LocalDate date;
	private String endSection;

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getEndSection() {
		return endSection;
	}

	public void setEndSection(String endSection) {
		this.endSection = endSection;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return personCount + " - " + section + " - " + activeStatus + " - " + endSection;
	}

}
