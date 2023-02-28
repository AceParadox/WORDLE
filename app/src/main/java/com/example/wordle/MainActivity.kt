package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    val wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etValue = findViewById<TextInputEditText>(R.id.etValue)
        val button = findViewById<Button>(R.id.guessButton)
        val textViewG1 = findViewById<TextView>(R.id.textView_Guess1)
        val textViewC1 = findViewById<TextView>(R.id.textView_Check1)
        val textViewG2 = findViewById<TextView>(R.id.textView_Guess2)
        val textViewC2 = findViewById<TextView>(R.id.textView_Check2)
        val textViewG3 = findViewById<TextView>(R.id.textView_Guess3)
        val textViewC3 = findViewById<TextView>(R.id.textView_Check3)
        val textViewAns = findViewById<TextView>(R.id.textView_answer)
        val textViewCongrats = findViewById<TextView>(R.id.textView_Congrats)

        println(wordToGuess)

        button.setOnClickListener{
            val guess = etValue.text.toString().toUpperCase()
            textViewG1.text = guess
            val result = checkGuess(guess)
            textViewC1.text = result
            etValue.setText("")
            if(guess == wordToGuess){
                Toast.makeText(this@MainActivity, "Good Job", Toast.LENGTH_SHORT).show()
                button.isEnabled = false
                button.isClickable = false
                textViewAns.text = wordToGuess
                textViewCongrats.text = "YOU WIN"
            } else {
                Toast.makeText(this@MainActivity, "2 Tries Left", Toast.LENGTH_SHORT).show()
                button.setOnClickListener{
                    val guess = etValue.text.toString().toUpperCase()
                    textViewG2.text = guess
                    val result = checkGuess(guess)
                    textViewC2.text = result
                    etValue.setText("")
                    if(guess == wordToGuess){
                        Toast.makeText(this@MainActivity, "Good Job", Toast.LENGTH_SHORT).show()
                        button.isEnabled = false
                        button.isClickable = false
                        textViewAns.text = wordToGuess
                        textViewCongrats.text = "YOU WIN"
                    } else {
                        Toast.makeText(this@MainActivity, "1 Try Left", Toast.LENGTH_SHORT).show()
                        button.setOnClickListener{
                            val guess = etValue.text.toString().toUpperCase()
                            textViewG3.text = guess
                            val result = checkGuess(guess)
                            textViewC3.text = result
                            etValue.setText("")
                            if(guess == wordToGuess){
                                Toast.makeText(this@MainActivity, "Good Job!", Toast.LENGTH_SHORT).show()
                                button.isEnabled = false
                                button.isClickable = false
                                textViewAns.text = wordToGuess
                                textViewCongrats.text = "YOU WIN"
                            } else {
                                Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_SHORT).show()
                                button.isEnabled = false
                                button.isClickable = false
                                textViewAns.text = wordToGuess
                                textViewCongrats.text = "YOU LOSE"
                            }
                        }
                    }
                }
            }
        }
    }

    fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}