package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.Ware;

public interface WareDAO {

	public Ware create(Ware w) throws DAOException;
	
	public ArrayList<Ware> search(Ware w) throws DAOException;
	
	public void update(Ware ek) throws DAOException;
	
	public void delete(Ware ek) throws DAOException;
}
