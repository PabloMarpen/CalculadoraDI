package com.example.calculadoradi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var AC: Button
    lateinit var CA: Button
    lateinit var division: Button
    lateinit var multiplicacion: Button
    lateinit var resta: Button
    lateinit var suma: Button
    lateinit var igual: Button
    lateinit var coma: Button
    lateinit var cero: Button
    lateinit var uno: Button
    lateinit var dos: Button
    lateinit var tres: Button
    lateinit var cuatro: Button
    lateinit var cinco: Button
    lateinit var seis: Button
    lateinit var siete: Button
    lateinit var ocho: Button
    lateinit var nueve: Button
    lateinit var visorTextView: TextView
    lateinit var ayuda : Button
    var visor = ""
    var conjunto1: Int? = null
    var conjunto2: Int? = null
    var resultado: Int? = null
    var operacion: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        AC = findViewById(R.id.buttonAC)
        division = findViewById(R.id.buttonDividir)
        multiplicacion = findViewById(R.id.buttonMulti)
        resta = findViewById(R.id.buttonResta)
        suma = findViewById(R.id.buttonSuma)
        igual = findViewById(R.id.buttonIgual)
        coma = findViewById(R.id.buttonComa)
        cero = findViewById(R.id.button0)
        uno = findViewById(R.id.button1)
        dos = findViewById(R.id.button2)
        tres = findViewById(R.id.button3)
        cuatro = findViewById(R.id.button4)
        cinco = findViewById(R.id.button5)
        seis = findViewById(R.id.button6)
        siete = findViewById(R.id.button7)
        ocho = findViewById(R.id.button8)
        nueve = findViewById(R.id.button9)
        visorTextView = findViewById(R.id.textView)
        ayuda = findViewById(R.id.buttonAyuda)
        CA = findViewById(R.id.buttonCA)

        uno.setOnClickListener { agregarNumero("1") }
        dos.setOnClickListener { agregarNumero("2") }
        tres.setOnClickListener { agregarNumero("3") }
        cuatro.setOnClickListener { agregarNumero("4") }
        cinco.setOnClickListener { agregarNumero("5") }
        seis.setOnClickListener { agregarNumero("6") }
        siete.setOnClickListener { agregarNumero("7") }
        ocho.setOnClickListener { agregarNumero("8") }
        nueve.setOnClickListener { agregarNumero("9") }
        cero.setOnClickListener { agregarNumero("0") }

        suma.setOnClickListener { seleccionarOperacion("+") }
        resta.setOnClickListener { seleccionarOperacion("-") }
        multiplicacion.setOnClickListener { seleccionarOperacion("*") }
        division.setOnClickListener { seleccionarOperacion("/") }

        igual.setOnClickListener {
            conjunto2 = visor.toIntOrNull()
            if (conjunto1 != null && conjunto2 != null && operacion != null) {
                calcularResultado()
            } else {
                visorTextView.text = "Error"
            }
        }

        AC.setOnClickListener {
            visor = ""
            conjunto1 = null
            conjunto2 = null
            operacion = null
            visorTextView.text = ""
        }

        CA.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
        }

        ayuda.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun agregarNumero(num: String) {
        visor += num
        visorTextView.text = visor
    }

    private fun seleccionarOperacion(valor: String) {
        conjunto1 = visor.toIntOrNull()
        operacion = valor
        visor = ""
        visorTextView.text = ""
    }

    private fun calcularResultado() {
        resultado = when (operacion) {
            "+" -> conjunto1!! + conjunto2!!
            "-" -> conjunto1!! - conjunto2!!
            "*" -> conjunto1!! * conjunto2!!
            "/" -> if (conjunto2 != 0) conjunto1!! / conjunto2!! else null
            else -> null
        }
        visorTextView.text = resultado?.toString() ?: "Error"
        conjunto1 = null
        conjunto2 = null
        operacion = null
        visor = ""
    }
}