package com.example.medicapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.medicapp.data.NetworkResult

interface NetworkCommunication : Communication<NetworkResult>{
    class Base() : Communication.Abstract<NetworkResult>(), NetworkCommunication
}

interface BaseObserve<T : Any>{
    fun observe(owner: LifecycleOwner, observer: Observer<T>) = Unit
}
interface Communication<T : Any> : BaseObserve<T> {
    fun map(data: T)
    abstract class Abstract<T : Any>(
        private val liveData: MutableLiveData<T> = MutableLiveData(),
    ) : Communication<T> {
        override fun map(data: T) {
            liveData.value = data
        }
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }
}