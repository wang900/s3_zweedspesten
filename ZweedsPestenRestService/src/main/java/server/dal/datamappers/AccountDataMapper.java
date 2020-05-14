package server.dal.datamappers;

import dataacces.BaseDataMapper;
import dto.model.Account;
import logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDataMapper<T> extends BaseDataMapper<Account> {

    @Override
    public String mapToSqlInternal(Account account) {

        String sqlQuery;
        if(account.getEntityId() == 0){
            sqlQuery = "insert into accounts (UserName, Password) values ('" + account.getUsername() + "', '" + account.getPassword() + "')";
        }
        else {
            sqlQuery = "update accounts set UserName='" + account.getUsername() + "', Password='" + account.getPassword() +"' where Id=" + account.getEntityId();
        }
            return sqlQuery;
    }

    @Override
    public List<Account> mapFromDatabaseInternal(ResultSet rs) {
        ArrayList<Account> players = new ArrayList<>();
        try {
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                Account a = new Account(name, password);
                a.setEntityId(id);
                players.add(a);
            }
            return players;
        }
        catch(SQLException ex)
        {
            Logger.getInstance().log(ex);
            return players;
        }
    }
}
