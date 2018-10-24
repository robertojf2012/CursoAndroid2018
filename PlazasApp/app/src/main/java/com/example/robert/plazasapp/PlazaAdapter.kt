package com.example.robert.plazasapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_plaza.view.*

class PlazaAdapter(private val plazaList: PlazaResult, private val context: Context): RecyclerView.Adapter<PlazaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plaza, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = plazaList.plazas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(plazaList.plazas[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context,PlazaDetail::class.java)
            intent.putExtra("nombre",plazaList.plazas[position].nombre)
            intent.putExtra("descripcion",plazaList.plazas[position].descripcion)
            intent.putExtra("telefono",plazaList.plazas[position].telefono)
            intent.putExtra("latitud",plazaList.plazas[position].latitud)
            intent.putExtra("longitud",plazaList.plazas[position].longitud)
            intent.putExtra("imagen",plazaList.plazas[position].imagen)

            context.startActivity(intent)
        }

    }

    //Esta clase se encarga de llenar la informacion
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(plaza: Item) { //5
            with(plaza) {
                itemView.tvNombre.text = plaza.nombre
                itemView.tvTelefono.text = plaza.telefono
                //itemView.url.text = favorito.url
                Picasso.get().load(plaza.imagen).into(itemView.ivImagen)
            }
        }
    }
}