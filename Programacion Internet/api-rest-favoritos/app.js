'use strict' //Llama a la ultima version de javascript

//cargar un import de un paquete o un modulo en concreto
const express = require('express'); //cargando express
const bodyParser = require('body-parser'); //cargando body-parser
var path = require("path");
const app = express();
const api = require('./routes/favorito');

app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());
app.use('/api',api);

module.exports = app;


/*
app.get('/sumar/:n1/:n2',(req,res)=>{
	
	const number1 = parseFloat(req.params.n1);
	const number2 = parseFloat(req.params.n2);

	var result = number1 + number2;

	res.send("La suma es: " + result);

	console.log("Peticion realizada!");
})

app.get('/restar/:n1/:n2',(req,res)=>{
	
	const number1 = parseFloat(req.params.n1);
	const number2 = parseFloat(req.params.n2);

	var result = number1 - number2;

	res.send("La resta es: " + result);

	console.log("Peticion realizada!");
})

app.get('/multiplicar/:n1/:n2',(req,res)=>{
	
	const number1 = parseFloat(req.params.n1);
	const number2 = parseFloat(req.params.n2);

	var result = number1 * number2;

	res.send("La multiplicacion es: " + result);

	console.log("Peticion realizada!");
})

app.get('/dividir/:n1/:n2',(req,res)=>{
	
	const number1 = parseFloat(req.params.n1);
	const number2 = parseFloat(req.params.n2);

	var result = number1 / number2;

	res.send("La division es: " + result);

	console.log("Peticion realizada!");
})

app.get('/mostrarhtml',(req,res)=>{
	res.sendFile(path.join(__dirname+'/views/prueba.html'));
})
*/


