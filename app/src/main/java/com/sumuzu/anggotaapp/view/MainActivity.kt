package com.sumuzu.anggotaapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sumuzu.anggotaapp.R
import com.sumuzu.anggotaapp.adapter.DataAdapter
import com.sumuzu.anggotaapp.model.ResponseAction
import com.sumuzu.anggotaapp.model.getData.DataItem
import com.sumuzu.anggotaapp.model.getData.ResponseGetData
import com.sumuzu.anggotaapp.viewModel.ViewModelMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : ViewModelMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ViewModelMainActivity::class.java)

        fabAdd.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
//            progress.visibility = View.VISIBLE
            showLoading(true)
        }

        viewModel.getListData()
        attachObserve()

//        showData()


    }

    private fun attachObserve() {
        viewModel.responseData.observe(this, Observer {
            showData(it)
        })

        viewModel.isError.observe(this, Observer {
            showError(it)
        })

        viewModel.isLoading.observe(this, Observer {
            showLoading(it)
        })

        viewModel.responseAction.observe(this, Observer {
            deleteData(it)
        })
    }

    private fun deleteData(it: ResponseAction?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
        showLoading(false)
        viewModel.getListData()
    }


    private fun showLoading(it: Boolean?) {
        if(it==true){
            progress.visibility = View.VISIBLE
        }else{
            progress.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showData(it: ResponseGetData) {

        val adapter = DataAdapter(it.data, object : DataAdapter.OnClickListener{
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
//                        deleteData(item?.id)
                        viewModel.deleteData(item?.id)
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

//        val listAnggota = NetworkModule.service().getData()
//        listAnggota.enqueue(object : Callback<ResponseGetData>{
//            override fun onResponse(
//                call: Call<ResponseGetData>,
//                response: Response<ResponseGetData>
//            ) {
//                Log.d("response service", response.message())
//
//                if(response.isSuccessful){
//
//                    val item = response.body()?.data
//
//                    val adapter = DataAdapter(item, object : DataAdapter.OnClickListener{
//                        override fun detail(item: DataItem?) {
//
//                            val intent = Intent(applicationContext, InputActivity::class.java)
//                            intent.putExtra("data", item)
//                            startActivity(intent)
//
//                        }
//
//                        override fun delete(item: DataItem?) {
//
//                            AlertDialog.Builder(this@MainActivity).apply {
//                                setTitle("Hapus Data")
//                                setMessage("Yakin mau hapus data??")
//                                setPositiveButton("Hapus"){dialog, which->
//                                    deleteData(item?.id)
//                                    dialog.dismiss()
//                                }
//                                setNegativeButton("Batal"){dialog, which ->
//                                    dialog.dismiss()
//                                }
//                            }.show()
//
//
//                        }
//
//                    })
//
//                    progress.visibility = View.GONE
//                    rvList.adapter = adapter
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
//                Log.d("error response service", t.message)
//
//                progress.visibility = View.GONE
//
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//            }
//
//        })

    }

//    private fun deleteData(id: String?) {
//
//        val delete = NetworkModule.service().deleteData(id ?:"")
//        delete.enqueue(object : Callback<ResponseAction>{
//            override fun onResponse(
//                call: Call<ResponseAction>,
//                response: Response<ResponseAction>
//            ) {
//                if(response.isSuccessful){
//                    Toast.makeText(applicationContext, "Data berhasil diHAPUS", Toast.LENGTH_LONG).show()
//
////                    showData(it)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }
//
    override fun onResume() {
        super.onResume()
//        showData(Re)
        viewModel.getListData()
    }

}