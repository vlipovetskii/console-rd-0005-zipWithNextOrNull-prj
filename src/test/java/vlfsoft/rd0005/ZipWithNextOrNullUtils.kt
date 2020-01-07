package vlfsoft.rd0005

private inline fun <T> Iterator<T>.zipWithNextOrNull(action: (Pair<T, T?>) -> Unit) {
    while (true) {
        val first = (if (hasNext()) next() else null) ?: break
        val second = if (hasNext()) next() else null
        action(first to second)
        if (second == null) break
    }
}

/**
 * [zipWithNextOrNull] is based on [zipWithNext]
 */
@Suppress("unused")
fun <T> Sequence<T>.zipWithNextOrNull(): Sequence<Pair<T, T?>> {
    return sequence {
        iterator().zipWithNextOrNull {
            yield(it)
        }
    }
}

/**
 *[zipWithNextOrNullList] is more optimized than, [zipWithNextOrNull].toList()
 */
@Suppress("unused")
fun <T> Sequence<T>.zipWithNextOrNullList(): List<Pair<T, T?>> {
    return mutableListOf<Pair<T, T?>>().apply {
        this@zipWithNextOrNullList.iterator().zipWithNextOrNull {
            add(it)
        }
    }
}