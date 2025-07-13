package me.qingshu.ktorexample.config.html

import kotlinx.html.*

class LogoAreaScope {
    var title = ""
    var subtitle = ""
}

fun DIV.logoArea(block: LogoAreaScope.() -> Unit) {
    val config = LogoAreaScope().apply(block)
    div {
        classes = setOf("logo-area")

        div {
            classes = setOf("logo")
            +"K"
        }
        h1 {
            classes = setOf("title")
            +config.title
        }
        p {
            classes = setOf("subtitle")
            +config.subtitle
        }
    }
}