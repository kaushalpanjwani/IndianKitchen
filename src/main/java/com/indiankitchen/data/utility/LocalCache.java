package com.indiankitchen.data.utility;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Component
public class LocalCache {
	
	static final Logger LOG = LoggerFactory.getLogger(LocalCache.class);

	private Cache<String, Set<String>> alreadySuggestedMeals = Caffeine
			.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES)
			.removalListener((key, value, cause) -> LOG.debug("Removed {}-{} due to {}", key,value, cause))
			.build();

	public void addToCache(String sessionId, String mealName) {

		Set<String> mealsAlreadySuggested = alreadySuggestedMeals.get(sessionId,k -> {
			return new HashSet<String>();
		});

		mealsAlreadySuggested.add(mealName);
	}

	public boolean checkIfExistsElseAdd(String sessionId, String mealName) {

		Set<String> mealsAlreadySuggested = alreadySuggestedMeals.getIfPresent(sessionId);
		if (mealsAlreadySuggested != null && mealsAlreadySuggested.contains(mealName)) {
			return true;
		} else {
			addToCache(sessionId, mealName);
			return false;
		}

	}

	public Set<String> getAlreadySuggestedMealsForCurrentSession(String sessionId) {
		Set<String> mealsAlreadySuggested = alreadySuggestedMeals.getIfPresent(sessionId);
		if (mealsAlreadySuggested != null) {
			return mealsAlreadySuggested;
		} else {
			return new HashSet<String>();
		}
	}
}
