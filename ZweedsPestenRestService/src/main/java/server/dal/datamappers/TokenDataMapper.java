package server.dal.datamappers;

import dataacces.BaseDataMapper;
import dto.model.Token;
import logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TokenDataMapper<T> extends BaseDataMapper<Token> {

    @Override
    public String mapToSqlInternal(Token token) {
        String sqlQuery;

        if(token.getEntityId() == 0){
            sqlQuery = "insert into tokens (TokenText, CreationDate, TimeToLive, UserId)" +
                        " values ('" + token.getTokenText() + "', '" + token.getCreationDate().toString() + "', '" + token.getTimeToLive() + "', '" + token.getPlayerId() + "')";
        }
        else {
            sqlQuery = "update Tokens set TokenText='" + token.getTokenText() + "', TimeToLive='" + token.getTimeToLive() +"' where Id=" + token.getEntityId();
        }
        return sqlQuery;
    }

    @Override
    public List<Token> mapFromDatabaseInternal(ResultSet rs) {
        ArrayList<Token> tokens = new ArrayList<>();

        try {
            while (rs.next()) {
                long id = rs.getLong(1);
                String text = rs.getString(2);
                Date date = new Date((rs.getString(3)));
                int ttl = rs.getInt(4);
                long playerId = rs.getLong(5);
                Token t = new Token(text, date, ttl, playerId);
                t.setEntityId(id);
                tokens.add(t);
            }
            return tokens;
        }
        catch(SQLException ex)
        {
            Logger.getInstance().log(ex);
            return tokens;
        }
    }
}
