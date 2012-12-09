package in.co.vertex.feedback.j2p;

import in.co.vertex.feedback.model.Answer;
import in.co.vertex.feedback.model.Batch;
import in.co.vertex.feedback.model.Form;
import in.co.vertex.feedback.model.Option;
import in.co.vertex.feedback.model.Question;
import in.co.vertex.feedback.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class J2PFormSubmit extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// List<Boolean> OptionName=new
	public String execute() throws Exception {

		persistAnswerObj();

		return SUCCESS;
	}

	private void persistAnswerObj() {

		org.hibernate.classic.Session session = HibernateUtil
				.getSessionFactory().openSession();
		try {

			session.beginTransaction();
			Map sessionMap = ActionContext.getContext().getSession();
			ArrayList<Question> questionList = (ArrayList<Question>) sessionMap
					.get("questionMap");

			User userObj = (User) sessionMap.get("userobj");

			Batch batchObj = (Batch) sessionMap.get("batchobj");

			HashMap<Integer, Integer> answerArray = new HashMap<Integer, Integer>();
			for (int iCounter = 0; iCounter < optionNames.length; iCounter++) {
				if (optionNames[iCounter] != null) {

					Question quesObj = questionList.get(iCounter);
					Option optionObj = null;
					Form form = quesObj.getForms().iterator().next();
					for (Iterator iterator = quesObj.getOptions().iterator(); iterator
							.hasNext();) {
						Option opObj = (Option) iterator.next();

						if (opObj.getOptionId() == optionNames[iCounter]) {
							optionObj = opObj;
						}

					}
					Answer ansObj = new Answer(comments[iCounter], questionList
							.get(iCounter), optionObj, userObj, form, batchObj);

					if (ansObj != null) {
						session.save(ansObj);
					}
					System.out.println(optionNames[iCounter]);
				}
			}

			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();

			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	Integer[] optionNames = new Integer[100];

	public Integer[] getOptionNames() {
		return optionNames;
	}

	public void setOptionNames(Integer[] optionNames) {
		this.optionNames = optionNames;
	}

	String[] comments = new String[100];

	public String[] getComments() {
		return comments;
	}

	public void setComments(String[] comment) {
		this.comments = comment;
	}

}
