$(function() {

	UserViewModel = function(data) {
		var self = this;
		self.id;
		self.username = ko.observable('');
		self.password = ko.observable('');
		self.roles = [];
		self.firstname = ko.observable('');
		self.surname = ko.observable('');
		self.availableRoles = [ 'User', 'Author', 'Admin' ];
		
		$.ajax(contextpath + "/rest/user/current", {
			async : false,
			type : "get",
			contentType : "application/json",
			success : function(data) {
				self.id = data.id;
				self.username(data.username);
				self.password(data.password);
				self.roles = data.roles;
				self.firstname(data.firstname);
				self.surname(data.surname);
			},
			error : function(msg) {
				displayError(msg);
			},
		});

		self.save = function(callback) {
			$.ajax(contextpath + "/rest/user", {
				data : ko.toJSON(new UserDTO(self)),
				async : false,
				type : "put",
				contentType : "application/json",
				success : function(result) {
					if (callback) {
						callback(result);
					}
				},
				error : function(msg) {
					displayError(msg);
				},
				headers : {
					"Authorization" : basicAuth(self)
				}
			});
		};
	};

	UserDTO = function(userViewModel) {
		var self = this;
		self.id = userViewModel.id;
		self.username = userViewModel.username;
		self.password = userViewModel.password;
		self.roles = userViewModel.roles;
		self.firstname = userViewModel.firstname;
		self.surname = userViewModel.surname;
	};

});
