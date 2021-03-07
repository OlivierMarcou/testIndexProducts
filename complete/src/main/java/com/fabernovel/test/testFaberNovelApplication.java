package com.fabernovel.test;

import com.fabernovel.test.domain.Announcement;
import com.fabernovel.test.domain.Product;
import com.fabernovel.test.domain.WebSite;
import com.fabernovel.test.repository.WebSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class testFaberNovelApplication implements CommandLineRunner {

	@Autowired
	private WebSiteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(testFaberNovelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		repository.save(
			new WebSite(1L, "www.free.fr",
				Arrays.asList(
					new Product( "Iphone 10",
						Arrays.asList(
							new Announcement("First launch price 1200$"),
							new Announcement("Black friday price 600$")
						)
					),
					new Product( "Galaxy note 20",
						Arrays.asList(
							new Announcement("First launch price 1500$"),
							new Announcement("Black friday price 800$")
						)
					)
				)
			)
		);

		repository.save(
			new WebSite(2, "www.sfr.fr",
				Arrays.asList(
					new Product( "Galaxy note 20 pink",
						Arrays.asList(
							new Announcement("Price 1259$"),
							new Announcement("Black friday price 600$")
						)
					),
					new Product( "Galaxy A10",
						Arrays.asList(
							new Announcement("Price 300$"),
							new Announcement("Black friday price 199$")
						)
					)
				)
			)
		);
		List<WebSite> webSites = null;

		System.out.println("========== All WebSite ==========");
		webSites = repository.findAll();
		webSites.forEach(System.out::println);

		System.out.println("==========Find a webstire by url ==========");
		webSites = repository.findByUrl("www.sfr.fr");
		webSites.forEach(System.out::println);

		System.out.println("WebSite found with findByUrl('www.free.fr'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByUrl("www.free.fr"));


	}

}
