package me.qingshu.ktorexample.config.html

import kotlinx.html.*

fun HTML.index() {
    head {
        link(rel = "stylesheet", href = "/index_style.css", type = "text/css")
    }
    body {
        background {
            content {
                sidebar()
            }
        }
    }
}

fun BODY.background(block: DIV.() -> Unit) {
    div(classes = "background") {
        block()
    }
}

fun DIV.content(block: DIV.() -> Unit) {
    div(classes = "content") {
        block()
    }
}

fun DIV.sidebar(
    title: String = "Demo.",
    items: List<String> = listOf(
        "Dashboard",
        "Posts",
        "Categories",
        "Media",
        "Users",
        "Settings",
    ),
) {
    div(classes = "sidebar") {
        div(classes = "user-info") {
            div(classes = "user-avatar") {

            }
            div("user-nickname") {
                +title
            }
        }
        // items
        div("menu-items") {
            repeat(items.size) { index ->
                div("menu-item") {
                    +items[index]
                }
            }
        }

        // guide
        div("bottom-card") {
            +"User Guide"
            +"documentation"
        }
    }
}