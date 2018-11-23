package com.example.robert.examen3parcial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        //Button to open dialog and create user
        btnGoToRegistrar.setOnClickListener {
            val myDialogView = layoutInflater.inflate(R.layout.dialog,null)
            val mBuilder = AlertDialog.Builder(this).setView(myDialogView)
            val mAlertDialog = mBuilder.show()

            mAlertDialog.btnRegistrar.setOnClickListener {
                var email = mAlertDialog.etEmail.text.toString()
                var password = mAlertDialog.etPassword.text.toString()
                var password2 = mAlertDialog.etPassword2.text.toString()

                if(validateEmail(email) && validatePasswords(password,password2)){
                    createUser(email,password)
                    mAlertDialog.dismiss()
                }

            }

        }

        btnIniciar.setOnClickListener {
            var email = etEmailLogin.text.toString()
            var password = etPasswordLogin.text.toString()
            login(email,password)
        }
    }

    fun goToHome(user: String){
        val intent = Intent(this,HomeActivity::class.java)
        intent.putExtra("emailUser",user)
        this.startActivity(intent)
    }

    fun login(email:String, password:String){
        if(email!="" && password!=""){
            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in: success
                        // update UI for current User
                        val user = mAuth!!.getCurrentUser()
                        Toast.makeText(this,"Login con ${user?.getEmail()}",Toast.LENGTH_SHORT).show()
                        goToHome(user?.getEmail().toString())

                    } else {
                        // Sign in: fail
                        //Log.e(TAG, "signIn: Fail!", task.exception)
                        Toast.makeText(this,"Error, ${task.exception?.message}",Toast.LENGTH_LONG).show()
                    }
                }
        }else{
            Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show()
        }

    }

    fun createUser(email: String, password:String){
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in: success
                    // update UI for current User
                    val user = mAuth!!.currentUser
                    Toast.makeText(this,"Usuario creado exitosamente",Toast.LENGTH_SHORT).show()

                    goToHome(user?.getEmail().toString())

                } else {
                    // Sign in: fail
                    Toast.makeText(this,"Error, ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun validateEmail(e:String): Boolean {
        if(e!=""){
            return true
        }else{
            Toast.makeText(this,"Email invalido",Toast.LENGTH_SHORT).show()
            return false
        }
    }

    fun validatePasswords(p1:String, p2:String): Boolean {

        var lenghtOK = false
        var emptyOK = false
        var equalOK = false

        if(p1.length>=6 && p2.length>=6){
            lenghtOK = true
        }
        if(p1!="" && p2!=""){
            emptyOK = true
        }
        if(p1.equals(p2)){
            equalOK = true
        }

        if(!lenghtOK){
            Toast.makeText(this,"La contrasena debe tener almenos 6 caracteres",Toast.LENGTH_SHORT).show()
        }
        if(!emptyOK){
            Toast.makeText(this,"No has escrito contrasenas",Toast.LENGTH_SHORT).show()
        }
        if(!equalOK){
            Toast.makeText(this,"Las contrasenas no coinciden",Toast.LENGTH_SHORT).show()
        }

        if(!lenghtOK || !emptyOK || !emptyOK){
            return false
        }
        return true
    }
}
