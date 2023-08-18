package com.tracymedcalf.warehouseapp;

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SkuRepository extends PagingAndSortingRepository<Sku, Long> { 

}