$(function() {
	HippoViewModel = function() {
		var self = this;
		self.products = ko.observableArray([]);
		self.selectedProduct = ko.observable();
		self.getSelectedProduct = function(product) {
			console.log(ko.toJSON(product));
			$.ajax(contextpath + "/rest/product/", {
				async : false,
				type : "post",
				data : ko.toJSON(product),
				contentType : "application/json",
				success : function(result) {
					self.selectedProduct(new HippoProduct(result));
				},
				error : function(msg) {
					displayError(msg);
				}
			});
		};
		$.getJSON(contextpath + "/rest/products", function(allData) {
			console.log(ko.toJSON(allData));
			var allProducts = $.map(allData, function(item) {
				return new HippoProduct(item);
			});
			self.products(allProducts);
		});
	};

	HippoProduct = function(data) {
		var self = this;
		self.title = data.title;
		self.name = data.name;
		self.summary = data.summary;
		self.productLink = data.productLink;
		self.price = data.price;
		self.rating = data.rating;
		self.image = data.image;
		self.smallThumbnail = data.smallThumbnail;
	};

	// ko.applyBindings('hippo', document.getElementById("hippo"));
});