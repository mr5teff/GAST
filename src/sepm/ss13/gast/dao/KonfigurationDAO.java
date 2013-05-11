package sepm.ss13.gast.dao;

import sepm.ss13.gast.domain.Konfiguration;

public interface KonfigurationDAO {

	public Konfiguration load() throws DAOException;

	public void save(Konfiguration k) throws DAOException;

}