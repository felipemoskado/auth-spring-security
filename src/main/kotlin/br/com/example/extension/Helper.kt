package br.com.example.extension

inline fun <T, R> T?.caseSome(block: () -> R): R? =
  if (this != null) {
    block()
  } else {
    null
  }

inline fun <T> T?.caseNone(block: () -> T): T = this ?: block()

fun <T, R : Exception> T?.orThrow(exception: R): T = this ?: throw exception