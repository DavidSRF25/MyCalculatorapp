package com.example.mycalculatorapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.core.view.WindowInsetsCompat
import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    var OperacionAc =""
    var  primerNum:Double = Double.NaN
    var secondNum:Double = Double.NaN
    lateinit var  tvTemp:TextView
    lateinit var result: TextView

    lateinit var formatDecimal : DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        formatDecimal = DecimalFormat("#.#######")
        tvTemp = findViewById(R.id.tvtemp)
        result = findViewById(R.id.result)

        tvTemp.text=""

    }

    //Funcion cambiar operador del boton
    fun changeOperator(b: View){

        if(tvTemp.text.isNotEmpty() ||  primerNum.toString()!="NaN"){

        Calculate()
        //convertir View a Boton
        val button:Button = b as Button

        if(button.text.toString().trim()=="÷"){

            OperacionAc = "/"
        }else if(button.text.toString().trim()=="*"){

            OperacionAc = "*"

        }else{

            OperacionAc = button.text.toString().trim()

        }
        result.text = formatDecimal.format(primerNum) + OperacionAc
        tvTemp.text = ""
        }
    }

    fun Calculate(){

    try {
        if(primerNum.toString()!="NaN"){
            if(tvTemp.text.toString().isEmpty()){

                tvTemp.text = result.text.toString()

            }

            secondNum = tvTemp.text.toString().toDouble()
            tvTemp.text=""

            when(OperacionAc){
                "+"-> primerNum = (primerNum+secondNum)
                "-"-> primerNum = (primerNum-secondNum)
                "*"-> primerNum = (primerNum*secondNum)
                "/"-> primerNum = (primerNum/secondNum)
                "%"-> primerNum = (primerNum%secondNum)
            }

        }else{

            primerNum = tvTemp.text.toString().toDouble()


        }
    }catch (e:Exception){


    }


    }

    fun SelectNumber(b: View){


        val boton:Button = b as Button

        tvTemp.text = tvTemp.text.toString() + boton.text.toString()


    }


    fun  Equals(b:View){
        Calculate()

        result.text = formatDecimal.format(primerNum)

        OperacionAc = ""

    }

    fun Delete(b: View){

        //convertir View a Boton
        val button:Button = b as Button

        if(button.text.toString().trim()=="C"){

            tvTemp.text = ""
            primerNum = Double.NaN
            secondNum = Double.NaN
            result.text = ""

        }



    }
}