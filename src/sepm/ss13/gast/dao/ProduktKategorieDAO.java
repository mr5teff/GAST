package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.ProduktKategorie;

public interface ProduktKategorieDAO {

	public ProduktKategorie create(ProduktKategorie p) throws DAOException;

	public ArrayList<ProduktKategorie> search(ProduktKategorie p)
			throws DAOException;

	public void update(ProduktKategorie p) throws DAOException;

	public void delete(ProduktKategorie p) throws DAOException;

}