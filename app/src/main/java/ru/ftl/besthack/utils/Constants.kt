package ru.ftl.besthack.utils

import org.intellij.lang.annotations.Language
import java.util.regex.Pattern

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

object Constants {
    @Language("RegExp")
    val LOGIN_PATTERN = Pattern.compile("([a-zA-Z0-9]+)")
    const val PASSWORD_MAX_LENGHT = 4
}