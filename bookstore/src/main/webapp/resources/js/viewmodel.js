$(function() {

	BookViewModel = function(data) {
		var self = this;
		self.id;
		self.title = ko.observable('');
		self.author = ko.observable('');
		self.content = '';
		self.deleting = ko.observable(false);
		self.mode = 'view';

		if (data) {
			self.id = data.id;
			self.title = ko.observable(data.title);
			self.author = ko.observable(data.author);
			self.content = data.content;
		}

		self.saveBook = function(loggedInUser) {
			$.ajax(contextpath + "/rest/book", {
				data : ko.toJSON(new BookDTO(self)),
				async : false,
				type : "post",
				contentType : "application/json",
				success : function(result) {
					self.id = result.id;
				},
				error : function(msg) {
					displayError(msg);
				},
				headers : {
					"Authorization" : basicAuth(loggedInUser)
				}
			});
		};

		self.setDeleting = function() {
			self.deleting(true);
		};

		self.unsetDeleting = function() {
			self.deleting(false);
		};

	};

	BookDTO = function(bookModel) {
		var self = this;
		self.id = bookModel.id;
		self.title = bookModel.title;
		self.author = bookModel.author;
		self.content = bookModel.content;
	};

});
