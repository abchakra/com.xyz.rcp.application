package in.co.vertex.feedback.j2p;

import in.co.vertex.feedback.model.Batch;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Criteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetBatchsAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Batch> batchList;

	public String execute() throws Exception {

		org.hibernate.classic.Session hibsession = HibernateUtil
				.getSessionFactory().openSession();
		Criteria crit = hibsession.createCriteria(Batch.class);
		ArrayList<Batch> batchList = (ArrayList<Batch>) crit.list();

		setBatchList(batchList);
		// Map session = ActionContext.getContext().getSession();
		
		return SUCCESS;
	}

	public ArrayList<Batch> getBatchList() {
		return batchList;
	}

	public void setBatchList(ArrayList<Batch> batchList) {
		this.batchList = batchList;
	}

	public String logout() throws Exception {

		Map session = ActionContext.getContext().getSession();
		session.remove("logged-in");
		return SUCCESS;
	}

}