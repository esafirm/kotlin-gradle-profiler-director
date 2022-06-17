package com.github.ajalt.clikt.samples

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate
import com.github.ajalt.clikt.parameters.types.int

fun main(args: Array<String>) = MemoryHeap().main(args)

class MemoryHeap : CliktCommand() {

    private val max by option("--max", help = "max memory heap in giga").int().default(8)
    private val min by option("--min", help = "min memory heap in giga").int().default(2)

    private val step by option("-s", "--step", help = "step from min to max memory heap").int()
        .default(1)
        .validate { it < 0 }

    private val task by option("-t", "--task", help = "task for the scenario").default("assembleDebug")

    private val defaultTemplate: String = """
            {{task_name}} {
                title = "{{task_name}}-Xmx:{{max}}-Xms:{{min}}"
                tasks = ["{{task}}"]
                jvm-args = ["-Xmx{{max}}g", "-Xmx{{min}}g"]
            }
        """.trimIndent()

    override fun run() {
        if (max < min) error("Max heap cannot be less than min heap")

        var scenarioNumber = 1
        for (currentMax in min..max step step) {
            val data = mapOf<String, Any>(
                "max" to currentMax,
                "min" to min,
                "task" to task,
                "task_name" to "benchmark${scenarioNumber}"
            )

            println(defaultTemplate.mustache(data))
            scenarioNumber++
        }
    }

    private fun String.mustache(data: Map<String, Any>): String {
        return data.entries.fold(this) { acc, entry ->
            acc.replace("{{${entry.key}}}", entry.value.toString())
        }
    }
}
