package algorithm.baekjoon

/**
 * 단어의 개수
 *
 * - [baekjoon 1152](https://www.acmicpc.net/problem/1152)
 */
fun main() {
    val words = readln().trim().split(" ")
    if (words[0] == "") println(0)
    else println(words.size)
}