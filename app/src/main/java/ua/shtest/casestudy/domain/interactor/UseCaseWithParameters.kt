package ua.shtest.casestudy.domain.interactor

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.shtest.casestudy.domain.functional.Either

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

abstract class UseCaseWithParameters<in P, T> where P : Any, T : Any {

    private val disposables = CompositeDisposable()

    abstract fun execute(params: P): Observable<T>

    operator fun invoke(params: P, onResult: (Either<Throwable, T>) -> Unit = {}) {
        val observable = execute(params).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
        disposables.add(observable.subscribeWith(createDisposableObserverWith(onResult)))
    }

    private fun createDisposableObserverWith(onResult: (Either<Throwable, T>) -> Unit): DisposableObserver<T> =
        object : DisposableObserver<T>() {
            override fun onNext(result: T) {
                onResult(Either.Success(result))
            }

            override fun onError(e: Throwable) {
                onResult(Either.Failure(e))
            }

            override fun onComplete() {
                // empty
            }
        }
}