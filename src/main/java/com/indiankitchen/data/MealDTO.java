package com.indiankitchen.data;

import java.util.Arrays;

import com.indiankitchen.data.constants.CuisineType;
import com.indiankitchen.data.constants.DishType;


public class MealDTO {
	
	private String name;
	private DishType dishType;
	private CuisineType cuisine;
	private String[] vegetables;
	private String[] proteins;

	public MealDTO(String name, DishType dishType, CuisineType cuisine, String[] proteins, String[] vegetables) {
		this.name = name;
		this.dishType = dishType;
		this.cuisine = cuisine;
		this.vegetables = Arrays.copyOf(vegetables, vegetables.length);
		this.proteins = Arrays.copyOf(proteins, proteins.length);
	}

	public MealDTO(MealEntity mealEntity) {
		this.name = mealEntity.getName();
		this.dishType = DishType.getByName(mealEntity.getDishType());
		this.cuisine = CuisineType.valueOf(mealEntity.getCuisine());
		this.vegetables = Arrays.copyOf(mealEntity.getVegetables(), mealEntity.getVegetables().length);
		this.proteins = Arrays.copyOf(mealEntity.getProteins(), mealEntity.getProteins().length);
	}

	public String getName() {
		return name;
	}
	public DishType getDishType() {
		return dishType;
	}
	public String[] getVegetables() {
		return vegetables;
	}
	public String[] getProteins() {
		return proteins;
	}
	public CuisineType getCuisine() {
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
		MealDTO other = (MealDTO) obj;
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
