package com.example.solucionoriontek

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.solucionoriontek.models.cliente
import com.example.solucionoriontek.models.empresa
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity: AppCompatActivity() {
    private lateinit var buttons: FloatingActionButton
    private lateinit var myrecv: RecyclerView
    private lateinit var listaClientes: ArrayList<cliente>
    private lateinit var listaDirecciones: List<String>
    private lateinit var MainAdapter: RecyclerViewAdapter
    private lateinit var empresa: empresa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listaClientes = ArrayList ()
        listaDirecciones=ArrayList()
        empresa= empresa()
        buttons=findViewById(R.id.addbttn)
        myrecv=findViewById(R.id.mRecycler)
        MainAdapter=RecyclerViewAdapter(this, empresa)
        myrecv.layoutManager = LinearLayoutManager(this)
        myrecv.adapter = MainAdapter

        buttons.setOnClickListener {
            addInfo() }
    }
    private fun addInfo(){
        if(MainAdapter.itemCount==0){
            val inflater= LayoutInflater.from(this)
            val v= inflater.inflate(R.layout.addfirstitem,null)
            val nombreCliente = v.findViewById<EditText>(R.id.addnombrecliente1)
            val direccion = v.findViewById<EditText>(R.id.adddirecciones1)
            val nombredeLaempresa= v.findViewById<EditText>(R.id.nombreEmpresa)
            val addDialog= AlertDialog.Builder(this)

            addDialog.setView(v)
            addDialog.setPositiveButton("Ok"){
                    dialog,_->
                val nombres = nombreCliente.text.toString()
                val direcciones=direccion.text.toString()
                val nombreempresa=nombredeLaempresa.text.toString()
                listaDirecciones= stringToWords(direcciones)
                listaClientes.add(cliente("Nombre: $nombres",listaDirecciones,RecyclerViewAdapter.VIEW_TYPE_ONE))
                empresa.nombre=nombreempresa
                empresa.listadoclientes=listaClientes
                MainAdapter.notifyDataSetChanged()
                Toast.makeText(this,"Empresa creada", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            addDialog.setNegativeButton("Cancelar"){
                    dialog,_->
                dialog.dismiss()
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show()
            }
            addDialog.create()
            addDialog.show()

        }
        else{
            val inflater= LayoutInflater.from(this)
            val v= inflater.inflate(R.layout.additem,null)
            val nombreCliente = v.findViewById<EditText>(R.id.nombrecliente)
            val direccion = v.findViewById<EditText>(R.id.direcciones)
            val addDialog= AlertDialog.Builder(this)

            addDialog.setView(v)
            addDialog.setPositiveButton("Ok"){
                    dialog,_->

                val nombres = nombreCliente.text.toString()
                val direcciones=direccion.text.toString()
                listaDirecciones= stringToWords(direcciones)
                listaClientes.add(cliente("Nombre: $nombres",listaDirecciones,RecyclerViewAdapter.VIEW_TYPE_TWO))
                empresa.listadoclientes=listaClientes
                MainAdapter.notifyDataSetChanged()
                Toast.makeText(this,"Cliente añadido", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            addDialog.setNegativeButton("Cancelar"){
                    dialog,_->
                dialog.dismiss()
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show()

            }
            addDialog.create()
            addDialog.show()
        }

    }

    fun stringToWords(s : String) = s.trim().splitToSequence(',')
        .filter { it.isNotEmpty() }
        .toList()

}