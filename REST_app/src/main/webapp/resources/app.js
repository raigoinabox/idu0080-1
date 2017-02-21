var current_book_id;

function get_books() {
	$.ajaxSetup({
		cache : false
	});
	$.ajax({
		url : 'service/books',
		type : "GET",
		dataType : 'json',
		success : function(data) {
			display_books(data);
			console.log(JSON.stringify(data));
		}
	});
}

function display_books(data) {
	var out_data = "";
	out_data = out_data
			+ "<table bgcolor=eeeeee><tr><td colspan=4>Raamatuid kokku: <b>"
			+ data.length + "</b></td></tr>";
	for ( var i in data) {
		var book = data[i];
		out_data = out_data + "<tr><td>autor:</td><td bgcolor=ffffff>"
				+ book.author + "</td><td>pealkiri:</td><td bgcolor=ffffff>"
				+ book.title + "</td>";
		out_data = out_data
				+ "<td><button type='button' class='btn'  onClick='javascript:get_book("
				+ book.id + ")'>Vali</button></td>";
		var delete_button = document.createElement("button");
		delete_button.setAttribute("type", "button");
		delete_button.setAttribute("class", "btn");
		delete_button.setAttribute("onClick", "javascript:delete_book(" + book.id + ")");
		delete_button.appendChild(document.createTextNode("Kustuta"));
		var delete_cell = document.createElement("td");
		delete_cell.appendChild(delete_button);
		out_data = out_data + delete_cell.outerHTML;
	}
	out_data = out_data + "</table>";

	$("#books_table").html(out_data);
}

function get_book(id) {
	$.ajaxSetup({
		cache : false
	});
	$.ajax({
		url : 'service/book/' + id,
		type : "GET",
		dataType : 'json',
		success : function(data) {
			current_book_id = data.id;
			display_book(data);
			console.log(JSON.stringify(data));
		}
	});
}

function display_book(book) {
	var out_data = "";
	out_data = out_data
			+ "<table bgcolor=eeeeee><tr><td>Muutmine. Raamatu id: <b>"
			+ book.id + "</b></td></tr>";

	out_data = out_data
			+ "<tr><td>Pealkiri:</td><td><input type=text name=title value='"
			+ book.title + "'></td></tr>";
	out_data = out_data
			+ "<tr><td>Autor:</td><td><input type=text name=author value='"
			+ book.author + "'></td></tr>";
	out_data = out_data
			+ "<tr><td>Aasta:</td><td><input type=text name=year value='"
			+ book.year + "'></td></tr>";
	out_data = out_data
			+ "<tr><td>Kirjastus:</td><td><input type=text name=publisher value='"
			+ book.publisher + "'></td></tr>";
	out_data = out_data
			+ "<td><button type='button' class='btn'  onClick='javascript:save_book()'>Salvesta</button></td>";
	out_data = out_data + "</table>";

	$("#one_book").html(out_data);
}

function save_book() {
	var book = {
			"id": current_book_id,
			"title": document.forms[0].title.value,
			"author": document.forms[0].author.value,
			"year": document.forms[0].year.value,
			"publisher": document.forms[0].publisher.value
	};
	
	var jsonData = JSON.stringify(book);
	$.ajaxSetup({
		cache : false
	});
	$.ajax({
		url : 'service/book/' + book.id,
		type : "POST",
		data : jsonData,
		dataType : 'json',
		contentType : 'application/json',
		success : function(data) {
			show_message("Salvestatud");
			console.log(JSON.stringify(data));
		}
	});
}

function save_new_book() {
	var form = document.getElementById("new_book_form");
	var book = {
			"title": form.elements.title.value,
			"author": form.elements.author.value,
			"year": form.elements.year.value,
			"publisher": form.elements.publisher.value
	};
	
	var jsonData = JSON.stringify(book);
	$.ajax({
		url : 'service/book/create',
		type : "PUT",
		data : jsonData,
		dataType : 'json',
		contentType : 'application/json',
		success : function() {
			show_message("Loodud");
			get_books();
		}
	});
}

function delete_book(book_id) {
	$.ajax({
		url: "service/book/" + book_id + "/delete",
		type: "DELETE",
		success: function() {
			show_message("Kustutatud");
			get_books();
		}
	});
}

function show_message(message) {
	$("#msg_text").html(message);
}
