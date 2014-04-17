$(function() {

	Controller = function() {
		var self = this;
		self.hippoProducts = new HippoViewModel();
		self.loggedInUser = new UserViewModel();

		self.logout = function() {
			self.loggedInUser = new UserViewModel();
			return true;
		};
	};

	ko.applyBindings(new Controller());
});