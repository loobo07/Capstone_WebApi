<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Account:</h3>
	</div>
	
	<div class="panel-body">
	
		<h4 class="panel-title">New Record</h4>
		
		<br/>
		
		<form:form action="${pageContext.request.contextPath}/account/create" class="form" modelAttribute="accountForm" role="form">
			
			<div class="form-group">
				<form:label path="accountNumber">accountNumber</form:label>
				<form:input path="accountNumber" class="form-control" />
 				<form:errors cssClass="error" path="accountNumber"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Add</button>
				<button type="reset" class="btn btn-default">Clear</button>
			</div>
			
		</form:form>
		
	</div>
	
	<div class="panel-body">

		<table class="table table-striped" id="account-table">
			<thead>
				<tr>
					<th>accountNumber</th>
					<th>accountType</th>
					<th>balance</th>
				</tr>
			</thead>
			<tbody>

				<c:if test="${not empty accounts}">
					<c:forEach var="account" items="${account}">
						<tr>
							<td>${account.accountNumber}</td>
							<td>${account.accountType}</td>
							<td><a href="/account/edit?accountNumber=${account.accountNumber}">Edit</a> | <a href="" onclick="deleteAccountNumber('account/delete?accountNumber=${account.accountNumber}')">Delete</a></td>
						</tr>
					</c:forEach>
				</c:if>
		
			</tbody>
		</table>

	</div>
	
</div>
<script type="text/javascript">
function go(url)
{
    window.location = url;
}

function deleteAccountNumber(url)
{
    var isOK = confirm("Are you sure to delete?");
    if(isOK)
    {
        go(url);
    }
}
</script>

<%@include file="include/footer.jsp" %>
