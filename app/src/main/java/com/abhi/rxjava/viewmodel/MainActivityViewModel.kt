package com.abhi.rxjava.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.abhi.rxjava.model.BookListModel
import com.abhi.rxjava.network.RetroService
import com.abhi.rxjava.network.RetrofitInstance

class MainActivityViewModel : ViewModel() {
    var bookList: MutableLiveData<BookListModel>

    init {
        bookList = MutableLiveData()
    }

    fun getBookListObserver(): MutableLiveData<BookListModel> {
        return bookList
    }

    fun makeApiCall(query: String) {
        val retroInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getBookListFromApi(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx())
    }

    private fun getBookListObserverRx(): Observer<BookListModel> {
        return object : Observer<BookListModel> {
            override fun onComplete() {
                //hide progress indicator .

            }

            override fun onError(e: Throwable) {
                bookList.postValue(null)
            }

            override fun onNext(t: BookListModel) {
                bookList.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }
        }
    }
}