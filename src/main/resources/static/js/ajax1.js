let datosControlador;

$(function() { // Cuando la página se haya cargado.

	$.ajax({
		url: '/pruebaJSON',
		type: 'GET',
		success: function(data) {
			console.log(data);
			datosControlador = data;
		}
	});
	
	console.log('Página cargada')

});

