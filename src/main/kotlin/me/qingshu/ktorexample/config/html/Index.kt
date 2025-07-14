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
                dashBoard()
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
            div("book-icon") {
                img(classes = "book-closed") {
                    src = "/static/images/book_down.svg"
                    alt = "Closed book"
                }
                img(classes = "book-open") {
                    src = "/static/images/open_book.svg"
                    alt = "Open book"
                }
            }
            div("guide-text") {
                div("guide-title") {
                    +"User Guide"
                }
                div("guide-subtitle") {
                    +"documentation"
                }
            }
        }
    }
}

fun DIV.dashBoard() {
    div("dashboard") {
        div("dashboard-user-info") {
            div("dashboard-user-info-avatar") {

            }
            div("dashboard-user-info-content") {
                div("dashboard-user-nickname") {
                    +"用户昵称"
                }
                div("dashboard-user-role") {
                    +"用户角色"
                }
            }
        }

        div("dashboard-info") {
            div("dashboard-title") {
                +"Dashboard"
            }
            div("dashboard-subtitle") {
                +"Home / Dashboard"
            }
        }

        div("dashboard-grid") {
            div("dashboard-item")
            div("dashboard-item")
            div("dashboard-item")

            div("visitor-growth") {
                div("visitor-header") {
                    div("dashboard-info") {
                        div("dashboard-title") {
                            +"Visitor Growth"
                        }
                        div("dashboard-subtitle") {
                            +"Overall Information"
                        }
                    }
                    div("time-selector") {
                        div("time-button active") {
                            +"Monthly"
                        }
                        div("time-button") {
                            +"Yearly"
                        }
                    }
                }
                div("bar-chart") {

                }
            }
            div("latest-post") {

            }
        }
    }
}