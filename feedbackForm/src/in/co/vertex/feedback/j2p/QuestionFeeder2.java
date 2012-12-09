package in.co.vertex.feedback.j2p;

import in.co.vertex.feedback.model.Batch;
import in.co.vertex.feedback.model.Form;
import in.co.vertex.feedback.model.Option;
import in.co.vertex.feedback.model.Question;
import in.co.vertex.feedback.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.hibernate.HibernateException;

public class QuestionFeeder2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		QuestionFeeder2 qf = new QuestionFeeder2();
		qf.saveQuestion();

	}

	public void saveQuestion() {
		org.hibernate.classic.Session session = HibernateUtil
				.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			User userObj1 = new User("ac265921", "pass1", "user1");
			User userObj2 = new User("ac265922", "pass2", "user2");
			User userObj3 = new User("ac265923", "pass3", "user3");
			User userObj4 = new User("ac265924", "pass4", "user4");
			User userObj5 = new User("ac265925", "pass5", "user5");

			HashSet<User> userset1 = new HashSet<User>();

			HashSet<User> userset2 = new HashSet<User>();
			userset1.add(userObj1);
			userset1.add(userObj2);
			userset1.add(userObj3);
			userset1.add(userObj4);
			userset1.add(userObj5);

			userset2.add(userObj2);
			userset2.add(userObj1);
			userset2.add(userObj4);

			HashSet<Form> formSet1 = new HashSet<Form>();

			HashSet<Form> formSet2 = new HashSet<Form>();

			Form form1 = new Form("Training FeedBack Form");
			formSet1.add(form1);
			Form form2 = new Form("J2PForm");
			formSet2.add(form2);

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			// Get Date 1
			Date d1 = null;
			Date d2 = null;
			Date d3 = null;
			try {
				d1 = df.parse("2010-03-26");

				d2 = df.parse("2010-04-12");
				d3 = df.parse("2010-05-12");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Batch batch1 = new Batch("Batch1", d2, d3, userset1, form1);

			Batch batch2 = new Batch("Batch2", d1, d2, userset2, form2);

			// HashSet<Question> questSet = new HashSet<Question>();
			HashSet<Option> optionSet1 = new HashSet<Option>();

			HashSet<Option> optionSet2 = new HashSet<Option>();

			HashSet<Option> optionSet3 = new HashSet<Option>();

			Option o1 = new Option();
			o1.setLabel("Excellent");

			Option o2 = new Option();
			o2.setLabel("Good");

			Option o3 = new Option();
			o3.setLabel("Satisfactory");

			Option o4 = new Option();
			o4.setLabel("Average");

			Option o5 = new Option();
			o5.setLabel("Poor");

			Option o6 = new Option();
			o6.setLabel("Yes");

			Option o7 = new Option();
			o7.setLabel("No");

			Option o8 = new Option();
			o8.setLabel("Unsatisfactory");

			Option o9 = new Option();
			o9.setLabel("Not applicable");

			optionSet1.add(o1);
			optionSet1.add(o2);
			optionSet1.add(o3);
			optionSet1.add(o4);
			optionSet1.add(o5);

			optionSet2.add(o6);
			optionSet2.add(o7);

			optionSet3.add(o2);
			optionSet3.add(o3);
			optionSet3.add(o8);
			optionSet3.add(o9);

			Question q1 = new Question(
					"The proficiency of the faculty for this module.",
					optionSet3, formSet2);
			Question q2 = new Question(
					"Faculty’s communication / Presentation skills.",
					optionSet3, formSet2);
			Question q3 = new Question(
					"Faculty’s responsiveness and interaction with the students",
					optionSet3, formSet2);
			Question q4 = new Question(
					"How is the depth and Quality of the contents of training?  ",
					optionSet3, formSet2);
			Question q5 = new Question(
					"What was the quality of the lecture material and teaching aids used?",
					optionSet3, formSet2);
			Question q6 = new Question("Home work /Assignment", optionSet3,
					formSet2);
			Question q7 = new Question(
					"Overall rating including Faculty, study material, lecture / session",
					optionSet3, formSet2);
			Question q8 = new Question(
					"How you will use what you have learnt?", optionSet3,
					formSet2);

			Question q9 = new Question("Training Content", optionSet1, formSet1);
			Question q10 = new Question("Usefulness of the training",
					optionSet1, formSet1);
			Question q11 = new Question("Practical value to Vertex",
					optionSet1, formSet1);
			Question q12 = new Question("Faculty knowledge", optionSet1,
					formSet1);
			Question q13 = new Question("Standard of Training Material",
					optionSet1, formSet1);
			Question q14 = new Question("Overall training organization",
					optionSet1, formSet1);
			Question q15 = new Question("Interaction with others", optionSet1,
					formSet1);
			Question q16 = new Question("Presentation/Delivery", optionSet1,
					formSet1);
			Question q17 = new Question("Case studies", optionSet1, formSet1);

			Question q18 = new Question(
					"Do you recommend this training for your colleagues?",
					optionSet2, formSet1);

			// Question q11 = new Question(
			// "How do you propose to apply the knowledge/ skills acquired in the course in Vertex?",
			// null, formSet);

			session.save(q1);
			session.save(q2);
			session.save(q3);
			session.save(q4);
			session.save(q5);
			session.save(q6);
			session.save(q7);
			session.save(q8);
			session.save(q9);
			session.save(q10);

			session.save(q11);
			session.save(q12);
			session.save(q13);
			session.save(q14);
			session.save(q15);
			session.save(q16);
			session.save(q17);
			session.save(q18);

			session.save(batch1);
			session.save(batch2);

			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}
