package com.cl.example.entity;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
				configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
				configuration.setProperty("hibernate.connection.url",
						"jdbc:postgresql://localhost:5432/StudentDatabase");
				configuration.setProperty("hibernate.connection.username", "postgres");
				configuration.setProperty("hibernate.connection.password", "crimson@123");
				configuration.setProperty("hibernate.show_sql", "true");
				configuration.setProperty("hibernate.format_sql", "true");

				configuration.addAnnotatedClass(StudentInfo.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
