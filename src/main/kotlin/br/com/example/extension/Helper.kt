package br.com.example.extension

inline fun <T, R> T?.caseSome(block: (T) -> R): R? =
  if (this != null) {
    block(this)
  } else {
    null
  }

inline fun <T> T?.caseNone(block: () -> T): T = this ?: block()

fun <T, R : Exception> T?.orThrow(exception: R): T = this ?: throw exception