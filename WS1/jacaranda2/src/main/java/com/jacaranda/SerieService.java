package com.jacaranda;

import org.springframework.stereotype.Service;

@Service
public class SerieService {

	public boolean isValidTitle(String title) {
		return title != null; //devuelve true, si el título no es nulo
	}
	
}
