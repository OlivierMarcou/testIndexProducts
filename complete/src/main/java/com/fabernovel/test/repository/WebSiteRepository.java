package com.fabernovel.test.repository;

import com.fabernovel.test.domain.WebSite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebSiteRepository extends MongoRepository<WebSite, String> {

	List<WebSite> findByUrl(String url);

	@Query("{ 'products.name' : ?0}")
	List<WebSite> findWebSiteByProductName(String name);

}
