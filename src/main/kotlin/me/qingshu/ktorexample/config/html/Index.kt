package me.qingshu.ktorexample.config.html

import kotlinx.html.*

fun HTML.index() {
    head {
        link(rel = "stylesheet", href = "/common.css", type = "text/css")
    }
    body {
        h1(classes = "page-title") {
            +"This a template project! Thanks."
        }
    }
}