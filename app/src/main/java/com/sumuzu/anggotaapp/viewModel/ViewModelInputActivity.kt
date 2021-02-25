package com.sumuzu.anggotaapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumuzu.anggotaapp.model.ResponseAction
import com.sumuzu.anggotaapp.model.getData.ResponseGetData
import com.sumuzu.anggotaapp.repo.Repository

class ViewModelInputActivity : ViewModel() {

    val repository = Repository()

//    var responseData = MutableLiveData<ResponseGetData>()
    var responseAction = MutableLiveData<ResponseAction>()
    var isError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun insertData(nama : String?, nohp : String?, alamat : String?){
        isLoading.value = true

        repository.insertData(nama, nohp, alamat, {
            responseAction.value = it
            isLoading.value = true
        },{
            isError.value = it
            isLoading.value = false
        })
    }

    fun updateData(id: String?, nama : String?, nohp : String?, alamat : String?){

        isLoading.value = true

        repository.updateData(id, nama, nohp, alamat,{
            responseAction.value = it
            isLoading.value = true
        },{
            isError.value = it
            isLoading.value = false
        })

    }

}