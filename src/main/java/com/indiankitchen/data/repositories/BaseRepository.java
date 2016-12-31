package com.indiankitchen.data.repositories;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.indiankitchen.data.BaseEntity;

public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends MongoRepository<T, Serializable> {

}
