package models;

import java.util.List;

public class SettingsDaoImplementation implements GenericDAO<Settings> {

    private final int DEFAULT_SETTINGS_CONFIG = 1;
    List<Settings> settings;

    @Override
    public List<Settings> getAll() throws DAOException {
        return settings;
    }

    @Override
    public Settings getByIndex(int index) throws DAOException {
        return settings.get(index);
    }

    @Override
    public void save(Settings settings) throws DAOException {

    }

    @Override
    public void update(int index, Settings settings) throws DAOException {

    }

    @Override
    public void delete(int index, Settings settings) throws DAOException {

    }

    @Override
    public void deleteAll() throws DAOException {

    }
}
