package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.Rezept;

public interface RezeptDAO {
	public Rezept create(Rezept toCreate) throws DAOException;
	
	public ArrayList<Rezept> search(Rezept toSearch) throws DAOException;
	
	public void update(Rezept toUpdate) throws DAOException;
	
	public void delete(Rezept toDelete) throws DAOException;
}
