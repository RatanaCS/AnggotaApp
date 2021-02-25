package com.sumuzu.anggotaapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sumuzu.anggotaapp.R
import com.sumuzu.anggotaapp.config.NetworkModule
import com.sumuzu.anggotaapp.model.ResponseAction
import com.sumuzu.anggotaapp.model.getData.DataItem
import com.sumuzu.anggotaapp.viewModel.ViewModelInputActivity
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {

    lateinit var viewModel : ViewModelInputActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        viewModel = ViewModelProviders.of(this).get(ViewModelInputActivity::class.java)

        val getData = intent.getParcelableExtra<DataItem>("data")

        if(getData != null) {
            etNama.setText(getData.nama)
            etNoHP.setText(getData.nohp)
            etAlamat.setText(getData.alamat)
            btnSimpan.text ="Update"
        }

        when(btnSimpan.text){
            "Update" ->{
                btnSimpan.setOnClickListener {
//                    updateData(getData?.id, etNama.text.toString(), etNoHP.text.toString(), etAlamat.text.toString())
                    viewModel.updateData(getData?.id, etNama.text.toString(), etNoHP.text.toString(), etAlamat.text.toString())
                }
            }else -> {
                btnSimpan.setOnClickListener {
//                    inputData(etNama.text.toString(), etNoHP.text.toString(), etAlamat.text.toString())
//                    inputData()
                    viewModel.insertData(etNama.text.toString(), etNoHP.text.toString(), etAlamat.text.toString())
                }
            }
        }

        btnCancel.setOnClickListener {
//            etAlamat.text.clear()
//            etNoHP.text.clear()
//            etAlamat.text.clear()
            finish()
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.responseAction.observe(this, Observer {
            inputData(it)
            updateData(it)
        })

        viewModel.isError.observe(this, Observer {
            showError(it)
        })

        viewModel.isLoading.observe(this, Observer {
            showLoading(it)
        })
    }

    private fun updateData(it: ResponseAction?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun showLoading(it: Boolean?) {
        if(it==true){
//            progress.visibility = View.VISIBLE
        }else{
//            progress.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun inputData(it: ResponseAction?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
        finish()
    }


//    private fun inputData(nama : String?, nohp : String?, alamat : String?){
//
//        val input = NetworkModule.service().insertData(nama ?: "", nohp ?:"", alamat ?:"")
//        input.enqueue(object : Callback<ResponseAction>{
//            override fun onResponse(
//                call: Call<ResponseAction>,
//                response: Response<ResponseAction>
//            ) {
//
//                if(response.isSuccessful){
//                    Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_LONG).show()
//                    finish()
//
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//
//            }
//
//        })
//    }

//    private fun updateData(id : String?, nama : String?, nohp : String?, alamat : String?){
//
//        val update = NetworkModule.service().updateData(id ?:"",nama ?: "", nohp ?:"", alamat ?:"")
//        update.enqueue(object : Callback<ResponseAction>{
//            override fun onResponse(
//                call: Call<ResponseAction>,
//                response: Response<ResponseAction>
//            ) {
//
//                if(response.isSuccessful){
//                    Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_LONG).show()
//                    finish()
//
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//
//            }
//
//        })
//    }

}