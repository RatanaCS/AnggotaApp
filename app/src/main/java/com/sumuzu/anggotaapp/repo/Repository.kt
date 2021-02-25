package com.sumuzu.anggotaapp.repo

import com.sumuzu.anggotaapp.config.NetworkModule
import com.sumuzu.anggotaapp.model.ResponseAction
import com.sumuzu.anggotaapp.model.getData.ResponseGetData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository {

    fun getData(responHandler : (ResponseGetData) -> Unit, errorHandler : (Throwable) -> Unit){

        NetworkModule.service().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            },{
                errorHandler(it)
            })


    }

    fun deleteData(id: String? , responHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit){
        id?.let {
            NetworkModule.service().deleteData(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responHandler(it)
                },{
                    errorHandler(it)
                })
        }
    }

    fun insertData(nama : String?, nohp : String?, alamat : String? , responHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit){
        nama?.let {
            nohp?.let { it1 ->
                alamat?.let { it2 ->
                    NetworkModule.service().insertData(nama, nohp, alamat).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            responHandler(it)
                        },{
                            errorHandler(it)
                        })
                }
            }
        }
    }

    fun updateData(id : String?, nama : String?, nohp : String?, alamat : String? , responHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit){

        id?.let {
            nama?.let { it1 ->
                nohp?.let { it2 ->
                    alamat?.let { it3 ->
                        NetworkModule.service().updateData(it, it1, it2, it3).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                responHandler(it)
                            },{
                                errorHandler(it)
                            })
                    }
                }
            }
        }

    }


}