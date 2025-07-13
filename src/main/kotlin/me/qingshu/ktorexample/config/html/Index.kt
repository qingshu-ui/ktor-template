package me.qingshu.ktorexample.config.html

import kotlinx.html.*

fun HTML.index(username: String = "", email: String = "") {
    head {
        link(rel = "stylesheet", href = "/common.css", type = "text/css")
    }
    body {
        h1(classes = "page-title") {
            +"This a template project! Thanks."
        }
        if (username.isNotEmpty()) {
            p(classes = "subtitle") {
                +username
            }
        }
        if (email.isNotEmpty()) {
            p("subtitle2") {
                +email
            }
        }
    }
}