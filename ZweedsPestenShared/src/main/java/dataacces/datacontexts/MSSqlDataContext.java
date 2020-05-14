package dataacces.datacontexts;

import dataacces.BaseDataMapper;
import dataacces.DataMapperFactoryBase;
import dataacces.Entity;
import dataacces.PropertyReader;
import dataacces.datacontexts.interfaces.IDataContext;
import logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MSSqlDataContext implements IDataContext {

    private DataMapperFactoryBase mapperFactory;


    public MSSqlDataContext (DataMapperFactoryBase mapperFactory)
    {
        this.mapperFactory = mapperFactory;
    }

    public Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Load the SQLServerDriver class, build the
        // connection string, and get a connection
        String[] data = PropertyReader.getDataBaseProperties();

        try {
                    String connectionUrl = "jdbc:mysql://" +
                    data[0] + "/" + data[1] +
                    "?user=" + data[1] +
                    "&password=" + data[2];
            return DriverManager.getConnection(connectionUrl);
        }
        catch(Exception e)
        {
            Logger.getInstance().log(e);
            return null;
        }
    }

    public boolean executeNonQuery(String query)
    {
        try
        {
            Connection con = getConnection();
            // Create and execute an SQL statement that returns some data.
            try (Statement stmt = con.createStatement()) {
                return stmt.execute(query);
            }
        }
        catch(Exception e)
        {
            Logger.getInstance().log(e);
            return false;
        }
    }

    private <T> List<T> executeQuery(String query, Class<T> returnType, String simpleType) {
        try
        {
            Connection con = getConnection();
                // Create and execute an SQL statement that returns some data.
            try (Statement stmt = con.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    BaseDataMapper<T> mapper = null;
                    mapper = mapperFactory.getMapper(returnType);
                    if(mapper != null)
                        return mapper.mapFromDatabase(rs);
                    else {
                        mapper = mapperFactory.getMapper(simpleType);
                        return mapper.mapFromDatabase(rs);
                    }
                }
            }
        }
        catch(Exception e)
        {
            Logger.getInstance().log(e);
            return new ArrayList();
        }
    }

    public <T> T getSingle(long id, Class<T> returnType, String simpleType)
    {
        List<T> list = executeQuery("select top 1 * from "+ simpleType +"s where Id=" + id, returnType, simpleType);
        if(list != null && !list.isEmpty())
            return list.get(0);
        else
            return null;
    }

    public <T> List<T> getAll(Class<T> returnType, String simpleType)
    {
        String query = "select * from "+ simpleType +"s";
        return executeQuery(query , returnType, simpleType);
    }

    private <T> void writeToDatabase(Object obj, Class<T> returnType)
    {
        BaseDataMapper<T> mapper = null;
        mapper = mapperFactory.getMapper(returnType);
        if(mapper != null) {
            String sql = mapper.mapToSql(obj);
            executeNonQuery(sql);
        }
    }

    public <T> void add(Entity obj, Class<T> returnType)
    {
        writeToDatabase(obj, returnType);
    }

    public <T> void update(Entity obj, Class<T> returnType, String simpleType)
    {
        writeToDatabase(obj, returnType);
    }

    public <T> void remove(Entity obj, Class<T> returnType, String simpleType)
    {
        String sql = "delete from ["+ simpleType +"s] where Id=" + obj.getEntityId();
        executeNonQuery(sql);
    }
}
