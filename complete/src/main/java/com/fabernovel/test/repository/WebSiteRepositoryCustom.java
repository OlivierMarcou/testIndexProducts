package com.fabernovel.test.repository;

import com.fabernovel.test.domain.Announcement;
import com.fabernovel.test.domain.Product;
import com.fabernovel.test.domain.WebSite;

import java.util.List;

public interface WebSiteRepositoryCustom {
	List<Announcement> annoncesParProduit(Product product);
	List<Announcement> annoncesParSite(WebSite webSite);
	List<Product> produitsParSite(WebSite webSite);
	List<WebSite> siteParProduit(Product product);
	List<Announcement> annoncesParProduitEtParSite(Product product, WebSite webSite);
}
