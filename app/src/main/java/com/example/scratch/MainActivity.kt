package com.example.scratch

import android.view.animation.AnimationUtils
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog
import android.widget.EditText
import android.view.WindowManager
import android.widget.RadioButton
import android.content.Intent
import android.widget.TextView
import android.widget.RadioGroup


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOpenRunPanel = findViewById<Button>(R.id.RunApp)
        btnOpenRunPanel.setOnClickListener{
            val panel = findViewById<LinearLayout>(R.id.RunAppPanel)
            val animOpen = AnimationUtils.loadAnimation(this, R.anim.slide_in_top)
            val animClose = AnimationUtils.loadAnimation(this, R.anim.slide_out_top)

            if (panel.visibility == View.VISIBLE)
            {
                panel.visibility = View.GONE
                panel.startAnimation(animClose)
            }
            else
            {
                panel.visibility = View.VISIBLE
                panel.startAnimation(animOpen)
            }
        }

        val btnOpenSLPanel = findViewById<Button>(R.id.SaveLoad)
        btnOpenSLPanel.setOnClickListener{
            val panel = findViewById<LinearLayout>(R.id.SaveLoadPanel)
            val animOpen = AnimationUtils.loadAnimation(this, R.anim.slide_in_top)
            val animClose = AnimationUtils.loadAnimation(this, R.anim.slide_out_top)

            if (panel.visibility == View.VISIBLE)
            {
                panel.visibility = View.GONE
                panel.startAnimation(animClose)
            }
            else
            {
                panel.visibility = View.VISIBLE
                panel.startAnimation(animOpen)
            }
        }

        val saveWindow = findViewById<Button>(R.id.Save)
        saveWindow.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Название окна")
            val input = EditText(this)
            builder.setView(input)
            builder.setPositiveButton("Ок") { _, _ -> }
            builder.setNegativeButton("Отмена") { dialog, _ -> dialog.cancel() }
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            dialog.window?.decorView?.systemUiVisibility = window.decorView.systemUiVisibility
            dialog.setOnShowListener {
                dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            }
        }

        val myButton = findViewById<Button>(R.id.Load)
        myButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, MyActivity::class.java)
                intent.putExtra("key", "value")
                startActivity(intent)
            }
        })
    }
}


class MyActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var cancelButton: Button
    private lateinit var okButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity_layout)

        titleTextView = findViewById(R.id.title)
        radioGroup = findViewById(R.id.radio_group)
        cancelButton = findViewById(R.id.button_cancel)
        okButton = findViewById(R.id.button_ok)

        titleTextView.text = "New Title"

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
            val text = radioButton.text.toString()
        }

        cancelButton.setOnClickListener {
            finish()
        }

        okButton.setOnClickListener {
            finish()
        }
    }
}
