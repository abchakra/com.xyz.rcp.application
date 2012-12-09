<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Reports</title>
</head>
<body>

<table border="1" width=700 cellpadding="0" cellspacing="0">
	<tr>
		<s:iterator value="headerStr" id="bean" status="headStatus">
			<td><s:property value=" headerStr[#headStatus.index]" /></td>
		</s:iterator>

	</tr>


	<s:iterator value="productList" status="a">
		<s:iterator value="productList[#a.index]">
			<s:property value="id" />
			<s:property value="title" />
			<s:property value="description" />
			<s:property value="picture_url" />
			<s:property value="price" />
		</s:iterator>
	</s:iterator>



	<s:iterator value="datas" id="databean" status="dStatus">
		<tr>

			<s:property value="datas[#dStatus.index]" />
			<s:iterator value="datas[#dStatus.index]" id="itembean"
				status="istatus">
				<td><s:property value=" itembean[#istatus.index]" /></td>
			</s:iterator>
		</tr>
	</s:iterator>






</table>






</body>
</html>