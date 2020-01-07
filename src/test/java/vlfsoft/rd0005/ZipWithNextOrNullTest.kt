package vlfsoft.rd0005

import org.amshove.kluent.`should equal`
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.TestFactory
import vlfsoft.rd0003.kTestFactory

class ZipWithNextOrNullTest {

    @TestFactory
    fun testFactory() = kTestFactory {

        "empty" {
            sequenceOf<Int>().zipWithNextOrNull().count() `should equal` 0
        }

        "1" {
            sequenceOf(1).zipWithNextOrNull().run {
                count() `should equal` 1
                last().second.shouldBeNull()
                assertIterableEquals(listOf(1 to null), asIterable())
            }
        }

        "1, 2" {
            sequenceOf(1, 2).zipWithNextOrNull().run {
                count() `should equal` 1
                last().second.shouldNotBeNull()
                assertIterableEquals(listOf(1 to 2), asIterable())
            }
        }

        "1, 2, 3" {
            sequenceOf(1, 2, 3).zipWithNextOrNull().run {
                count() `should equal` 2
                last().second.shouldBeNull()
                assertIterableEquals(listOf(1 to 2, 3 to null), asIterable())
            }
        }

        "1, 2, 3, 4" {
            sequenceOf(1, 2, 3, 4).zipWithNextOrNull().run {
                count() `should equal` 2
                last().second.shouldNotBeNull()
                assertIterableEquals(listOf(1 to 2, 3 to 4), asIterable())
            }
        }

    }

}