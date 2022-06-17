package nolambda.stream.profilerdirector

import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.samples.MemoryHeap
import com.github.ajalt.clikt.samples.ProfilerDirector

fun main(args: Array<String>) = ProfilerDirector().subcommands(MemoryHeap()).main(args)
