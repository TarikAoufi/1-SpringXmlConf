package fr.aoufi.springsamplexml.dao;

import fr.aoufi.springsamplexml.model.Personne;


public class PersonneJPADAO implements PersonneDAO {

	public Personne save(Personne personne) {
		System.out.println("Méthode JPA");
		return null;
	}

}
