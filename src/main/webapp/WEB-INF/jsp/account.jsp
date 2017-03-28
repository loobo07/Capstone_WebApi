<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Account Summary:</h3>
	</div>
	
	<div class="panel-body">

		<table class="table table-striped" id="account-table">
			<thead>
				<tr>
					<th>accountNumber</th>
					<th>balance</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td>${account.accountNumber}</td>
					<td>${account.balance}</td>
				</tr>

			</tbody>
		</table>

	</div>
	<div class="panel-heading">
		<h4 class="panel-title">Transaction</h4>
	</div>
	<br/>
	<div class="panel-body">
		<form:form action="${pageContext.request.contextPath}/account/withdraw" class="form" modelAttribute="accountForm" role="form">
			
			<div class="form-group">
				<form:label path="balance">Withdraw</form:label>
				<form:input path="balance" class="form-control" />
				<form:errors cssClass="error" path="balance"></form:errors>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Withdraw</button>
			</div>
			
		</form:form>
		
	</div>
	
</div>

<%@include file="include/footer.jsp" %>
