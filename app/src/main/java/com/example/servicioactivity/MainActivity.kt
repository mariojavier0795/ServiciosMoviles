package com.example.servicioactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGet.setOnClickListener { metodoGet(txtNombre.text.toString(), txtCosto.text.toString(), txtTabletas.text.toString()) }
        btnPost.setOnClickListener { metodoPost(txtNombre.text.toString(), txtCosto.text.toString(), txtTabletas.text.toString()) }
        btnPut.setOnClickListener { metodoPut(txtNombre.text.toString(), txtCosto.text.toString(), txtTabletas.text.toString()) }
        btnDelete.setOnClickListener { metodoDelete(txtNombre.text.toString(), txtCosto.text.toString(), txtTabletas.text.toString()) }
    }
    fun metodoGet(nombre:String?, costo:String?, tableta:String?){
        val url = "http://192.168.100.8:3000/medicina"
        if(nombre.equals(null) || costo.equals(null) || tableta.equals(null)){
            url.httpGet().responseString{request, response, result ->
                when(result){
                    is Result.Failure ->{
                        val exepcion = result.getException()
                        Toast.makeText(this, "${exepcion}", Toast.LENGTH_LONG).show()
                    }
                    is Result.Success ->{
                        val data =  result.get()
                        Toast.makeText(this, "${data}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }else{
            val json = listOf("nombre" to nombre, "costo" to costo, "tabletas" to tableta)
            url.httpGet(json).responseString{request, response, result ->
                when(result){
                    is Result.Failure ->{
                        val exepcion = result.getException()
                        Toast.makeText(this, "${exepcion}", Toast.LENGTH_LONG).show()
                    }
                    is Result.Success ->{
                        val data =  result.get()
                        Toast.makeText(this, "${data}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun metodoPost(nombre:String?, costo:String?, tableta:String?){
        val url = "http://192.168.100.8:3000/medicina"
        val json = listOf("nombre" to nombre, "costo" to costo, "tabletas" to tableta)
        url.httpPost(json).responseString{request, response, result ->
            when(result){
                is Result.Failure ->{
                    val exepcion = result.getException()
                    Toast.makeText(this, "${exepcion}", Toast.LENGTH_LONG).show()
                }
                is Result.Success ->{
                    val data =  result.get()
                    Toast.makeText(this, "${data}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    fun metodoPut(nombre:String?, costo:String?, tableta:String?) {
        val url = "http://192.168.100.8:3000/medicina"
        val json = listOf("nombre" to nombre, "costo" to costo, "tabletas" to tableta)
        url.httpPut(json).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val exepcion = result.getException()
                    Toast.makeText(this, "${exepcion}", Toast.LENGTH_LONG).show()
                }
                is Result.Success -> {
                    val data = result.get()
                    Toast.makeText(this, "${data}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun metodoDelete(nombre:String?, costo:String?, tableta:String?) {
        val url = "http://192.168.100.8:3000/medicina"
        val json = listOf("nombre" to nombre, "costo" to costo, "tabletas" to tableta)
        url.httpDelete(json).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val exepcion = result.getException()
                    Toast.makeText(this, "${exepcion}", Toast.LENGTH_LONG).show()
                }
                is Result.Success -> {
                    val data = result.get()
                    Toast.makeText(this, "${data}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
