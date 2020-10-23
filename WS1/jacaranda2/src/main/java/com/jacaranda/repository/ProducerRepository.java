package com.jacaranda.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.Producer;

@Repository(value = "producerRepository")
public interface ProducerRepository extends CrudRepository<Producer, Integer>{

}
