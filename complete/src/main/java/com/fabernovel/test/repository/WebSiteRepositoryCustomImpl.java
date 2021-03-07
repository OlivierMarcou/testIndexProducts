package com.fabernovel.test.repository;

import com.fabernovel.test.domain.Announcement;
import com.fabernovel.test.domain.Product;
import com.fabernovel.test.domain.WebSite;
import com.fabernovel.test.execption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WebSiteRepositoryCustomImpl implements WebSiteRepositoryCustom{

	@Autowired
	WebSiteRepository webSiteRepository;

	@Override
	public List<Announcement> annoncesParProduit(Product product){
		List<WebSite> webSites = webSiteRepository.findWebSiteByProductName(product.getName());
		if(webSites == null)
			throw new NotFoundException("product not found");
		return getAnnouncementListByProduct(product, webSites);
	}

	@Override
	public List<Announcement> annoncesParSite(WebSite webSite) {
		List<WebSite> webSites = verifyWebsite(webSite);
		return getAnnouncementList(webSites);
	}

	@Override
	public List<Product> produitsParSite(WebSite webSite) {
		List<WebSite> webSites = verifyWebsite(webSite);
		return getproductList(webSites);
	}

	@Override
	public List<WebSite> siteParProduit(Product product) {
		List<WebSite> webSites = webSiteRepository.findWebSiteByProductName(product.getName());
		return webSites;
	}

	@Override
	public List<Announcement> annoncesParProduitEtParSite(Product product, WebSite webSite) {
		if(webSite == null)
			throw new NullPointerException("webSite can't be null");
		List<WebSite> webSites = webSiteRepository.findWebSiteByProductName(product.getName());
		List<Announcement> announcements = new ArrayList<>();
		webSites.stream().forEach(v -> {
			if(v.getId() == webSite.getId())
				v.getProducts().stream().forEach(
						p->{
							if(p.getName().equals(product.getName()))
								announcements.addAll(p.getAnnouncements());
						});
		});
		return announcements;
	}


	private List<WebSite> verifyWebsite(WebSite webSite) {
		if(webSite == null)
			throw new NullPointerException("webSite can't be null");
		List<WebSite> webSites = webSiteRepository.findByUrl(webSite.getUrl());
		if(webSites == null)
			throw new NotFoundException("webSite not found");
		return webSites;
	}

	private List<Product> getproductList(List<WebSite> webSites) {
		List<Product> products = new ArrayList<>();
		webSites.stream().forEach(v -> products.addAll(v.getProducts()));
		return products;
	}

	private List<Announcement> getAnnouncementList(List<WebSite> webSites) {
		List<Announcement> announcements = new ArrayList<>();
		webSites.stream().forEach(v ->
				v.getProducts().stream().forEach(
						p->{
							announcements.addAll(p.getAnnouncements());
						})
		);
		return announcements;
	}

	private List<Announcement> getAnnouncementListByProduct(Product product, List<WebSite> webSites) {
		List<Announcement> announcements = new ArrayList<>();
		webSites.stream().forEach(v ->
				v.getProducts().stream().forEach(
						p->{
							if(product.getName().equals(p.getName()))
								announcements.addAll(p.getAnnouncements());
						})
		);
		return announcements;
	}
}
