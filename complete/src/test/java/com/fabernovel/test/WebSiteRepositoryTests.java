package com.fabernovel.test;


import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.fabernovel.test.domain.Announcement;
import com.fabernovel.test.domain.Product;
import com.fabernovel.test.domain.WebSite;
import com.fabernovel.test.repository.WebSiteRepository;
import com.fabernovel.test.repository.WebSiteRepositoryCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

@SpringBootTest
public class WebSiteRepositoryTests {

	@Autowired
	WebSiteRepository webSiteRepository;
	@Autowired
	WebSiteRepositoryCustom webSiteRepositoryCustom;

	WebSite sfr, free, bouyguesTelecom;

	Product galaxyNote10, iphone;
	Announcement blackFriday, changePriceIphone;
	@BeforeEach
	public void setUp() {

		webSiteRepository.deleteAll();
		iphone = new Product( "Iphone 10",
				Arrays.asList(
				)
		);
		galaxyNote10 = new Product( "Galaxy note 10",
				Arrays.asList(
				)
		);
		free = webSiteRepository.save(
				new WebSite(1L, "www.free.fr",
						Arrays.asList(
								new Product( "Iphone 10",
										Arrays.asList(
												new Announcement("First launch price 1200$"),
												new Announcement("Black friday price 600$")
										)
								),
								new Product( "Galaxy note 10",
										Arrays.asList(
												new Announcement("First launch price 1500$"),
												new Announcement("Black friday price 800$")
										)
								)
						)
				)
		);

		sfr = webSiteRepository.save(
				new WebSite(2, "www.sfr.fr",
						Arrays.asList(
								new Product( "Galaxy note 10",
										Arrays.asList(
												new Announcement("Price 259$"),
												new Announcement("Black friday price 100$")
										)
								),
								new Product( "Galaxy A10",
										Arrays.asList(
												new Announcement("Price 300$")
										)
								),
								new Product( "Xiaomi note 9",
										Arrays.asList(
												new Announcement("Price 400$"),
												new Announcement("Black Friday price 200$")
										)
								)
						)
				)
		);

	}

	@Test
	public void findsByLastName() {
		List<WebSite> result = webSiteRepository.findByUrl("www.sfr.fr");
		assertThat(result).hasSize(1).extracting("url").contains("www.sfr.fr");
	}

	@Test
	public void findsByExample() {

		WebSite probe = new WebSite( 2, "www.sfr.fr", null);

		List<WebSite> result = webSiteRepository.findAll(Example.of(probe));

		assertThat(result).hasSize(1).extracting("url").contains("www.sfr.fr");
	}

	@Test
	public void retrieveByProduct(){
		List<Announcement> announcements = webSiteRepositoryCustom.annoncesParProduit(iphone);
		assertThat(announcements).hasSize(2);
	}
	@Test
	public void retrieveAllAnnoucementByWebSite(){
		List<Announcement> announcements = webSiteRepositoryCustom.annoncesParSite(sfr);
		assertThat(announcements).hasSize(5);
	}
	@Test
	public void retrieveAllProductByWebSite(){
		List<Product> products = webSiteRepositoryCustom.produitsParSite(sfr);
		assertThat(products).hasSize(3);
	}
	@Test
	public void retrieveAllWebSiteByProduct(){
		List<WebSite> webSites = webSiteRepositoryCustom.siteParProduit(galaxyNote10);
		assertThat(webSites).hasSize(2);
	}
	@Test
	public void retrieveAllAnnoucementByWebSiteAndProduct(){
		List<Announcement> announcements = webSiteRepositoryCustom.annoncesParProduitEtParSite(galaxyNote10, sfr);
		assertThat(announcements).hasSize(2);
	}

}
