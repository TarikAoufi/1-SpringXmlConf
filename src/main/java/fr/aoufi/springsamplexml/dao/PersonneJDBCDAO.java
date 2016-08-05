package fr.aoufi.springsamplexml.dao;

import fr.aoufi.springsamplexml.model.Personne;


public class PersonneJDBCDAO implements PersonneDAO {

	public Personne save(Personne personne) {
		System.out.println("Méthode JDBC");
		return null;
	}

}
