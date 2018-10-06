'use strict'

const express = require('express');
const FavoritoController = require('../controllers/favorito'); 
const api = express.Router();

api.get('/saludar/:name/:lastname',FavoritoController.saludar); //obtener
api.get('/favorito/:id',FavoritoController.getFavorito); //obtener
api.post('/favorito',FavoritoController.saveFavorito); //guardar
api.put('/favorito/:id',FavoritoController.updateFavorito); //actualizar
api.delete('/favorito/:id',FavoritoController.deleteFavorito); //eliminar
api.get('/web',FavoritoController.getwebpage);

api.get('/favoritos',FavoritoController.getFavoritos); //obtener

module.exports = api;