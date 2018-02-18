package com.kc.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kc.madridshops.R
import com.kc.madridshops.domain.interactor.internetStatus.IsConnectedToWebInteractorImpl
import com.kc.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shopsButton.setOnClickListener {
            Router().navigateFromMainActivitytoShopActivity(this)
        }

        activitiesButton.setOnClickListener {
            Router().navigateFromMainActivitytoActivityActivity(this)
        }

        IsConnectedToWeb()

    }


    fun IsConnectedToWeb(){
        IsConnectedToWebInteractorImpl().execute(this, success = {
            showButtons()
        }, error = {
            AlertDialog.Builder(this)
                    .setTitle("Connection Error")
                    .setMessage("Error connecting to web")
                    .setPositiveButton("Reintentar", { dialog, _ ->
                        dialog.dismiss()
                        IsConnectedToWeb()

                    })
                    .setNegativeButton("Salir", {_, _ -> this.finish()})
                    .show()

        })
    }

    fun showButtons(){
        shopsButton.visibility = View.VISIBLE
        activitiesButton.visibility = View.VISIBLE
    }
}
