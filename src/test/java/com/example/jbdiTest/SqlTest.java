package com.example.jbdiTest;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.Test;

public class SqlTest {
	
	@Test
	public void sqlSelectNames() {		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://127.0.0.1:3306/testdb?useSSL=false", "root", "password"); 
		jdbi.installPlugin(new SqlObjectPlugin());
		
		List<String> names = jdbi.withHandle(handle ->
	    handle.createQuery("select name from contacts")
	          .mapTo(String.class)
	          .list());
		
		assertThat(names).contains("ペルソナ！");
	}

	@Test
	public void sqlDropName() {		
		Jdbi jdbi = Jdbi.create("jdbc:mysql://127.0.0.1:3306/testdb?useSSL=false", "root", "password"); 
		jdbi.installPlugin(new SqlObjectPlugin());
		
		jdbi.useHandle(handle -> { 
			handle.execute("delete from contacts where name = '雨宮蓮'");});
	}
	
	@Test
	public void sqlInsert() {
		Jdbi jdbi = Jdbi.create("jdbc:mysql://127.0.0.1:3306/testdb?useSSL=false", "root", "password"); 
		jdbi.installPlugin(new SqlObjectPlugin());
		
		jdbi.useHandle(handle -> { 
			handle.execute("insert into contacts (id, name) values (?, ?)", 3, "鳴上悠");});
	}
	
	
}
