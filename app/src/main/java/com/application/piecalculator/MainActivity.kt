package com.application.piecalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat




class MainActivity : AppCompatActivity() {
    private lateinit var resultTv: TextView
    private lateinit var solutionTv: TextView

    private lateinit var btnC: MaterialButton
    private lateinit var btnBrkOpen: MaterialButton
    private lateinit var btnBrkClose: MaterialButton
    private lateinit var btnDiv: MaterialButton
    private lateinit var btnMul: MaterialButton
    private lateinit var btnPlus: MaterialButton
    private lateinit var btnMinus: MaterialButton
    private lateinit var btnEqls: MaterialButton
    private lateinit var btn0: MaterialButton
    private lateinit var btn1: MaterialButton
    private lateinit var btn2: MaterialButton
    private lateinit var btn3: MaterialButton
    private lateinit var btn4: MaterialButton
    private lateinit var btn5: MaterialButton
    private lateinit var btn6: MaterialButton
    private lateinit var btn7: MaterialButton
    private lateinit var btn8: MaterialButton
    private lateinit var btn9: MaterialButton
    private lateinit var btnPercent: MaterialButton
    private lateinit var btnDot: MaterialButton
    private lateinit var btnPow: MaterialButton
    private lateinit var btnSqrt: MaterialButton
    private lateinit var btnAns: MaterialButton
    private lateinit var btnBackspace: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize buttons by finding them from the layout
        btnC = findViewById(R.id.button_c)
        btnBrkOpen = findViewById(R.id.button_open_paren)
        btnBrkClose = findViewById(R.id.button_close_paren)
        btnDiv = findViewById(R.id.button_divide)
        btnMul = findViewById(R.id.button_mul)
        btnPlus = findViewById(R.id.button_plus)
        btnMinus = findViewById(R.id.button_minus)
        btnEqls = findViewById(R.id.button_equals)
        btn0 = findViewById(R.id.button_0)
        btn1 = findViewById(R.id.button_1)
        btn2 = findViewById(R.id.button_2)
        btn3 = findViewById(R.id.button_3)
        btn4 = findViewById(R.id.button_4)
        btn5 = findViewById(R.id.button_5)
        btn6 = findViewById(R.id.button_6)
        btn7 = findViewById(R.id.button_7)
        btn8 = findViewById(R.id.button_8)
        btn9 = findViewById(R.id.button_9)
        btnPercent = findViewById(R.id.button_percentage)
        btnDot = findViewById(R.id.button_dot)
        btnPow = findViewById(R.id.button_pow)
        btnSqrt = findViewById(R.id.button_sqrt)
        btnAns = findViewById(R.id.button_ans)
        btnBackspace = findViewById(R.id.button_backspace)

        // Set click listeners for each button
        btnC.setOnClickListener { handleButtonClick(btnC) }
        btnBrkOpen.setOnClickListener { handleButtonClick(btnBrkOpen) }
        btnBrkClose.setOnClickListener { handleButtonClick(btnBrkClose) }
        btnDiv.setOnClickListener { handleButtonClick(btnDiv) }
        btnMul.setOnClickListener { handleButtonClick(btnMul) }
        btnPlus.setOnClickListener { handleButtonClick(btnPlus) }
        btnMinus.setOnClickListener { handleButtonClick(btnMinus) }
        btnEqls.setOnClickListener { handleButtonClick(btnEqls) }
        btn0.setOnClickListener { handleButtonClick(btn0) }
        btn1.setOnClickListener { handleButtonClick(btn1) }
        btn2.setOnClickListener { handleButtonClick(btn2) }
        btn3.setOnClickListener { handleButtonClick(btn3) }
        btn4.setOnClickListener { handleButtonClick(btn4) }
        btn5.setOnClickListener { handleButtonClick(btn5) }
        btn6.setOnClickListener { handleButtonClick(btn6) }
        btn7.setOnClickListener { handleButtonClick(btn7) }
        btn8.setOnClickListener { handleButtonClick(btn8) }
        btn9.setOnClickListener { handleButtonClick(btn9) }
        btnPercent.setOnClickListener { handleButtonClick(btnPercent) }
        btnDot.setOnClickListener { handleButtonClick(btnDot) }
        btnPow.setOnClickListener { handleButtonClick(btnPow) }
        btnSqrt.setOnClickListener { handleButtonClick(btnSqrt) }
        btnAns.setOnClickListener { handleButtonClick(btnAns) }
        btnBackspace.setOnClickListener { handleButtonClick(btnBackspace) }

        resultTv = findViewById(R.id.result_tv)
        solutionTv = findViewById(R.id.solution_tv)
    }


    // Algorithm
    private fun getResult(exp: String): String {
        try {
            // Create an instance of Expression with the given expression
            val expression = Expression(exp)

            // Evaluate the expression
            val res = expression.calculate()

            // Check if the result is NaN or infinite
            return if (res.isNaN() || res.isInfinite()) {
                "0"
            } else {
                // Format the result with up to 6 decimal places
                DecimalFormat("0.######").format(res)
            }
        } catch (e: Exception) {
            // Handle any exceptions that occur during expression evaluation
            println("Error occurred due to $e")
            return "0"
        }
    }



    // Define isEquals as a property of the containing class or ViewModel
    private var isEquals = false
    private var currentResultText = "0"

    private fun handleButtonClick(button: MaterialButton) {
        val buttonText = button.text.toString()
        var currentSolutionText = solutionTv.text.toString()


        if (currentSolutionText == "0") {
            currentSolutionText = ""
        }

        val lastIndx = currentSolutionText.length - 1;
        if (buttonText == "⌫") {
            // Handle backspace
            if (currentSolutionText.isNotEmpty()) {
                currentSolutionText = currentSolutionText.substring(0, currentSolutionText.length - 1)
            }
            if(currentSolutionText.isEmpty()){
                currentSolutionText = "0"
            }
        } else if (buttonText == "C") {
            currentSolutionText = "0"
            currentResultText = "0"
        } else if (buttonText == "=") {
            // Handle result
            currentResultText = getResult(currentSolutionText).toString()
            currentSolutionText = currentResultText
            isEquals = true
        }else if(buttonText == "Ans" && (currentSolutionText[lastIndx] == '/' || currentSolutionText[lastIndx] == '*' ||
                                         currentSolutionText[lastIndx] == '-' || currentSolutionText[lastIndx] == '+')) {
            currentSolutionText += currentResultText
        }else if(buttonText == "Ans" && (currentSolutionText[lastIndx] != '/' || currentSolutionText[lastIndx] != '*' ||
                                         currentSolutionText[lastIndx] != '-' || currentSolutionText[lastIndx] != '+')) {
            currentSolutionText = currentResultText
        }else if(buttonText != "Ans"){
            // Handle other button presses (values or operators)
            if (isEquals) {
                // Reset to start a new expression after "=" was pressed
                currentSolutionText = currentResultText + buttonText
                isEquals = false
            } else {
                currentSolutionText += buttonText
            }

            // Check if buttonText is not an operator ("/", "*", "-", "+")
            if (buttonText !in listOf("/", "*", "-", "+", "^")) {
                currentResultText = getResult(currentSolutionText).toString()
            }

        }

        if(currentSolutionText[0] == '0'){
            currentSolutionText.removeRange(0, 1);
        }
        // Update the solutionTv with the new text
        solutionTv.text = currentSolutionText
        resultTv.text = currentResultText
    }



}
