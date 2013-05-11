package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.Bestellung;

public interface BestellungDAO {

	public Bestellung create(Bestellung b) throws DAOException;

	public ArrayList<Bestellung> search(Bestellung b) throws DAOException;

	public void delete(Bestellung b) throws DAOException;

}