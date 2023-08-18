package com.tracymedcalf.warehouseapp;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Sku {

	private @Id @GeneratedValue Long id; 
	private String name;
	private String putawayType;
	private String description;

	private Sku() {}

	public Sku(String name, String putawayType, String description) {
		this.name = name;
		this.putawayType = putawayType;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sku employee = (Sku) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(name, employee.name) &&
			Objects.equals(putawayType, employee.putawayType) &&
			Objects.equals(description, employee.description);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, putawayType, description);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPutawayType() {
		return putawayType;
	}

	public void setPutawayType(String putawayType) {
		this.putawayType = putawayType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Sku{" +
			"id=" + id +
			", name='" + name + '\'' +
			", putawayType='" + putawayType + '\'' +
			", description='" + description + '\'' +
			'}';
	}
}