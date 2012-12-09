package in.co.vertex.feedback.j2p;

import in.co.vertex.feedback.model.Batch;
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

public class J2PFeedback extends ActionSupport {

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
		crit.add(Restrictions.eq("formId", Integer.valueOf("1")));
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
		Map session = ActionContext.getContext().getSession();
		User userObj = (User) session.get("userobj");

		Batch batch = getBatchforUser(userObj);

		if (batch == null) {
			return ERROR;
		}
		getValues();
		session.put("batchobj", batch);
		session.put("questionMap", getQuestionList());

		setUserName(userObj.getFullname());

		return SUCCESS;
	}

	private Batch getBatchforUser(User userObj) {
		org.hibernate.classic.Session session = HibernateUtil
				.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Batch.class);
			ArrayList<Batch> batchList = (ArrayList<Batch>) criteria.list();

			for (Batch batch : batchList) {
				Set<User> userlist = (Set<User>) batch.getUsers();

				for (User user : userlist) {
					System.out.println(user.getUserId());
					if (user.getUserId().equals(userObj.getUserId())) {
						Date currentDate = new Date();

						if (currentDate.after(batch.getEndDate())
								|| currentDate.before(batch.getStartDate())) {

						} else {
							return batch;
						}
						// return the batch that matches with the user and time
						// period
					}

				}
			}

			return null;
		} catch (HibernateException e) {
			session.getTransaction().rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
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