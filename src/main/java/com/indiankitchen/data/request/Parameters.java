
package com.indiankitchen.data.request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "DishType",
    "Cuisine",
    "Protein",
    "Vegetable",
    "Name"
})
public class Parameters {

    @JsonProperty("DishType")
    private String dishType;
    @JsonProperty("Cuisine")
    private String cuisine;
    @JsonProperty("Protein")
    private String[] protein = null;
    @JsonProperty("Vegetable")
    private String[] vegetable = null;
    @JsonProperty("Name")
    private String name = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("DishType")
    public String getDishType() {
        return dishType;
    }

    @JsonProperty("DishType")
    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    @JsonProperty("Cuisine")
    public String getCuisine() {
        return cuisine;
    }

    @JsonProperty("Cuisine")
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @JsonProperty("Protein")
    public String[] getProtein() {
        return protein;
    }

    @JsonProperty("Protein")
    public void setProtein(String[] protein) {
        this.protein = protein;
    }

    @JsonProperty("Vegetable")
    public String[] getVegetable() {
        return vegetable;
    }

    @JsonProperty("Vegetable")
    public void setVegetable(String[] vegetable) {
        this.vegetable = vegetable;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    @JsonProperty("Name")
	public String getName() {
		return name;
	}
    @JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parameters [dishType=").append(dishType)
				.append(", cuisine=").append(cuisine).append(", protein=")
				.append(Arrays.toString(protein)).append(", vegetable=")
				.append(Arrays.toString(vegetable)).append(", name=")
				.append(name).append(", additionalProperties=")
				.append(additionalProperties).append("]");
		return builder.toString();
	}

}
