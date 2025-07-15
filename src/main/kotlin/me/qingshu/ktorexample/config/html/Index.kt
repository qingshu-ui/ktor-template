package me.qingshu.ktorexample.config.html

import kotlinx.html.*

fun HTML.index() {
    lang = "zh-cn"
    head {
        link(rel = "stylesheet", href = "/index_style.css", type = "text/css")
        script { src = "/static/js/chart.js" }
        script { src = "/static/js/visitorChart.js" }
        title { +"Dashboard" }
    }
    body {
        container {
            sidebar()
            main()
        }
    }
}

fun BODY.container(block: DIV.() -> Unit) {
    div("container") {
        block()
    }
}

fun DIV.sidebar(
    username: String = "Username",
    items: List<String> = listOf(
        "Dashboard",
        "Posts",
        "Categories",
        "Media",
        "Users",
        "Settings"
    ),
    selected: Int = 0,
) {
    div("sidebar") {
        div("item") {
            h1 {
                +username
            }
            ul {
                repeat(items.size) {
                    val active = if(selected == it) "active" else null
                    li(active) { +items[it] }
                }
            }
        }
        div("guide") {
            div("icon") {
                img(classes = "closed", src = "/static/images/book_down.svg")
                img(classes = "open", src = "/static/images/open_book.svg")
            }
            div("info") {
                +"User Guide"
                br
                small {
                    +"Documentation"
                }
            }
        }
    }
}

fun DIV.main() {
    div("main")
}

// ---do

/*
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
                    canvas {
                        id = "visitorChart"
                    }
                }
            }
            div("latest-post") {

            }
        }
    }
}

fun HTML.index2() {
    head {
        meta(charset = "UTF-8")
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
        link(rel = "stylesheet", href = "/index_style.css", type = "text/css")
        title("Dashboard")
    }

    body {
        div("container") {
            sidebar()
            main()
        }
    }
}

fun DIV.sidebar(
    userName: String = "Scribble.",
    items: List<String> = listOf(
        "Dashboard",
        "Posts",
        "Categories",
        "Media",
        "Users",
        "Settings",
    ),
    selected: Int = 0,
) {
    div("sidebar") {
        div {
            h1 {
                +userName
            }
            ul {
                repeat(items.size) {
                    val active = if (it == selected) "active" else null
                    ul(active) {
                        +items[it]
                    }
                }
            }
        }
        div("guide") {
            +"User Guide"
            br
            small {
                +"Documentation"
            }
        }
    }
}

fun DIV.main() {
    div("main") {
        div("top-bar") {
            div("title") {
                h2 { +"Dashboard" }
                p { +"Home / Dashboard" }
            }
            div("user") {
                img(src = "https://i.pravatar.cc/40?img=3", alt = "User")
                div {
                    strong { +"Ryan Adhitama" }
                    br
                    small { +"Web Developer" }
                }
            }
        }
        div("grid-layout") {
            div("card") {
                div {
                    +"Posts"
                    strong {
                        +"10"
                    }
                }
                div("icon") {
                    +"📝"
                }
            }
            div("card") {
                div {
                    +"Categories"
                    strong {
                        +"3"
                    }
                }
                div("icon") {
                    +"🔲"
                }
            }
            div("card") {
                div {
                    +"Users"
                    strong {
                        +"2"
                    }
                }
                div("icon") {
                    +"👤"
                }
            }

            div("visitor-growth") {
                h3 { +"Visitor Growth" }
                p { +"Overall Information" }
                div("tabs") {
                    button(classes = "active") { +"Monthly" }
                    button { +"Yearly" }
                }
                div("chart") {
                    div("chart-bar") { style = "height: 50%" }
                    div("chart-bar") { style = "height: 60%" }
                    div("chart-bar") { style = "height: 70%" }
                    div("chart-bar highlight")
                    div("chart-bar") { style = "height: 60%" }
                    div("chart-bar") { style = "height: 50%" }
                    div("chart-bar") { style = "height: 80%" }
                }
            }

            div("latest-posts") {
                h4 { +"Latest Post" }
                div("post") {
                    +"The Power of Dream "
                    br
                    small { +"28 June 2021" }
                }
                div("post") {
                    +"Emotional Healing "
                    br
                    small { +"22 June 2021" }
                }
                div("post") {
                    +"Works vs School "
                    br
                    small { +"22 June 2021" }
                }
            }
        }
    }
}*/
