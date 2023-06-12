package com.hans.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.Edificio;

@Repository
public interface EdificioPagebleRepository extends PagingAndSortingRepository<Edificio, Long>{

}
