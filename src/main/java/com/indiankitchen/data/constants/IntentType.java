package com.indiankitchen.data.constants;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IntentType {

	SUGGEST_A_MEAL("Recommend Meal"),
	SUGGEST_ANOTHER_MEAL("Recommend other Meal"),
	SURPRISE_ME("Surprise Me"),
	SURPRISE_ME_AGAIN("Surprise Me Again"),
	ADD_A_MEAL("Add Meal");

	private final String name;

	IntentType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static IntentType getByName(String name) {
		return lookup.get(name);
	}


	// Reverse-lookup map for getting a day from an abbreviation
	private static final Map<String, IntentType> lookup = new HashMap<String, IntentType>();

	static {
		for (IntentType d : IntentType.values()) {
			lookup.put(d.getName(), d);
		}
	}

}
