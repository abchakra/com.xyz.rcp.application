package in.co.vertex.feedback.j2p;

import in.co.vertex.feedback.model.Form;
import in.co.vertex.feedback.model.Option;
import in.co.vertex.feedback.model.Question;

import java.util.HashSet;

import org.hibernate.HibernateException;

public class QuestionFeeder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		QuestionFeeder qf = new QuestionFeeder();
		qf.saveQuestion();

	}

	public void saveQuestion() {
		org.hibernate.classic.Session session = HibernateUtil
				.getSessionFactory().openSession();
		try {

			session.beginTransaction();

			HashSet<Form> formSet = new HashSet<Form>();
			Form form = new Form("J2PForm");
			formSet.add(form);
			// HashSet<Question> questSet = new HashSet<Question>();
			HashSet<Option> optionSet = new HashSet<Option>();
			Option o1 = new Option();
			o1.setLabel("Good");

			Option o2 = new Option();
			o2.setLabel("Satisfactory");

			Option o3 = new Option();
			o3.setLabel("Unsatisfactory");

			Option o4 = new Option();
			o4.setLabel("Not applicable");

			optionSet.add(o1);
			optionSet.add(o2);
			optionSet.add(o3);
			optionSet.add(o4);

			Question q1 = new Question(
					"The proficiency of the faculty for this module.",
					optionSet, formSet);
			Question q2 = new Question(
					"Faculty’s communication / Presentation skills.",
					optionSet, formSet);
			Question q3 = new Question(
					"Faculty’s responsiveness and interaction with the students",
					optionSet, formSet);
			Question q4 = new Question(
					"How is the depth and Quality of the contents of training?  ",
					optionSet, formSet);
			Question q5 = new Question(
					"What was the quality of the lecture material and teaching aids used?",
					optionSet, formSet);
			Question q6 = new Question("Home work /Assignment", optionSet,
					formSet);
			Question q7 = new Question(
					"Overall rating including Faculty, study material, lecture / session",
					optionSet, formSet);
			Question q8 = new Question(
					"How you will use what you have learnt?", optionSet,
					formSet);

			session.save(q1);
			session.save(q2);
			session.save(q3);
			session.save(q4);
			session.save(q5);
			session.save(q6);
			session.save(q7);
			session.save(q8);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}
