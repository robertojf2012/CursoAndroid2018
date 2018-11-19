package com.example.robert.dialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_dialog.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMostrar.setOnClickListener {
            val myDialogView = layoutInflater.inflate(R.layout.login_dialog,null)

            val mBuilder = AlertDialog.Builder(this).setView(myDialogView).setTitle("Login Form")

            val mAlertDialog = mBuilder.show()

            myDialogView.btnLogin.setOnClickListener {
                val name = myDialogView.txtName.text.toString()
                val email = myDialogView.txtEmail.text.toString()
                val password = myDialogView.txtPassword.text.toString()

                tvMensaje.setText("Nombre:${name} \n Email: ${email} \n Password:${password}")

                mAlertDialog.dismiss()
            }

            myDialogView.btnCancelar.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }
}
