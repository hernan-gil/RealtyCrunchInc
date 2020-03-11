package com.example.demo;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.err.println("File reading start...");
		leerArchivo();
		System.err.println("File reading finish.");
	}

	public static void leerArchivo() {
		String dir = "C:\\Users\\Hero\\Documents\\eclipse-workspace\\demo\\src\\main\\java\\com\\example\\demo\\html\\";
		File file = new File(dir+"listing1.html");
		Document document = null;
		try {
			document = Jsoup.parse(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements contents = document.getElementsByAttributeValue("data-tn", "listing-page-summary-price");
		System.err.println("Price: " + contents.get(0).getElementsByClass("textIntent-title2").text());
		contents = document.getElementsByAttributeValue("data-tn", "listing-page-summary-beds");
		System.err.println("Beds: " + contents.get(0).getElementsByClass("textIntent-title2").text());
		contents = document.getElementsByAttributeValue("data-tn", "listing-page-summary-baths");
		System.err.println("Baths: " + contents.get(0).getElementsByClass("textIntent-title2").text());
		Element address = document.getElementsByClass("sc-dxgOiQ iPjopb").first();
		System.err.println("Adress: " + address.getElementsByClass("textIntent-title1").text() + " " + address.getElementsByClass("textIntent-caption2").text());
		System.err.println("Image URLs:");
		contents = document.getElementsByAttributeValue("data-tn", "undefined-gallery-hero-image");
		int i = 1;
		for (Element item : contents) {
			System.err.println("["+i+"] ->" + "https:" + item.attr("data-src"));
			i++;
		}
	}
}
