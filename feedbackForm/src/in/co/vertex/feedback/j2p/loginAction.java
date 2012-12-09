package in.co.vertex.feedback.j2p;

import in.co.vertex.feedback.model.User;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class loginAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String password;

	public String execute() throws Exception {

		org.hibernate.classic.Session hibsession = HibernateUtil
				.getSessionFactory().openSession();
		// try {
		Criteria crit = hibsession.createCriteria(User.class);
		crit.add(Restrictions.eq("userId", userId));
		crit.add(Restrictions.eq("password", password));
		ArrayList<User> formList = (ArrayList<User>) crit.list();

		if (formList.size() > 0) {
			User userobj = (User) formList.get(0);

			Map session = ActionContext.getContext().getSession();
			session.put("logged-in", "true");
			session.put("userobj", userobj);
			return SUCCESS;
		} else {
			return ERROR;
		}

		// if ("admin".equals(userId) && "admin".equals(password)) {
		// Map session = ActionContext.getContext().getSession();
		// session.put("logged-in", "true");
		// return SUCCESS;
		// } else {
		// return ERROR;
		// }
	}

	public String logout() throws Exception {

		Map session = ActionContext.getContext().getSession();
		session.remove("logged-in");
		return SUCCESS;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}