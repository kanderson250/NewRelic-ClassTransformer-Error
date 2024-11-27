package io.ktor.samples.clientmultipart

import java.util.*
import kotlinx.coroutines.*


fun main() = runBlocking {
    launch {
        doWorld(); //throws
//        otherWorld() //does not throw
    }
    println("Hello");
    Thread.sleep(1000000);
}

suspend fun doWorld() {
    println("World!")
}

fun otherWorld() {
    println("World!")
}