package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.Einkauf;

public interface EinkaufDAO {

	public Einkauf create(Einkauf ek) throws DAOException;
	
	public ArrayList<Einkauf> search(Einkauf ek) throws DAOException;
	
	public void update(Einkauf ek) throws DAOException;
	
	public void delete(Einkauf ek) throws DAOException;
}
