package com.josegrillo.marvelapi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val _errorDisplayer = MutableLiveData<Throwable>()
    val errorDisplayer: LiveData<Throwable> get() = _errorDisplayer

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}