package com.empresa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	public Product findProductById(Integer id);
//	public static final EntityManager em = null;
//	public default Product getById(Integer id) {
//		String sql = "select * from Product where prdId = '" + id + ";";
//		Query q = em.createNamedQuery(sql);
//		return null;
//	}
//	
//	public List<Product> findAllOrderedById();

	public default List<Product> getAllOrderById() {
		List<Product> resultList = new ArrayList<>();
		findAll().forEach(resultList::add);
		
		return resultList.stream().sorted().collect(Collectors.toList());
	}
}
