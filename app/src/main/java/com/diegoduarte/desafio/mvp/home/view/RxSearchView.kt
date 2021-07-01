package com.diegoduarte.desafio.mvp.home.view

import androidx.appcompat.widget.SearchView
import com.diegoduarte.desafio.base.UseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeUnit

class RxSearchView(private val searchView: SearchView, mThreadExecutor: Scheduler, mPostExecutionThread: Scheduler) :
    UseCase<String, Void>(mThreadExecutor, mPostExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<String>? {
        return buildObservable(searchView)
    }

    private fun buildObservable(searchView: SearchView): Observable<String>? {
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
            ?.debounce(50, TimeUnit.MILLISECONDS)
            ?.filter { text -> text.isNotEmpty()}
            ?.map{text -> text.lowercase(Locale.getDefault()).trim()}
            ?.distinctUntilChanged()
    }
}