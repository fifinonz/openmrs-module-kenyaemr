<%
	ui.decorateWith("standardAppPage")
%>

<style>
.col2 {
	width: 48%;
	float: left;
}
#end-of-day {
	display: none;
}
#end-of-day .spaced {
	padding: 0.3em;
}
</style>

<div class="col2">
	<h1>Registration App</h1>
	<h3>Welcome!</h3>
	
	${ ui.includeFragment("widget/button", [ icon: "search_32.png", label: "Find a Patient", href: ui.pageLink("registrationSearch") ]) }
</div>

<div class="col2">
	<h2>Checked In Patients</h2>
	${ ui.includeFragment("patientList", [ id: "checkedInPatients", page: "registrationViewPatient" ]) }
	
	<br/>
	<div id="end-of-day">
		<h3>End-of-Day Tasks</h3>
		Close all open visits of the following types:
		<form method="post" action="${ ui.actionLink("registrationUtil", "closeActiveVisits") }">
			<div class="form-data">
			</div>
			<input type="submit" value="Close Visits"/>
		</form>
	</div>
</div>


<script>
	getJsonAsEvent(actionLink('patientSearch', 'withActiveVisits'), 'checkedInPatients/show');
	jq.getJSON(actionLink('registrationUtil', 'activeVisitTypes'), function(result) {
		if (result.length == 0) {
			return;
		}
		var str = "";
		for (var i = 0; i < result.length; ++i) {
			var r = result[i];
			str += '<div class="spaced"><input type="checkbox" name="visitType" value="' + r.visitTypeId + '"/> ' + r.name + ' (' + r.count + ')</div>';
		}
		jq('#end-of-day .form-data').html(str);
		jq('#end-of-day').show();
	});
</script>