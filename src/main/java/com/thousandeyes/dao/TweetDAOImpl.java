package com.thousandeyes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.thousandeyes.exception.DAOException;
import com.thousandeyes.model.Tweet;
import com.thousandeyes.model.User;


@Repository
public class TweetDAOImpl implements TweetDAO{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<Tweet> tweetList(User user, List<User> followerList, String searchParam) throws DAOException
	{
		
		List<Integer> userIds = new ArrayList<Integer>(); 
		userIds.add(user.getId());
		for(User usr: followerList)
		{
			userIds.add(usr.getId());
		}
		
		Map<String, List> namedParameters = new HashMap<String, List>(); 
		namedParameters.put("listOfUser", userIds);
		List<Tweet> tweetList = null;
		if(searchParam == null)
		{
			tweetList = namedParameterJdbcTemplate.query( "select id, person_id, content from tweet where person_id IN ( :listOfUser )", namedParameters,
			new RowMapper<Tweet>() {
            public Tweet mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tweet tweet = new Tweet();
                tweet.setId(rs.getInt("id"));
                tweet.setPersonId(rs.getInt("person_id"));
                tweet.setContent(rs.getString("content"));
                return tweet;
            } } );
		}
		
		if(searchParam != null)
		{
			List<String> str = new ArrayList<String>();
			str.add("%"+searchParam+"%");
			namedParameters.put("searchVal", str);
			tweetList = namedParameterJdbcTemplate.query( "select id, person_id, content from tweet where person_id IN ( :listOfUser ) and content like :searchVal ", namedParameters,
					new RowMapper<Tweet>() {
		            public Tweet mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Tweet tweet = new Tweet();
		                tweet.setId(rs.getInt("id"));
		                tweet.setPersonId(rs.getInt("person_id"));
		                tweet.setContent(rs.getString("content"));
		                return tweet;
		            } } );
		}
		   
		return tweetList;
	}
}
