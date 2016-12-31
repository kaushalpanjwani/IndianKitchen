package com.indiankitchen.data.utility;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.indiankitchen.data.MealDTO;
import com.indiankitchen.data.MealEntity;
import com.indiankitchen.data.constants.CuisineType;
import com.indiankitchen.data.constants.DishType;
import com.indiankitchen.data.repositories.MealRepository;

@Component
public class MealService {

	public static ConcurrentHashMap.KeySetView<MealDTO, Boolean> allMealsStartupData = ConcurrentHashMap.newKeySet();

	static{

		allMealsStartupData.add(new MealDTO("Palak Paneer", DishType.CURRY, CuisineType.INDIAN, new String [] {"Cottage Cheese"}, new String [] {"Spinach"}));
		allMealsStartupData.add(new MealDTO("Kadai Paneer", DishType.DRY_GRAVY, CuisineType.INDIAN, new String [] {"Cottage Cheese"}, new String [] {"Capsicum"}));
		allMealsStartupData.add(new MealDTO("Paneer Matar", DishType.CURRY, CuisineType.INDIAN, new String [] {"Cottage Cheese", "Green Peas"}, new String [] {"Tomatoes"}));
		allMealsStartupData.add(new MealDTO("Mushroom Matar", DishType.CURRY, CuisineType.INDIAN, new String [] {"Green Peas"}, new String [] {"Mushroom"}));
		allMealsStartupData.add(new MealDTO("Curry Chicken Dum", DishType.CURRY, CuisineType.INDIAN, new String [] {"Chicken"}, new String [] {"Tomatoes"}));
		allMealsStartupData.add(new MealDTO("Daal with Aloo Gobhi", DishType.CURRY, CuisineType.INDIAN, new String [] {"Lentils"}, new String [] {"Potato", "Cauliflower"}));
		allMealsStartupData.add(new MealDTO("Lauki in tomato gravy with Egg burjhi", DishType.CURRY, CuisineType.INDIAN, new String [] {"Eggs"}, new String [] {"Squash"}));		
	}

	@Autowired
	private MealRepository mealRepository;

	public static MealDTO testGetSuggestedMeal(DishType dishType, CuisineType cuisine, String[] vegetables, String[] proteins) {

		List<MealDTO> mealNames = allMealsStartupData.stream()
				.filter(m -> m.getCuisine().equals(cuisine))
				.filter(m -> m.getDishType().equals(dishType))
				.filter(m -> Arrays.asList(m.getVegetables()).containsAll(Arrays.asList(vegetables)))
				.filter(m -> Arrays.asList(m.getProteins()).containsAll(Arrays.asList(proteins)))
				.collect(Collectors.toList());
		if (mealNames.size() > 0) { 
			double ran = Math.random();
			int selection = (int) Math.round(ran * (mealNames.size()-1));
			return mealNames.get(selection);
		} else {
			return null;
		}
	}

	public MealDTO getSuggestedMeal(DishType dishType, CuisineType cuisine, String[] vegetables, String[] proteins) {
		List<MealEntity> allMeals = mealRepository.findAll();

		List<MealEntity> mealNames = allMeals.stream()
				.filter(m -> m.getCuisine().equals(cuisine.name()))
				.filter(m -> m.getDishType().equals(dishType.getName()))
				.filter(m -> Arrays.asList(m.getVegetables()).containsAll(Arrays.asList(vegetables)))
				.filter(m -> Arrays.asList(m.getProteins()).containsAll(Arrays.asList(proteins)))
				.collect(Collectors.toList());
		if (mealNames.size() > 0) { 
			double ran = Math.random();
			int selection = (int) Math.round(ran * (mealNames.size()-1));
			return new MealDTO(mealNames.get(selection));
		} else {
			return null;
		}
	}

	public MealDTO addMeal(DishType dishType, CuisineType cuisine, String[] protein, String[] vegetable, String name) {
		MealDTO newMeal = new MealDTO(name, dishType, cuisine, protein, vegetable); 
		MealEntity savedMeal = mealRepository.save(new MealEntity(newMeal));
		return new MealDTO(savedMeal);
	}


	public static void main(String[] args) {

		String[] veg = new String [] {"Squash"};
		MealDTO m  = new MealDTO("M1", DishType.DRY_GRAVY, CuisineType.INDIAN, new String [] {"Eggs"}, veg);
		System.out.println(m);
		veg[0] = "Carrot";
		System.out.println(DishType.DRY_GRAVY);
		//		System.out.println(getSuggestedMeal(DishType.CURRY, CuisineType.INDIAN, new String [] {"Eggs"}, new String [] {"Squash"}));
		//		System.out.println(getSuggestedMeal(DishType.CURRY, CuisineType.INDIAN, new String [] {"Cottage Cheese"}, new String [] {"Capsicum"}));
		//		System.out.println(getSuggestedMeal(DishType.CURRY, CuisineType.INDIAN, new String [] {"Cottage Cheese"}, new String [] {"Capsicum"}));
		//		System.out.println(getSuggestedMeal(DishType.CURRY, CuisineType.INDIAN, new String [] {"Cottage Cheese", "Green Peas"}, new String [] {"Capsicum"}));
		//		System.out.println(getSuggestedMeal(DishType.CURRY, CuisineType.INDIAN, new String [] {"Cottage Cheese", "Green Peas"}, new String [] {"Tomatoes"}));
		//		System.out.println(getSuggestedMeal(DishType.CURRY, CuisineType.INDIAN, new String [] {"Green Peas"}, new String [] {"Capsicum"}));
		//		System.out.println(getSuggestedMeal(DishType.CURRY, CuisineType.INDIAN, new String [] {"Green Peas"}, new String [] {"Mushroom"}));
	}


}
