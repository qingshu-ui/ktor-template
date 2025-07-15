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
                    val active = if (selected == it) "active" else null
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


data class NavItem(
    val title: String,
    val iconUrl: String,
    val data: Int,
)

data class LatestPost(
    val title: String,
    val date: String,
)

val navItem = listOf(
    NavItem("Posts", "/static/images/edit_rectangle_outline.svg", 10),
    NavItem("Categories", "/static/images/apps_outline.svg", 3),
    NavItem("Users", "/static/images/user_outline.svg", 2),
)

val latestPost = listOf(
    LatestPost("The Power of Dream", "28 June 2021"),
    LatestPost("Emotional Healing", "22 June 2021"),
    LatestPost("Works vs School", "21 June 2021"),
)

fun DIV.main(
    userName: String = "Ryan Adhitama",
    role: String = "Web Developer",
) {
    div("main") {
        div("top-bar") {
            div("title") {
                h2 { +"Dashboard" }
                p { +"Home / Dashboard" }
            }
            div("user") {
                img(alt = "User", src = "https://i.pravatar.cc/40?img=3")
                div {
                    strong { +userName }
                    br
                    small { +role }
                }
            }
        }
        div("grid-layout") {
            repeat(navItem.size) {
                val title = navItem[it].title
                val iconUrl = navItem[it].iconUrl
                val data = navItem[it].data
                div("card") {
                    div {
                        +title
                        br
                        strong { +data }
                    }
                    div("icon") {
                        img(src = iconUrl)
                    }
                }
            }
            div("visitor-growth") {
                div("info") {
                    div {
                        h3 { +"Visitor Growth" }
                        p { +"Overall Information" }
                    }
                    div("tabs") {
                        button(classes = "active") { +"Monthly" }
                        button { +"Yearly" }
                    }
                }
                div("chart") {
                    repeat(22) {
                        div("chart-bar") {
                            style = "height: 50%"
                        }
                    }
                }
            }
            div("latest-posts") {
                h4 { +"Latest Posts" }
                repeat(3) {
                    div("post") {
                        +latestPost[it].title
                        br
                        small { +latestPost[it].date }
                    }
                }
            }
        }
    }
}