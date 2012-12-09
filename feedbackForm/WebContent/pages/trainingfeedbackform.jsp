<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Training Feedback form</title>
</head>
<body>

<s:form action="j2pformsubmit" method="POST">
	<table border="0" width=700 cellpadding="0" cellspacing="0">
		<tr>
			<th colspan="5" bgcolor="#7FE897">Training Feedback form</th>
		</tr>
		<tr>
			<td colspan="1">Student's Name:<s:property value="userName" /></td>
			<td colspan="4" align="right">Date:<b><s:property
				value="currentTime" /></b></td>
		</tr>


		<tr>
			<th colspan="5" align="right">Please pick appropriate choice</th>
		</tr>
		<tr>
			<th colspan="5" bgcolor="#F6F631">Questions</th>
			<s:iterator value="questionList" id="question"
				status="questionStatus">
				<tr bgcolor="#3C74B5">
					<td colspan="1"><font color="#FFFFFF"><s:property
						value="questionId" /></font></td>
					<td colspan="4"><font color="#FFFFFF"><s:property
						value="label" /></font></td>
				</tr>





				<s:set var="OptionsSet" value="#question.options" />


				<tr>
					<td colspan="5"><s:radio list="OptionsSet"
						name="optionNames[%{#questionStatus.index}]" label=""
						listValue="label" listKey="optionId" /></td>
				</tr>

				<!--
				
				<s:iterator value="OptionsSet" id="option" status="optionStatus">
				
				<td><s:property value="label" /></td>

			</s:iterator>
	-->
				<tr>
					<td colspan="1">Free comments:</td>
					<td colspan="4"><s:textarea
						name="comments[%{#questionStatus.index}]" rows="2" cols="50"></s:textarea></td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</s:iterator>
		</tr>
		<tr>
			<th colspan="5">Any comments and remarks you wish to give us:
			(Feel free to write in Japanese)</th>

		</tr>

		<tr>
		</tr>
		<tr>
			<s:submit value="Submit" align="center" />
		</tr>


	</table>
</s:form>

</body>
</html>
