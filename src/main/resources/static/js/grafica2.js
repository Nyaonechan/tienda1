// Obtener una referencia al elemento canvas del DOM
const $categorias = document.querySelector("#categorias");
// Las etiquetas son las que van en el eje X. 
const etiquetas2 = ["Enero", "Febrero", "Marzo", "Abril"]
// Podemos tener varios conjuntos de datos. Comencemos con uno
const datosProductos = {
    label: "Ventas por mes",
    data: [5000, 1500, 8000, 5102], // La data es un arreglo que debe tener la misma cantidad de valores que la cantidad de etiquetas
    backgroundColor: 'rgba(54, 162, 235, 0.2)', // Color de fondo
    borderColor: 'rgba(54, 162, 235, 1)', // Color del borde
    borderWidth: 1,// Ancho del borde
};
new Chart($categorias, {
    type: 'bar',// Tipo de gráfica
    data: {
        labels: etiquetas2,
        datasets: [
            datosProductos,
            // Aquí más datos...
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

