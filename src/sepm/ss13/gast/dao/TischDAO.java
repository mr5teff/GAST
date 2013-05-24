package sepm.ss13.gast.dao;

import java.util.ArrayList;
import sepm.ss13.gast.domain.Tisch;

public interface TischDAO {

	public Tisch create(Tisch t) throws DAOException;

	public ArrayList<Tisch> search(Tisch t) throws DAOException;

	public void update(Tisch t) throws DAOException;
	
	public void delete(Tisch t) throws DAOException;
}
