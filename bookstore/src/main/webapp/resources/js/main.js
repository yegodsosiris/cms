$(function() {

	$('#errorbutton').live('click', function() {
		$('#ajaxerror').hide();
	});

});

displayError = function(msg) {
	$('#errormessage').html(msg.responseText);
	$('#ajaxerror').fadeIn();
};

basicAuth = function(user) {
	var tok = user.username() + ':' + user.password();
	var hash = btoa(tok);
	return "Basic " + hash;
};