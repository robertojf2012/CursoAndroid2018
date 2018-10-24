package com.example.robert.favoritoapp

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorito.view.*


class FavoritoAdapter(private val favoritoList: FavoritoResult, private val context:Context): RecyclerView.Adapter<FavoritoAdapter.ViewHolder{

    //Este metodo se va a sobreescribir la lista por la vista custom que creamos que se llama item_favorito.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent, false) //2
        return ViewHolder(view)
    }

    //rellena la posicion de acuerdo a lo que responde el webservice
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(favoritoList.favoritos[position]) //3

        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetailFavorito::class.java)
            intent.putExtra("title",favoritoList.favoritos[position].title)
            intent.putExtra("description",favoritoList.favoritos[position].description)
            intent.putExtra("url",favoritoList.favoritos[position].url)
            context.startActivity(intent)
        }

    }

    //Numero de elementos que va a mostrar en la lista
    override fun getItemCount(): Int = favoritoList.favoritos.size //4

    //Esta clase se encarga de llenar la informacion
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(favorito: Item) { //5
            with(favorito) {
                itemView.title.text = favorito.title
                itemView.description.text = favorito.description
                //itemView.url.text = favorito.url
                Picasso.get().load(favorito.url).into(itemView.icon)
            }
        }
    }

}