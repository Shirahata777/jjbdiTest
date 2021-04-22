package com.example.jbdiTest.controller;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jbdiTest.repository.DatabaseProperty;

@RestController
public class Controller {
	
	@Autowired
	private DatabaseProperty databaseProperty;
	
	@RequestMapping("/test")
	public void test(){
		String url = databaseProperty.getUrl();
		String user = databaseProperty.getUsername();
		String pass = databaseProperty.getPassword();

		
		Jdbi jdbi = Jdbi.create(url, user, pass); 
		jdbi.installPlugin(new SqlObjectPlugin());
		
		jdbi.useHandle(handle -> { 
			handle.execute("DROP TABLE IF EXISTS contacts");
		    handle.execute("create table contacts (id int primary key, name varchar(100))");
		    handle.execute("insert into contacts (id, name) values (?, ?)", 1, "ペルソナ！");
		    handle.execute("insert into contacts (id, name) values (?, ?)", 2, "雨宮蓮");
		});
		
	}

}
