package com.indiankitchen.data;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="INDIANKITCHEN.MEAL")
public class MealEntity extends BaseEntity{

	@Id
	private String name;
	private String dishType;
	private String cuisine;
	private String[] vegetables;
	private String[] proteins;

	public MealEntity() {
		
	}
	
	public MealEntity(MealDTO mealDTO) {
		this.name = mealDTO.getName();
		this.dishType = mealDTO.getDishType().toString();
		this.cuisine = mealDTO.getCuisine().toString();
		this.vegetables = Arrays.copyOf(mealDTO.getVegetables(), mealDTO.getVegetables().length);
		this.proteins = Arrays.copyOf(mealDTO.getProteins(), mealDTO.getProteins().length);
	}

	public String getName() {
		return name;
	}
	public String getDishType() {
		return dishType;
	}
	public String[] getVegetables() {
		return vegetables;
	}
	public String[] getProteins() {
		return proteins;
	}
	public String getCuisine() {
		return cuisine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuisine == null) ? 0 : cuisine.hashCode());
		result = prime * result
				+ ((dishType == null) ? 0 : dishType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(proteins);
		result = prime * result + Arrays.hashCode(vegetables);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MealEntity other = (MealEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Meal [name=").append(name).append(", dishType=")
		.append(dishType).append(", cuisine=").append(cuisine)
		.append(", vegetables=").append(Arrays.toString(vegetables))
		.append(", proteins=").append(Arrays.toString(proteins))
		.append("]");
		return builder.toString();
	}



}
