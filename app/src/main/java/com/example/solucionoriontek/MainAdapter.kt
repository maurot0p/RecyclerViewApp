package com.example.solucionoriontek

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.solucionoriontek.models.empresa


class RecyclerViewAdapter(val c: Context, val empresa: empresa):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }
    private val context: Context = c
    val empresa2: empresa= empresa

    inner class MainViewHolder(val v:View):RecyclerView.ViewHolder(v) {
        var nombre: TextView=v.findViewById(R.id.nombrecliente1)
        var nombreEmpresa: TextView=v.findViewById(R.id.nombreempresa1)
        var menus: ImageView=v.findViewById(R.id.mMenus)
        var direcciones: TextView=v.findViewById(R.id.direccionescliente1)
        fun bind(position: Int) {
            val recyclerViewModel = empresa2
            nombre.text=recyclerViewModel.listadoclientes[position].nombrecliente
            nombreEmpresa.text=recyclerViewModel.nombre
            direcciones.text=recyclerViewModel.listadoclientes[position].direcciones.toString()
            menus.setOnClickListener()
            { val recyclerViewModel = empresa2
                nombre.text=recyclerViewModel.listadoclientes[position].nombrecliente
                direcciones.text=recyclerViewModel.listadoclientes[position].direcciones.toString()
                menus.setOnClickListener()
                {  val position = empresa.listadoclientes[position]
                    val popupMenus = PopupMenu(c,v)
                    popupMenus.inflate(R.menu.show_menu)
                    popupMenus.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.editText->{
                                val v = LayoutInflater.from(c).inflate(R.layout.addfirstitem,null)
                                val textview=v.findViewById<TextView>(R.id.editarinfo2)
                                textview.text="Editar la información del cliente"
                                val nombreEmpresa=v.findViewById<EditText>(R.id.nombreEmpresa)
                                nombreEmpresa.hint=empresa2.nombre
                                nombreEmpresa.isEnabled=false
                                val nombre = v.findViewById<EditText>(R.id.addnombrecliente1)
                                nombre.hint=position.nombrecliente
                                val direccion = v.findViewById<EditText>(R.id.adddirecciones1)
                                direccion.hint= position.direcciones.toString()
                                AlertDialog.Builder(c)
                                    .setView(v)
                                    .setPositiveButton("Ok"){
                                            dialog,_->
                                        position.nombrecliente = nombre.text.toString()
                                        val direcciones=direccion.text.toString()
                                        position.direcciones=stringToWords(direcciones)
                                        notifyDataSetChanged()
                                        Toast.makeText(c,"Información de cliente editada",Toast.LENGTH_SHORT).show()
                                        dialog.dismiss()
                                    }
                                    .setNegativeButton("Cancelar"){
                                            dialog,_->
                                        dialog.dismiss()

                                    }
                                    .create()
                                    .show()

                                true
                            }
                            R.id.delete->{
                                AlertDialog.Builder(c)
                                    .setTitle("Borrar cliente")
                                    .setIcon(R.drawable.ic_warning)
                                    .setMessage("Estas seguro de que deseas borrar esta información?")
                                    .setPositiveButton("Yes"){
                                            dialog,_->
                                        empresa2.listadoclientes.remove(position)
                                        notifyDataSetChanged()
                                        Toast.makeText(c,"Información borrada",Toast.LENGTH_SHORT).show()
                                        dialog.dismiss()
                                    }
                                    .setNegativeButton("No"){
                                            dialog,_->
                                        dialog.dismiss()
                                    }
                                    .create()
                                    .show()

                                true
                            }
                            else-> true
                        }

                    }
                    popupMenus.show()
                    val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                    popup.isAccessible = true
                    val menu = popup.get(popupMenus)
                    menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                        .invoke(menu,true)
                } }
        }}


    inner class SecondViewHolder(val v:View):RecyclerView.ViewHolder(v) {
        var nombre: TextView=v.findViewById(R.id.nombrecliente)
        var menus: ImageView=v.findViewById(R.id.mMenus2)
        var direcciones: TextView=v.findViewById(R.id.direccionescliente)
        fun bind(position: Int) {
            val recyclerViewModel = empresa2
            nombre.text=recyclerViewModel.listadoclientes[position].nombrecliente
            direcciones.text=recyclerViewModel.listadoclientes[position].direcciones.toString()
            menus.setOnClickListener()
            {  val position = empresa.listadoclientes[position]
                val popupMenus = PopupMenu(c,v)
                popupMenus.inflate(R.menu.show_menu)
                popupMenus.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.editText->{
                            val v = LayoutInflater.from(c).inflate(R.layout.additem,null)
                            val textview=v.findViewById<TextView>(R.id.editarinfo)
                            textview.text="Editar la información del cliente"
                            val nombre = v.findViewById<EditText>(R.id.nombrecliente)
                            nombre.hint=position.nombrecliente
                            val direccion = v.findViewById<EditText>(R.id.direcciones)
                            direccion.hint= position.direcciones.toString()
                            AlertDialog.Builder(c)
                                .setView(v)
                                .setPositiveButton("Ok"){
                                        dialog,_->
                                    position.nombrecliente = nombre.text.toString()
                                    val direcciones=direccion.text.toString()
                                    position.direcciones=stringToWords(direcciones)
                                    notifyDataSetChanged()
                                    Toast.makeText(c,"Información de cliente editada",Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()
                                }
                                .setNegativeButton("Cancelar"){
                                        dialog,_->
                                    dialog.dismiss()

                                }
                                .create()
                                .show()

                            true
                        }
                        R.id.delete->{
                            AlertDialog.Builder(c)
                                .setTitle("Borrar")
                                .setIcon(R.drawable.ic_warning)
                                .setMessage("Estas seguro de que deseas borrar esta información?")
                                .setPositiveButton("Si"){
                                        dialog,_->
                                    empresa2.listadoclientes.remove(position)
                                    notifyDataSetChanged()
                                    Toast.makeText(c,"Usuario borrado",Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()
                                }
                                .setNegativeButton("No"){
                                        dialog,_->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()

                            true
                        }
                        else-> true
                    }

                }
                popupMenus.show()
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(popupMenus)
                menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                    .invoke(menu,true)
            } }
        }





    fun stringToWords(s : String) = s.trim().splitToSequence(',')
        .filter { it.isNotEmpty() }
        .toList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType== VIEW_TYPE_ONE){
            return MainViewHolder(
                LayoutInflater.from(context).inflate(R.layout.firstitem, parent, false)
            )
        }
        return SecondViewHolder(
            LayoutInflater.from(context).inflate(R.layout.listitem, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (empresa2.listadoclientes[position].viewType === VIEW_TYPE_ONE) {
            (holder as MainViewHolder).bind(position)
        } else {
            (holder as SecondViewHolder).bind(position)
        }

    }
    override fun getItemViewType(position: Int): Int {
        return empresa2.listadoclientes[position].viewType
    }


    override fun getItemCount(): Int {
        return empresa.listadoclientes.size
    }

}
