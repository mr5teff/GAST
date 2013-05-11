package sepm.ss13.gast.dao;

import java.util.ArrayList;

import sepm.ss13.gast.domain.Rechnung;

public interface RechnungDAO {

	public Rechnung create(Rechnung r) throws DAOException;

	public ArrayList<Rechnung> search(Rechnung r) throws DAOException;

}