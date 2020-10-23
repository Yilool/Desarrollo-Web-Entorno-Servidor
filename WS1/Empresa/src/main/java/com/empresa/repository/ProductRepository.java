package com.empresa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
//	public static final EntityManager em = null;
//	public default Product getById(Integer id) {
//		String sql = "select * from Product where prdId = '" + id + ";";
//		Query q = em.createNamedQuery(sql);
//		return null;
//	}
//	
//	
//	public default  List<Product> getAllOrderById() {
//		List<Product> resultList = new ArrayList<>();
//		findAll().forEach(resultList::add);
//		
//		return resultList.stream().sorted().collect(Collectors.toList());
//	}
	
	public List<Product> findAllOrderedById();
}
