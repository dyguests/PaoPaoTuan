package com.fanhl.ppt.util.extensions

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscription
import java.lang.RuntimeException

/**
 * see io/reactivex/rxkotlin/subscribers.kt
 */

private val onSubscribeStub: (Disposable) -> Unit = {}
private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = { throw OnErrorNotImplementedException(it) }
private val onCompleteStub: () -> Unit = {}

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Observable<T>.subscribeBy(
        onSubscribe: (Disposable) -> Unit = onSubscribeStub,
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onNext, onError, onComplete, onSubscribe)

/**
 * Overloaded subscribe function that allow passing named parameters
 */
fun <T : Any> Flowable<T>.subscribeBy(
        onSubscribe: (Subscription) -> Unit = {},
        onNext: (T) -> Unit = onNextStub,
        onError: (Throwable) -> Unit = onErrorStub,
        onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onNext, onError, onComplete, onSubscribe)

///**
// * Overloaded subscribe function that allow passing named parameters
// */
//fun <T : Any> Single<T>.subscribeBy(
//        onSuccess: (T) -> Unit = onNextStub,
//        onError: (Throwable) -> Unit = onErrorStub
//): Disposable = subscribe(onSuccess, onError)
//
///**
// * Overloaded subscribe function that allow passing named parameters
// */
//fun <T : Any> Maybe<T>.subscribeBy(
//        onSuccess: (T) -> Unit = onNextStub,
//        onError: (Throwable) -> Unit = onErrorStub,
//        onComplete: () -> Unit = onCompleteStub
//): Disposable = subscribe(onSuccess, onError, onComplete)
//
///**
// * Overloaded subscribe function that allow passing named parameters
// */
//fun Completable.subscribeBy(
//        onError: (Throwable) -> Unit = onErrorStub,
//        onComplete: () -> Unit = onCompleteStub
//): Disposable = subscribe(onComplete, onError)

class OnErrorNotImplementedException(e: Throwable) : RuntimeException(e)
