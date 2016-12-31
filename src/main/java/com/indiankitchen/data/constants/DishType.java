package com.indiankitchen.data.constants;

import java.util.HashMap;
import java.util.Map;

public enum DishType {
	
	CURRY ("Curry"),
	DRY_GRAVY("Dry Gravy"),
	RICE_BASED("Rice Based"),
	BREAKFAST("Breakfast"),
	KP_SPECIAL("KP Special");
	
	private final String name;
	
	DishType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static DishType getByName(String name) {
		return lookup.get(name);
	}

	
	// Reverse-lookup map 
    private static final Map<String, DishType> lookup = new HashMap<String, DishType>();

    static {
        for (DishType d : DishType.values()) {
            lookup.put(d.getName(), d);
        }
    }
    
    @Override
    public String toString() {
    	return getName();
    }

}
