'use strict'
const Favorito = require('../models/favorito');
var path = require("path");

function saludar(req,res){
	const nombre = req.params.name;
	const apellido = req.params.lastname;
	res.send('Hola '+nombre + " " + apellido);
	console.log("Peticion realizada!");
}

function getFavorito(req,res){
	const favoritoId = req.params.id;

	Favorito.findById(favoritoId,function(err,favorito){
		
		if(err){
			res.status(500).send({message:"error al devolver marcador"});
		}else{
			res.status(200).send({favorito});
		}

	})

}

function saveFavorito(req,res){
	var favorito = new Favorito();
	var params = req.body;
	
	favorito.title = params.title;
	favorito.description = params.description;
	favorito.url = params.url;

	favorito.save((err,favoritoStored)=>{
		if(err){
			console.log(err);
			res.send({message: "No se pudo guardar el registro"});	
		}else{
			res.send({message:favoritoStored});
		}
	})

}

function updateFavorito(req,res){
	
	var params = req.body;
	const favoritoId = req.params.id;

	Favorito.findByIdAndUpdate(favoritoId,params,function(err,favoritoUpdated){
		if(err){
			res.status(500).send({message:"error al devolver marcador"});
		}else{
			res.status(200).send({favoritoUpdated});
		}

	})	

}

function deleteFavorito(req,res){
	const favoritoId = req.params.id;

	Favorito.findOneAndRemove(favoritoId,function(err,deletedFavorito){
		if(err){
			res.status(500).send({message:"error al devolver marcador"});
		}else{
			res.status(200).send({deletedFavorito});
		}

	})

}

function getFavoritos(req,res){

	Favorito.find({}).collation({locale:"en"}).sort({title:-1}).exec((err,favoritos)=>{
		
		if(err){
			res.status(500).send({message: "Hubo en error en el server"});
		}
		if(!favoritos){
			res.status(404).send({message: "No hay registros para mostrar"});
		}

		res.status(200).send({favoritos});

	});

}

function getwebpage(req,res){
	res.sendFile(path.join(__dirname+'../../views/prueba.html'));
}

module.exports = {
	saludar,
	getFavorito,
	getFavoritos,
	saveFavorito,
	updateFavorito,
	deleteFavorito,
	getwebpage
}