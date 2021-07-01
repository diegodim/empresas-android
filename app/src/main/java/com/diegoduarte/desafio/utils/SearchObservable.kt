package com.diegoduarte.desafio.utils

import androidx.appcompat.widget.SearchView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeUnit

class SearchObservable {

    // Create a searchView observable
    fun fromView(searchView: SearchView): Observable<String>? {
        val subject = PublishSubject.create<String>()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                subject.onComplete()
                searchView.clearFocus() //if you want to close keyboard
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)
                return false
            }
        })
        return subject
            ?.debounce(100, TimeUnit.MILLISECONDS)
            ?.filter { text -> text.isNotEmpty()}
            ?.map{text -> text.lowercase(Locale.getDefault()).trim()}
            ?.distinctUntilChanged()
    }
}