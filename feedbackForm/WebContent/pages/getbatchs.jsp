<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Reports</title>
</head>
<body>


<div align="center">
<center>
<table border="1" cellpadding="0" cellspacing="0" width="400">

	<tr bgcolor="#D4CDCD">
		<td width="20" align="center">Id</td>
		<td width="80" align="center">Batch Name</td>
		<td width="160" align="center">Start Date</td>
		<td width="160" align="center">End Date</td>
	</tr>
	<s:iterator value="batchList" id="batch" status="batchstatus">


		<tr>

			<td width="20" align="center"><s:property value="batchId" /></td>
			<td width="80" align="center"><s:url
				action="showreportforbatch" var="idUrl">
				<s:param name="batchId" value="batchId" />
			</s:url>

			<p><a href="${idUrl}"><s:property value="batchName" /></a></p>


			</td>
			<td width="160" align="center"><s:property value="startDate" /></td>
			<td width="160" align="center"><s:property value="endDate" /></td>
		</tr>
	</s:iterator>

</table>
</center>
</div>







</body>
</html>