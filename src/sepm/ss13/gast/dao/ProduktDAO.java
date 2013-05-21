package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.Produkt;
import sepm.ss13.gast.domain.ProduktKategorie;

public interface ProduktDAO {

	public Produkt create(Produkt p) throws DAOException;

	public ArrayList<Produkt> search(Produkt p) throws DAOException;

	public void update(Produkt p) throws DAOException;

	public void delete(Produkt p) throws DAOException;
}