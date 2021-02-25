package com.sumuzu.anggotaapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumuzu.anggotaapp.model.ResponseAction
import com.sumuzu.anggotaapp.model.getData.ResponseGetData
import com.sumuzu.anggotaapp.repo.Repository

class ViewModelMainActivity : ViewModel() {

    val repository = Repository()

    var responseData = MutableLiveData<ResponseGetData>()
    var responseAction = MutableLiveData<ResponseAction>()
    var isError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getListData(){

        isLoading.value = true

        repository.getData({
            responseData.value = it
            isLoading.value = false
        },{
            isError.value = it
            isLoading.value = false
        })

    }

    fun deleteData(id: String?){

        isLoading.value = true

        repository.deleteData(id,
            {
            responseAction.value = it
            isLoading.value = false
        },{
            isError.value = it
            isLoading.value = false
        })
    }

}