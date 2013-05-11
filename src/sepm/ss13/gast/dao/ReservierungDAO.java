package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.Reservierung;

public interface ReservierungDAO {

public Reservierung create(Reservierung r) throws DAOException;
	
	public ArrayList<Reservierung> search(Reservierung r) throws DAOException;
	
	public void update(Reservierung r) throws DAOException;
	
	public void delete(Reservierung r) throws DAOException;
}
