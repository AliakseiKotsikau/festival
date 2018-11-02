package by.courses;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import by.courses.model.Festival;
import by.courses.model.Performer;

public class HibernateCheck {

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

		Performer perf = new Performer();
		perf.setName("Beer plant Krinica");

		perf.addFest(obj);
		obj.addPerformer(perf);

		session.save(perf);
		session.save(obj);
		session.getTransaction().commit();
		System.out.println("Done!");
		session.close();

	}

}
