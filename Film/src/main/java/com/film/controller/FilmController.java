package com.film.controller;

import java.time.LocalDate;

import com.film.entity.Category;
import com.film.entity.Film;
import com.film.entity.Odul;
import com.film.entity.Oyuncu;
import com.film.entity.Yonetmen;
import com.film.repository.CategoryDao;
import com.film.repository.OdulDao;
import com.film.repository.OyuncuDao;
import com.film.repository.YonetmenDao;
import com.film.service.FilmService;
import com.film.util.BAUtils;

public class FilmController {
	
	private FilmService filmService;
	private OdulDao odulDao;
	private OyuncuDao oyuncuDao;
	private YonetmenDao yonetmenDao;
	private CategoryDao categoryDao;
	
	public FilmController() {
		this.filmService = new FilmService();
		this.odulDao = new OdulDao();
		this.oyuncuDao = new OyuncuDao();
		this.yonetmenDao = new YonetmenDao();
		this.categoryDao = new CategoryDao();
	}
	public void createFilm() {
		
		Film film = new Film();
		String FilmIsmi = BAUtils.readString("film ismini giriniz ");
		String YonetmenIsmi = BAUtils.readString("yonetmen ismini giriniz ");
		String OyuncuIsmi = BAUtils.readString("oyuncu ismini giriniz ");
		String OdulIsmi = BAUtils.readString("odul ismini giriniz ");
		String kategory = BAUtils.readString("kategory ismini giriniz ");
		
		Oyuncu oyuncu = new Oyuncu(OyuncuIsmi);
		oyuncuDao.create(oyuncu);

		Odul odul = new Odul();
		odul.setName(OdulIsmi);
		odulDao.create(odul);
		
		Yonetmen yonetmen = new Yonetmen();
		yonetmen.setName(YonetmenIsmi);
		yonetmen.setOdul(odul);

		yonetmenDao.create(yonetmen);
		
		Category category = new Category();
		category.setName(kategory);
		categoryDao.create(category);

		film.getCategory().add(category);
		film.setName(FilmIsmi);
		film.getOyuncu().add(oyuncu);
		film.setProduction_date(LocalDate.now());
		film.setYonetmen(yonetmen);
		film.setOdul(odul);
		filmService.create(film);
		
	}
	public void delete() {
		int id = BAUtils.readInt("Lütfen silme istediğiniz filmin ID sini giriniz: ");
		filmService.delete(id);
	}
	public void find() {
		long id = BAUtils.readInt("Liddddz: ");
		filmService.find(id);
	}
	
	//update delete, list
	
}
