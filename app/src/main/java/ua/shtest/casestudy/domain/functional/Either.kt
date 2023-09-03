package ua.shtest.casestudy.domain.functional

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

sealed class Either<out L, out R> {
    data class Failure<out L>(val a: L) : Either<L, Nothing>()

    data class Success<out R>(val b: R) : Either<Nothing, R>()

    val isSuccess get() = this is Success<R>


    val isFailure get() = this is Failure<L>

    fun <L> failure(a: L) = Failure(a)

    fun <R> success(b: R) = Success(b)

    fun fold(onFailure: (L) -> Any, onSuccess: (R) -> Any): Any =
        when (this) {
            is Failure -> onFailure(a)
            is Success -> onSuccess(b)
        }
}
