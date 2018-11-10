package by.courses;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import by.courses.model.Login;
import by.courses.model.Participant;
import by.courses.model.Role;

public class HibernateCheck {

	public static void main(String[] args) {

		Session session = null;
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();

		session.beginTransaction();

		Participant part = new Participant();
		part.setFirstName("Ivan");
		part.setLastName("Ivanov");
		part.setAge(20);
		part.setEmail("ivanivan@mail.ru");
		part.setPhone("+375291234567");

		Login login = new Login();
		login.setUsername("user");
		login.setPassword("password");

		Role role = new Role();
		role.setRole("ROLE_USER");

		login.getRoles().add(role);
		role.getLogins().add(login);

		session.save(role);
		session.save(login);
		session.save(part);
		session.getTransaction().commit();
		System.out.println("Done!");
		session.close();

	}

}
