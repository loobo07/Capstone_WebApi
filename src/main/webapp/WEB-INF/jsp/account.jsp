<%@include file="include/header.jsp" %>

<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">Account:</h3>
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
	
</div>

<%@include file="include/footer.jsp" %>
