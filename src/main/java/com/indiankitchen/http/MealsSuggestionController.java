package com.indiankitchen.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indiankitchen.data.MealDTO;
import com.indiankitchen.data.constants.CuisineType;
import com.indiankitchen.data.constants.DishType;
import com.indiankitchen.data.constants.IntentType;
import com.indiankitchen.data.request.MealSuggestionRequest;
import com.indiankitchen.data.request.Metadata;
import com.indiankitchen.data.request.Parameters;
import com.indiankitchen.data.response.MealSuggestionResponse;
import com.indiankitchen.data.services.MealService;

@RestController
public class MealsSuggestionController {

	static final Logger LOG = LoggerFactory.getLogger(MealsSuggestionController.class);

	@Autowired
	private MealService mealsService;

	@RequestMapping(value = "/suggest")
	public MealSuggestionResponse suggest(@RequestBody MealSuggestionRequest mealSuggestionRequest) {
		StringBuilder speechBuilder = new StringBuilder();
		try {
			LOG.trace("Request : /suggest {} ", mealSuggestionRequest);
			LOG.debug("Request : Query: {} ", mealSuggestionRequest.getResult().getResolvedQuery());
			Parameters params = mealSuggestionRequest.getResult().getParameters();
			Metadata metadata = mealSuggestionRequest.getResult().getMetadata();
			String sessionId = mealSuggestionRequest.getSessionId();
			if (metadata != null && metadata.getIntentName() != null) {
				LOG.debug("Request : Metadata {} ", metadata);
				LOG.debug("Request : Params {} ", params);

				if (metadata.getIntentName().equals(IntentType.SUGGEST_A_MEAL) || metadata.getIntentName().equals(IntentType.SUGGEST_ANOTHER_MEAL)) {
					MealDTO suggestedMeal = mealsService.getSuggestedMeal(
							sessionId,
							DishType.getByName(params.getDishType()),
							CuisineType.valueOf(params.getCuisine().toUpperCase()),
							params.getVegetable(),params.getProtein());
					if (suggestedMeal != null) {
						speechBuilder.append("Alright, I would suggest ");
						speechBuilder.append(suggestedMeal.getName());
						speechBuilder.append(". If you want to get more options, say MORE OPTIONS.");
						LOG.trace("Meals suggestion : {} for Session : {}",suggestedMeal.getName(),sessionId);
					} else {
						speechBuilder.append("Sorry, unfortunately I do not know any dish which is ");
						speechBuilder.append(params.getCuisine()).append(" ");
						speechBuilder.append(params.getDishType()).append(". And is made of ");
						for (int i = 0; i < params.getProtein().length; i++) {
							if (params.getProtein()[i].equalsIgnoreCase("None"))
								continue;

							speechBuilder.append(params.getProtein()[i]).append(" and ");
						}
						for (int i = 0; i < params.getVegetable().length; i++) {
							if (params.getVegetable()[i].equalsIgnoreCase("None"))
								continue;

							speechBuilder.append(params.getVegetable()[i]).append(" and ");
						}
						speechBuilder.delete(speechBuilder.length() - 5, speechBuilder.length());
						speechBuilder.append(". If you want to try other combinations, ask for DIFFERENT COMBINATION");
						LOG.warn("Meals suggestion not found : {} for Session : {}",speechBuilder,sessionId);
					}
				} else if (metadata.getIntentName().equals(IntentType.ADD_A_MEAL)) {
					MealDTO addedMeal = mealsService.addMeal(
							DishType.getByName(params.getDishType()),
							CuisineType.valueOf(params.getCuisine().toUpperCase()),
							params.getProtein(), params.getVegetable(), params.getName());
					speechBuilder.append("Thank you! We have successfully added ");
					speechBuilder.append(addedMeal.getName());
				} else if (metadata.getIntentName().equals(IntentType.SURPRISE_ME) || metadata.getIntentName().equals(IntentType.SURPRISE_ME_AGAIN)) {
					MealDTO suggestedMeal = null;
					String surpriseDishTypeFilter = params.getDishType();
					if (!StringUtils.isEmpty(surpriseDishTypeFilter))
					{
						suggestedMeal = mealsService.getSurpriseSuggestion(sessionId, DishType.getByName(surpriseDishTypeFilter));
					} 
					else
					{
						suggestedMeal = mealsService.getSurpriseSuggestion(sessionId);
					}
					if (suggestedMeal != null) {
						speechBuilder.append("How about ");
						speechBuilder.append(suggestedMeal.getName());
						speechBuilder.append(". It is ").append(suggestedMeal.getCuisine().name()).append(" ").append(suggestedMeal.getDishType().getName());
						speechBuilder.append(". You would need ");
						for (int i = 0; i < suggestedMeal.getProteins().length; i++) {
							if (suggestedMeal.getProteins()[i].equalsIgnoreCase("None"))
								continue;

							speechBuilder.append(suggestedMeal.getProteins()[i]).append(" and ");
						}
						for (int i = 0; i < suggestedMeal.getVegetables().length; i++) {
							if (suggestedMeal.getVegetables()[i].equalsIgnoreCase("None"))
								continue;

							speechBuilder.append(suggestedMeal.getVegetables()[i]).append(" and ");
						}
						speechBuilder.delete(speechBuilder.length() - 5, speechBuilder.length());
						speechBuilder.append(". If you want another options, say SURPRISE AGAIN.");
						LOG.trace("Meals suggestion : {} for Session : {}",suggestedMeal.getName(),sessionId);
					} else {
						speechBuilder.append("Sorry, no more options to surprise you!");
					}
				} else {
					speechBuilder.append("Sorry, I cannot do that yet!");
				}
			} else {
				speechBuilder.append("Sorry, I am not sure how to help you!");
			}
		} catch (Exception e) {
			LOG.error("Exception in processing request ", e);
			speechBuilder.append("Sorry, something went wrong. Please try later!");
		}
		return new MealSuggestionResponse() {{
			setSpeech(speechBuilder.toString());
			setDisplayText(speechBuilder.toString());
		}};
	}

}
