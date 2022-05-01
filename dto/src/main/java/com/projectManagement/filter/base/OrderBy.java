package com.projectManagement.filter.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderBy implements Comparable<OrderBy> {

	private final String property;
	private final int priority;
	private final String direction;

	@JsonCreator
	public OrderBy(@JsonProperty("property") String property, @JsonProperty("priority") int priority, @JsonProperty("direction") String direction) {

		this.property = property;
		this.priority = priority;
		this.direction = direction;
	}

	public String getProperty() {

		return property;
	}

	public int getPriority() {

		return priority;
	}

	public String getDirection() {

		return direction;
	}

	@Override
	public int compareTo(OrderBy o) {

		return Integer.compare(priority, o.priority);
	}
}
