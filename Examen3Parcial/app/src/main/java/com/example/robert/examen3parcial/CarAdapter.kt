package com.example.robert.examen3parcial

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.car_item.view.*

class CarAdapter(private val carList: AutoResult, private val context: Context): RecyclerView.Adapter<CarAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(carList.cars[position])
    }
    override fun getItemCount(): Int = carList.cars.size

    //Esta clase se encarga de llenar la informacion
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRepo(car: Item) {
            with(car) {
                itemView.tvMarca.text = car.Marca
                itemView.tvModelo.text = car.Modelo
                itemView.tvAno.text = car.Ano.toString()
                Picasso.get().load(car.imagen).into(itemView.ivImagen)
            }
        }
    }


}