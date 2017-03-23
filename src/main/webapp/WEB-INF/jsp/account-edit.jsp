<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Account:</h3>
	</div>
	
	<div class="panel-body">
	
		<h4 class="panel-title">Edit Account</h4>
		
		<br/>
		
		<form:form action="${pageContext.request.contextPath}/account/edit" class="form" modelAttribute="accountForm" role="form">
			
			<div class="form-group">
				<form:label path="accountType">Account Type</form:label>
				<form:input path="accountType" class="form-control" />
 				<form:errors cssClass="error" path="accountType"></form:errors>
			</div>
			
			<div class="form-group">
				<form:label path="balance">Balance</form:label>
				<form:input path="balance" class="form-control" />
 				<form:errors cssClass="error" path="balance"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Save</button>
				<button type="reset" class="btn btn-default">Clear</button>
			</div>
			
		</form:form>
		
	</div>
	
</div>

<%@include file="include/footer.jsp" %>
