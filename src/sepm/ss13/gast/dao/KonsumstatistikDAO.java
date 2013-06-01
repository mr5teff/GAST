package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.Konsumstatistik;

public interface KonsumstatistikDAO 
{
	public ArrayList<Konsumstatistik> searchPopularProducts() throws DAOException;
}
