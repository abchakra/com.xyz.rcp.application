<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style type="text/css">
img {
	float: right;
	margin: 0 0 15px 20px;
	padding: 15px;
	text-align: center;
}
</style>
</head>
<body>
<s:url action="charts/chart1" id="url" />
<img src="<s:property value="url"/>" />
<p>XML is an open standard for data exchange as well as the ...</p>
</body>
</html>