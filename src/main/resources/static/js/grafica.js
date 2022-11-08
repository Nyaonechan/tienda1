let datosControlador;
//primera grafica
const $categorias = document.querySelector("#categorias");
let etiquetas = new Array();
let cantidades = new Array();
// segunda grafica
// Obtener una referencia al elemento canvas del DOM
const $productos = document.querySelector("#productos"); 
let etiquetas2 = new Array();
let valoracion = new Array();

$(function() { // Cuando la página se haya cargado.

	$.ajax({
		url: '/pruebaJSON',
		type: 'GET',
		success: function(data) {
			// Recorremos los arrays devueltos del controlador.
			data.categorias.forEach(function(element) {
				etiquetas.push(element.nombre); // Añadirmos las categorias a la variable etiquetas.
			},
			data.cantidades.forEach(function(element) {
				cantidades.push(element); // Añadirmos las categorias a la variable etiquetas.
			},
			data.productos.forEach(function(element) {
				etiquetas2.push(element); // Añadirmos las categorias a la variable etiquetas.
			},
			data.cantidades2.forEach(function(element) {
			valoracion.push(element); // Añadirmos las categorias a la variable etiquetas.
			}
			))));

			// Podemos tener varios conjuntos de datos. Comencemos con uno
			const datosCategorias = {
				label: "Categorás más vendidas",
				data: cantidades, // La data es un arreglo que debe tener la misma cantidad de valores que la cantidad de etiquetas
				backgroundColor: 'rgba(54, 162, 235, 0.2)', // Color de fondo
				borderColor: 'rgba(54, 162, 235, 1)', // Color del borde
				borderWidth: 1,// Ancho del borde
			};
			const datosProductos = {
			    label: "Productos más valorados",
			    data: valoracion, // La data es un arreglo que debe tener la misma cantidad de valores que la cantidad de etiquetas
			    backgroundColor: 'rgba(54, 162, 235, 0.2)', // Color de fondo
			    borderColor: 'rgba(54, 162, 235, 1)', // Color del borde
			    borderWidth: 1,// Ancho del borde
			};
			new Chart($categorias, { // primera grafica
				type: 'bar',// Tipo de gráfica
				data: {
					labels: etiquetas,
					datasets: [
						datosCategorias,
					]
				},
				options: {
					scales: {
						yAxes: [{
							ticks: {
								beginAtZero: true
							}
						}],
					},
				}
			});
			
			new Chart($productos, { // segunda grafica
			    type: 'bar',// Tipo de gráfica
			    data: {
			        labels: etiquetas2,
			        datasets: [
			            datosProductos,
			        ]
			    },
			    options: {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    beginAtZero: true
			                }
			            }],
			        },
			    }
			});

		}
	});

});





