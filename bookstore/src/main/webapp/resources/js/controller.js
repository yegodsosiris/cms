$(function() {

	Controller = function() {
		var self = this;

		self.newBook = ko.observable(new BookViewModel());
		self.books = ko.observableArray([]);
		self.authorSearch = ko.observable('');
		self.titleSearch = ko.observable('');
		self.selectedBook = ko.observable();
		self.loggedInUser = new UserViewModel();

		self.populateBooks = function(allData) {
			var allBooks = $.map(allData, function(item) {
				return new BookViewModel(item);
			});
			self.books(allBooks);
		};

		self.saveBook = function(book) {
			if (book.id) {
				book.saveBook(self.loggedInUser);
			} else {
				self.addBook();
			}
			self.unselectBook();
		};

		self.addBook = function() {
			var b = new BookViewModel();
			b.title(self.newBook().title());
			b.author(self.newBook().author());
			b.content = self.newBook().content;
			b.saveBook(self.loggedInUser);
			self.books.push(b);
			self.newBook(new BookViewModel());
		};

		self.getBooksByAuthor = function() {
			$.ajax(contextpath + "/rest/books/author/" + self.authorSearch(), {
				async : false,
				type : "get",
				contentType : "application/json",
				success : function(result) {
					self.populateBooks(result);
				},
				error : function(msg) {
					displayError(msg);
				},
				headers : {
					"Authorization" : basicAuth(self.loggedInUser)
				}
			});
		};

		self.getBooksByTitle = function() {
			$.ajax(contextpath + "/rest/books/title/" + self.titleSearch(), {
				async : false,
				type : "get",
				contentType : "application/json",
				success : function(result) {
					self.populateBooks(result);
				},
				error : function(msg) {
					displayError(msg);
				},
				headers : {
					"Authorization" : basicAuth(self.loggedInUser)
				}
			});
		};

		self.resetSearch = function() {
			$.getJSON(contextpath + "/rest/books", function(allData) {
				self.populateBooks(allData);
			});
			self.authorSearch('');
			self.titleSearch('');
		};

		self.deleteBook = function(book) {
			$.ajax(contextpath + "/rest/book/" + book.id, {
				async : false,
				type : "delete",
				contentType : "application/json",
				success : function(result) {
					self.books.remove(book);
				},
				error : function(msg) {
					displayError(msg);
				},
				headers : {
					"Authorization" : basicAuth(self.loggedInUser)
				}
			});

		};

		self.selectBook = function(book) {
			self.selectedBook(book);
		};

		self.unselectBook = function() {
			self.selectedBook().mode = 'view';
			self.selectedBook(null);
		};

		self.goToAddBook = function() {
			self.newBook().mode = 'edit';
			self.selectedBook(self.newBook());
		};

		self.editBook = function(book) {
			book.mode = 'edit';
			self.selectBook(book);
		};

		self.logout = function() {
			self.loggedInUser = new UserViewModel();
			return true;
		};
		self.saveUser = function() {
			self.loggedInUser.save(function() {
				document.location = contextpath;
			});
		};

		if (self.loggedInUser.id) {
			$.ajax({
				dataType : "json",
				url : contextpath + "/rest/books",
				success : function(allData) {
					self.populateBooks(allData);
				},
				error : function(msg) {
					displayError(msg);
				}
			});
		}
	};

	ko.applyBindings(new Controller());
});