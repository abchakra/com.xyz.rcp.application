package in.co.vertex.feedback.j2p;

import in.co.vertex.feedback.model.Form;
import in.co.vertex.feedback.model.Question;
import in.co.vertex.feedback.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TrainingFeedback extends ActionSupport {

	ArrayList<Question> questionList = new ArrayList<Question>();

	public ArrayList<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList<Question> questionList) {
		this.questionList = questionList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void getValues() {
		org.hibernate.classic.Session session = HibernateUtil
				.getSessionFactory().openSession();
		// try {
		Criteria crit = session.createCriteria(Form.class);
		crit.add(Restrictions.eq("formId", Integer.valueOf("2")));
		ArrayList<Form> formList = (ArrayList<Form>) crit.list();
		Set<Question> quesList = (Set<Question>) formList.get(0).getQuestions();

		// for (Question question : quesList) {
		// question.getOptions();
		// }
		// setQuestionList(quesList);

		// Iterator<Question>= (Iterator<Question>)

		for (Iterator iterator = quesList.iterator(); iterator.hasNext();) {
			Question question = (Question) iterator.next();
			questionList.add(question);
		}
		System.out.println("");

		// } finally {
		// session.close();
		//
		// }
	}

	public String execute() throws Exception {
		// persistUserObj();
		// storeUserInSession();
		getValues();
		setUserName("vertex");

		Map session = ActionContext.getContext().getSession();
		session.put("questionMap", getQuestionList());

		return SUCCESS;
	}

	private void storeUserInSession() {
		org.hibernate.classic.Session session = HibernateUtil
				.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("userId", Integer.valueOf("1")));
			ArrayList<User> userList = (ArrayList<User>) criteria.list();

			User userObj1 = userList.get(0);

			Map sessionMap = ActionContext.getContext().getSession();
			sessionMap.put("userobj", userObj1);
			// session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void persistUserObj() {

		org.hibernate.classic.Session session = HibernateUtil
				.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			User userObj = new User("ac26592", "pass1", "user1");

			session.save(userObj);

			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String userName;

	public String getCurrentTime() {
		return new Date().toString();
	}
}