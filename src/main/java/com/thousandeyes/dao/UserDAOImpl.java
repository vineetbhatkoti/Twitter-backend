package com.thousandeyes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.thousandeyes.exception.DAOException;
import com.thousandeyes.model.PopularUser;
import com.thousandeyes.model.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<User> getFollowersOfUser(User usr) throws DAOException {
		SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", usr.getId());
		List<User> list = namedParameterJdbcTemplate.query(
		        "select id, name from person where id in (select follower_person_id from followers where person_id = :person_id)",namedParameters,
		        new RowMapper<User>() {
		            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		                User user = new User();
		                user.setId(rs.getInt("id"));
		                user.setName(rs.getString("name"));
		                
		                return user;
		            }
		        });
		return list;
	}

	public List<User> getUserFollows(User user) throws DAOException{
		SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", user.getId());
		List<User> list = namedParameterJdbcTemplate.query(
		        "select id, name from person where id in (select person_id from followers where follower_person_id = :person_id);",namedParameters,
		        new RowMapper<User>() {
		            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		                User user = new User();
		                user.setId(rs.getInt("id"));
		                user.setName(rs.getString("name"));
		                
		                return user;
		            }
		        });
		return list;
		
	}

	public String startFollowing(User firstUser, User secondUser) throws DAOException{
		Map<String, Integer> namedParameters = new HashMap<String, Integer>();
		namedParameters.put("person_id", firstUser.getId());
		namedParameters.put("follower_person_id", secondUser.getId());
		int rowCount = namedParameterJdbcTemplate.update(
		        "insert into followers (person_id, follower_person_id) values (:person_id, :follower_person_id)",
		        namedParameters );
		String message = null;
		if(rowCount == 1)
		{
			message =  "Success !!  UserId: " + secondUser.getId()+" is now following UserId: "+firstUser.getId(); 
		}
		else
		{
			message = "Error while UserId: " + secondUser.getId()+" tried to follow UserId: "+firstUser.getId();
		}
		return message;
	}

	public String unfollow(User firstUser, User secondUser)  throws DAOException{
		Map<String, Integer> namedParameters = new HashMap<String, Integer>();
		namedParameters.put("person_id", firstUser.getId());
		namedParameters.put("follower_person_id", secondUser.getId());
		int rowCount = namedParameterJdbcTemplate.update(
		        "delete from followers where person_id = :person_id and follower_person_id = :follower_person_id",
		        namedParameters);
		String message = null;
		if(rowCount == 1)
		{
			message =  "Success !!  UserId: " + firstUser.getId()+" is unfollowed by UserId: "+secondUser.getId(); 
		}
		else
		{
			message = "Error while UserId: " + secondUser.getId()+" tried to unfollowed by UserId: "+firstUser.getId();
		}
		return message;
	}

	public List<PopularUser> mostPopularFollower() throws DAOException {
		String qry = "select table1.person_id, table1.follower_person_id from (select t1.person_id, t1.follower_person_id , t2.fol from (select a.person_id, a.follower_person_id from followers a order by a.person_id) as t1  right join (select b.person_id as per, count(b.follower_person_id) as fol from followers b group by b.person_id order by b.person_id) as t2 on t1.follower_person_id = t2.per order by t1.person_id, t2.fol desc) as table1  join ( select person_id, max(fol) as fol from (select t1.person_id, t1.follower_person_id , t2.fol from (select a.person_id, a.follower_person_id from followers a order by a.person_id) as t1  right join (select b.person_id as per, count(b.follower_person_id) as fol from followers b group by b.person_id order by b.person_id) as t2 on t1.follower_person_id = t2.per order by t1.person_id, t2.fol desc)   group by person_id ) as table2 on table1.person_id = table2.person_id and table1.fol =table2.fol order by person_id;";
		List<PopularUser> list = namedParameterJdbcTemplate.query(qry,
		        new RowMapper<PopularUser>() {
		            public PopularUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		                PopularUser user = new PopularUser();
		                user.setUserId(rs.getInt("person_id"));
		                user.setMostPopularFollowerId(rs.getInt("follower_person_id"));
		                return user;
		            }
		        });
		return list;
		
		
	}

}
