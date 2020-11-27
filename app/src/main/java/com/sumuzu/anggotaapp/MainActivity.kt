package com.sumuzu.anggotaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.sumuzu.anggotaapp.adapter.DataAdapter
import com.sumuzu.anggotaapp.config.NetworkModule
import com.sumuzu.anggotaapp.model.ResponseAction
import com.sumuzu.anggotaapp.model.getData.DataItem
import com.sumuzu.anggotaapp.model.getData.ResponseGetData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
            progress.visibility = View.VISIBLE
        }


        showData()


    }

    private fun showData(){
        val listAnggota = NetworkModule.service().getData()
        listAnggota.enqueue(object : Callback<ResponseGetData>{
            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {
                Log.d("response service", response.message())

                if(response.isSuccessful){

                    val item = response.body()?.data

                    val adapter = DataAdapter(item, object : DataAdapter.OnClickListener{
                        override fun detail(item: DataItem?) {

                            val intent = Intent(applicationContext, InputActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)

                        }

                        override fun delete(item: DataItem?) {

                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Yakin mau hapus data??")
                                setPositiveButton("Hapus"){dialog, which->
                                    deleteData(item?.id)
                                    dialog.dismiss()
                                }
                                setNegativeButton("Batal"){dialog, which ->
                                    dialog.dismiss()
                                }
                            }.show()


                        }

                    })

                    progress.visibility = View.GONE
                    rvList.adapter = adapter
                }

            }

            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Log.d("error response service", t.message)

                progress.visibility = View.GONE

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun deleteData(id: String?) {

        val delete = NetworkModule.service().deleteData(id ?:"")
        delete.enqueue(object : Callback<ResponseAction>{
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Data berhasil diHAPUS", Toast.LENGTH_LONG).show()

                    showData()
                }
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

}