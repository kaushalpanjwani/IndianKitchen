package com.indiankitchen;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.indiankitchen.data.MealDTO;
import com.indiankitchen.data.MealEntity;
import com.indiankitchen.data.repositories.MealRepository;
import com.indiankitchen.data.utility.MealService;


@SpringBootApplication
public class Application implements CommandLineRunner{

	@Bean
	public CountDownLatch closeLatch() {
		return new CountDownLatch(1);
	}

	@Autowired
	private MealRepository mealRepository;


	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}


	public void run(String... args) throws Exception {
		prepareMockDBData();

	}


	private void prepareMockDBData() {
//		for (MealDTO mealDTO : MealService.allMealsStartupData) {
//			mealRepository.save(new MealEntity(mealDTO));
//		}

	}
}