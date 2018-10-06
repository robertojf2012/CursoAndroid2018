'use strict'
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const FavoritoSchema = Schema({
	title: String,
	description: String,
	url: String
})

module.exports = mongoose.model('Favorito',FavoritoSchema);
