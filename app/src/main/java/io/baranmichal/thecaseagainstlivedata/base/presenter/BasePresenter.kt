package io.baranmichal.thecaseagainstlivedata.base.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<View> : ViewModel(), LifecycleObserver {

    private var view: View? = null
    private var viewLifecycle: Lifecycle? = null
    private val disposable = CompositeDisposable()

    @Synchronized
    fun attachView(view: View, viewLifecycle: Lifecycle) {
        this.view = view
        this.viewLifecycle = viewLifecycle

        viewLifecycle.addObserver(this)
        onViewAttached(view)
    }

    protected open fun onViewAttached(view: View) {

    }

    @Synchronized
    protected fun view(): View? {
        return view
    }

    @Synchronized
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        view = null
        viewLifecycle = null
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    protected fun Disposable.autoClear() {
        disposable.add(this)
    }
}
