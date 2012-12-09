package in.co.vertex.feedback.j2p;

import in.co.vertex.feedback.model.Answer;
import in.co.vertex.feedback.model.Batch;
import in.co.vertex.feedback.model.Form;
import in.co.vertex.feedback.model.Option;
import in.co.vertex.feedback.model.Question;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.ParameterAware;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.TableOrder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetBatchReportAction extends ActionSupport implements
		ParameterAware {
	private Map parameters;
	String[][] data = null;

	ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
	ArrayList<String> headerStr = null;

	public ArrayList<String> getHeaderStr() {
		return headerStr;
	}

	public ArrayList<ArrayList<String>> getDatas() {
		return datas;
	}

	public void setHeaderStr(ArrayList<String> headerStr) {
		this.headerStr = headerStr;
	}

	private ArrayList<Question> questionSet;

	public ArrayList<Question> getQuestionSet() {
		return questionSet;
	}

	public void setQuestionSet(ArrayList<Question> questionSet) {
		this.questionSet = questionSet;
	}

	private void createData() {
		// get parameter value
		String batchId = getParameterValue("batchId");

		org.hibernate.classic.Session hibsession = HibernateUtil
				.getSessionFactory().openSession();
		Criteria crit = hibsession.createCriteria(Batch.class);
		crit.add(Restrictions.eq("batchId", Integer.valueOf(batchId)));
		ArrayList<Batch> batchList = (ArrayList<Batch>) crit.list();

		Batch batchobj = (Batch) batchList.get(0);

		Form formobj = batchobj.getFormId();

		Set<Question> questionset = (Set<Question>) formobj.getQuestions();

		data = new String[questionset.size()][5];

		int iCounter = 0;

		for (Question question : questionset) {
			int jCounter = 0;
			data[iCounter][jCounter++] = question.getLabel();
			Set<Option> optionSet = (Set<Option>) question.getOptions();
			if (headerStr == null) {
				headerStr = new ArrayList<String>();
				headerStr.add("Questions");
			}
			ArrayList<String> optionArr = new ArrayList<String>();
			optionArr.add(question.getLabel());
			for (Option option : optionSet) {
				if (headerStr.size() < 5) {
					headerStr.add(option.getLabel());
				}
				crit = hibsession.createCriteria(Answer.class);
				crit.add(Restrictions.eq("batchId", batchobj));
				crit.add(Restrictions.eq("formId", formobj));
				crit.add(Restrictions.eq("questionId", question));
				crit.add(Restrictions.eq("optionId", option));

				ArrayList<Answer> answerList = (ArrayList<Answer>) crit.list();

				// data[iCounter][jCounter++] = answerList.size() + "";

				optionArr.add(answerList.size() + "");
			}
			datas.add(optionArr);
			iCounter++;
		}

		// System.out.println("asdf");

	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return A sample dataset.
	 */
	private CategoryDataset createDataset() {
		// get parameter value
		String batchId = getParameterValue("batchId");

		org.hibernate.classic.Session hibsession = HibernateUtil
				.getSessionFactory().openSession();
		Criteria crit = hibsession.createCriteria(Batch.class);
		crit.add(Restrictions.eq("batchId", Integer.valueOf(batchId)));
		ArrayList<Batch> batchList = (ArrayList<Batch>) crit.list();

		Batch batchobj = (Batch) batchList.get(0);

		Form formobj = batchobj.getFormId();

		Set<Question> questionset = (Set<Question>) formobj.getQuestions();

		double[][] data = new double[questionset.size()][4];

		int iCounter = 0;
		int jCounter = 0;
		for (Question question : questionset) {
			Set<Option> optionSet = (Set<Option>) question.getOptions();
			jCounter = 0;
			for (Option option : optionSet) {
				crit = hibsession.createCriteria(Answer.class);
				crit.add(Restrictions.eq("batchId", batchobj));
				crit.add(Restrictions.eq("formId", formobj));
				crit.add(Restrictions.eq("questionId", question));
				crit.add(Restrictions.eq("optionId", option));

				ArrayList<Answer> answerList = (ArrayList<Answer>) crit.list();

				data[iCounter][jCounter++] = Double.valueOf(answerList.size());
			}
			iCounter++;

		}

		final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				"Question ", "option", data);
		return dataset;
	}

	public String execute() throws Exception {
		// final CategoryDataset dataset = createDataset();
		createData();
		Map session = ActionContext.getContext().getSession();
		// session.put("dataset", dataset);

		return SUCCESS;
	}

	public String getParameterValue(String param) {
		Object varr = getParameters().get(param);
		if (varr == null)
			return null;
		return ((String[]) varr)[0];
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
}
