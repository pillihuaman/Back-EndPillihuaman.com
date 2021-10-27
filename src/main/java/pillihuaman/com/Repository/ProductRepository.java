package pillihuaman.com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import pillihuaman.com.BusinessEntity.model.Product;



public interface ProductRepository extends JpaRepository<Product, Integer>{
	   //String findByBase(int  baseId);
	  // Optional<TBLProduct> findByUserName(int  idPrice);
}
