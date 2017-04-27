<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Cloud Status:</h3>
	</div>
	
	<div class="panel-body">

		<table class="table table-striped" id="cloudtest-table">
			<thead>
				<tr>
					<th>Instance ID</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody id="refreshThis">
				<c:forEach items="${cloudtestForm.instanceMap}" var="instanceMap" varStatus="status">
					<tr id = "${instanceMap.key}">
						<td>${instanceMap.key}</td>
						<td>${instanceMap.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<div class="panel-heading">
		<h3 class="panel-title">Cloud Testing:</h3>
	</div>
	
	<div class="panel-body">
		<form:form action="${pageContext.request.contextPath}/cloudtest/start" class="form" modelAttribute="cloudtestForm" method="post" role="form">
			
			<div class="form-group">
				<form:label path="instanceID">Start instance</form:label>
				<form:input path="instanceID" class="form-control" placeholder="Enter instance id" />
 				<form:errors cssClass="error" path="instanceID"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Start</button>
			</div>
			
		</form:form>
		<br>
		<form:form action="${pageContext.request.contextPath}/cloudtest/stop" class="form" modelAttribute="cloudtestForm" method="post" role="form">
			
			<div class="form-group">
				<form:label path="instanceID">Stop instance</form:label>
				<form:input path="instanceID" class="form-control" placeholder="Enter instance id"/>
				<form:errors cssClass="error" path="instanceID"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Stop</button>
			</div>
			
		</form:form>
	</div>
	
</div>
<script type="text/javascript" src="/js/polling.js"></script>
<%@include file="include/footer.jsp" %>
