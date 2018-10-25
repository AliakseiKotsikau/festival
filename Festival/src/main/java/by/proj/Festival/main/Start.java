package by.proj.Festival.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import by.proj.Festival.events.Festival;

public class Start {

	public static void main(String[] args) {

		Session session = null;
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();

		session.beginTransaction();

		Festival obj = new Festival();
		obj.setName("Beer Festival");
		obj.setDate(new Date());
		obj.setPlace("Minsk, DreamLand");
		obj.setSeating(1000);

		session.save(obj);
		session.getTransaction().commit();
		session.close();

	}

}
