package com.seleniumexpress.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.seleniumexpress.dto.SignupDTO;

@Repository
public class SignupDAOImpl implements SignupDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveUser(SignupDTO signupDTO) {
		String sql = "insert into users values(?,?,?)";
		String sql2 = "insert into authorities values(?,?)";
		
		// jdbcTemplate.update(sql,
		// signupDTO.getUsername(),signupDTO.getPassword(),1);
		// jdbcTemplate.update(sql2, signupDTO.getUsername(),"USER");
		  //or
		/*jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, signupDTO.getUsername());
				preparedStatement.setString(2, signupDTO.getPassword());
				preparedStatement.setInt(3, 1);
			}
		});*/
		//into lambda expression
		jdbcTemplate.update(sql,ps->{
			
			ps.setString(1, signupDTO.getUsername());
			ps.setString(2, signupDTO.getPassword());	
			ps.setInt(3, 1);
		});
		
		/*jdbcTemplate.update(sql2, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pStatement) throws SQLException {
			    
				pStatement.setString(1, signupDTO.getUsername());
				pStatement.setString(2, "Coder");
				
			}
		});*/
		// into lambda expression
		jdbcTemplate.update(sql2, pStatement->{
			
			pStatement.setString(1, signupDTO.getUsername());
			pStatement.setString(2, "Coder");
			
		});

	}

}
