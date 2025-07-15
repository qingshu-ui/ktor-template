package me.qingshu.ktorexample.config.css

import kotlinx.css.*
import kotlinx.css.properties.BoxShadow
import kotlinx.css.properties.Transition
import kotlinx.css.properties.s
import kotlinx.css.properties.scale

object Theme {
    val mainBg = Color("#eacccb")
    val radius = 16.px
    val marginToC = Margin(20.px)
    val boxShadow = BoxShadow(
        color = Color.black.withAlpha(0.2),
        offsetX = 0.px,
        offsetY = 4.px,
        blurRadius = 12.px
    )
}

fun CssBuilder.indexStyle() {
    universal {
        boxSizing = BoxSizing.borderBox
        margin = Margin(0.px)
        padding = Padding(0.px)
    }
    body {
        fontFamily = "Segoe UI, sans-serif"
        backgroundColor = Theme.mainBg
    }
    rule(".container") {
        backgroundColor = Color.white
        minHeight = 100.vh - 40.px
        minWidth = 380.px
        display = Display.flex
        flexDirection = FlexDirection.row
        margin = Margin(20.px)
        borderRadius = 30.px
        boxShadow += Theme.boxShadow
        gap = 20.px
        // flexWrap = FlexWrap.wrap
    }
    rule(".sidebar") {
        width = 240.px
        color = Color.white
        background = "linear-gradient(180deg, #f68a7e, #fa7268)"
        borderRadius = Theme.radius
        margin = Theme.marginToC
        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent = JustifyContent.spaceBetween
        alignItems = Align.center
        paddingBottom = 20.px
        flexShrink = 1
        overflow = Overflow.hidden
        minHeight = 100.pct
        minWidth = 0.px
        wordWrap = WordWrap.breakWord
        overflowWrap = OverflowWrap.breakWord
        wordBreak = WordBreak.breakWord
        descendants(".item") {
            height = 100.pct
            display = Display.flex
            flexDirection = FlexDirection.column
            justifyContent = JustifyContent.spaceEvenly
            descendants("h1") {
                fontSize = 24.px
                cursor = Cursor.pointer
                transition += Transition(duration = 0.3.s)
                hover {
                    transform.scale(1.05)
                }
            }
            descendants("ul") {
                listStyleType = ListStyleType.none
                descendants("li") {
                    margin = Margin(25.px, 0.px)
                    fontWeight = FontWeight.w500
                    opacity = 0.9
                    whiteSpace = WhiteSpace.normal
                    overflow = Overflow.hidden
                    textOverflow = TextOverflow.ellipsis
                    wordBreak = WordBreak.breakWord
                    transition += Transition(duration = 0.3.s)
                    cursor = Cursor.pointer
                    hover {
                        transform.scale(1.05)
                    }
                }
                descendants("li.active") {
                    fontWeight = FontWeight.bold
                }
            }
        }
        descendants(".guide") {
            backgroundColor = rgb(255, 255, 255, 0.2)
            padding = Padding(18.px)
            paddingRight = 50.px
            borderRadius = Theme.radius
            fontSize = 14.px
            display = Display.flex
            flexDirection = FlexDirection.row
            gap = 5.px
            alignItems = Align.center
            transition += Transition(duration = 0.3.s)
            descendants(".icon") {
                display = Display.flex
                alignItems = Align.center
                position = Position.relative
                descendants(".closed") {
                    opacity = 1
                }
                descendants(".open") {
                    opacity = 0
                    position = Position.absolute
                }
                descendants("img") {
                    transition += Transition("opacity", duration = 0.3.s)
                }
            }
            hover {
                transform.scale(1.05)
                descendants(".icon .open") {
                    opacity = 1
                }
                descendants(".icon .closed") {
                    opacity = 0
                }
            }
        }
    }
}


/*fun CssBuilder.indexStyle() {
    "html, body" {
        margin = Margin(0.px)
        padding = Padding(0.px)
        height = 100.vh
        width = 100.vw
        overflow = Overflow.hidden
    }
    ".background" {
        backgroundColor = Color("#FF6A6433")
        width = 100.vw
        height = 100.vh
        display = Display.flex
        justifyContent = JustifyContent.center
        alignItems = Align.center
        padding = Padding(20.px, 40.px)
        boxSizing = BoxSizing.borderBox
    }
    ".content" {
        backgroundColor = Color.white
        width = 100.pct
        height = 100.pct
        borderRadius = 30.px
        display = Display.flex
        padding = Padding(20.px)
        boxSizing = BoxSizing.borderBox
    }
    // sidebar
    ".sidebar" {
        // backgroundColor = Color("#FF6A64")
        background = "linear-gradient(to bottom right, #ffb199f5 0%, #ff6a64d9 50%)"
        width = 225.px
        height = 100.pct
        marginRight = 20.px
        borderRadius = 20.px
        display = Display.flex
        flexDirection = FlexDirection.column
        alignItems = Align.center
        padding = Padding(20.px, 10.px)
        boxSizing = BoxSizing.borderBox
    }
    ".user-info" {
        display = Display.flex
        flexDirection = FlexDirection.column
        alignItems = Align.center
        marginBottom = 30.px
        marginTop = 50.px
    }
    ".user-avatar" {
        width = 70.px
        height = 70.px
        borderRadius = 50.pct
        backgroundColor = Color.white
        marginBottom = 10.px
        background = "cover"
        backgroundPosition = RelativePosition.center
    }
    ".user-nickname" {
        fontSize = 18.px
        color = Color.white
        fontWeight = FontWeight.bold
        textAlign = TextAlign.center
        transition += Transition(duration = 0.3.s)
        cursor = Cursor.pointer
    }
    ".user-nickname:hover" {
        color = Color("#ffffff80")
        transform.scale(1.05)
    }
    ".menu-items" {
        display = Display.flex
        flexDirection = FlexDirection.column
        width = 65.pct
        flex = Flex(1, 1, FlexBasis.auto)
        gap = 15.px
        alignItems = Align.center
        marginTop = 15.px
        minHeight = 0.px
        flexShrink = 1
    }
    ".menu-item" {
        width = 100.pct
        height = 40.px
        color = Color.white
        fontSize = 16.px
        textAlign = TextAlign.left
        cursor = Cursor.pointer
        transition += Transition(duration = 0.3.s)
        outline = Outline.none
        border = Border.none
        whiteSpace = WhiteSpace.nowrap
        textOverflow = TextOverflow.ellipsis
    }
    ".menu-item:hover" {
        // backgroundColor = Color("#ffffff30")
        color = Color("#ffffff80")
        transform.scale(1.05)
    }
    ".menu-item:focus" {
        outline = Outline.none
    }
    ".bottom-card" {
        width = 100.pct
        height = 80.px
        backgroundColor = Color("#ffffff30")
        borderRadius = 12.px
        display = Display.flex
        alignItems = Align.center
        justifyContent = JustifyContent.flexStart
        color = Color.white
        fontSize = 11.px
        cursor = Cursor.pointer
        transition += Transition(duration = 0.3.s)
    }
    ".bottom-card:hover" {
        backgroundColor = Color("#ffffff45")
        transform.translateY((-2).px)
        boxShadow += BoxShadow(
            color = rgb(0, 0, 0, 0.15),
            0.px, 4.px, 12.px
        )
    }
    ".book-icon" {
        width = 36.px
        height = 36.px
        marginRight = 12.px
        position = Position.relative
        flexShrink = 0
        marginLeft = 20.px
    }
    ".book-closed" {
        width = 100.pct
        height = 100.pct
        opacity = 1
        transition += Transition(property = "opacity", duration = 0.3.s)
    }
    ".book-open" {
        width = 100.pct
        height = 100.pct
        opacity = 0
        position = Position.absolute
        top = 0.px
        left = 0.px
        transition += Transition(property = "opacity", duration = 0.3.s)
    }
    ".bottom-card:hover .book-closed" {
        opacity = 0
    }
    ".bottom-card:hover .book-open" {
        opacity = 1
    }
    ".guide-text" {
        display = Display.flex
        flexDirection = FlexDirection.column
        gap = 2.px
    }
    ".guide-title" {
        fontSize = 14.px
        fontWeight = FontWeight.bold
        color = Color.white
        opacity = 0.9
    }
    ".guide-subtitle" {
        fontSize = 12.px
        color = Color.white
        opacity = 0.6
    }
    // dashboard
    ".dashboard" {
        display = Display.flex
        flexDirection = FlexDirection.column
        width = 80.pct
        height = 100.pct
        padding = Padding(20.px)
    }
    ".dashboard-user-info" {
        display = Display.flex
        flexDirection = FlexDirection.row
        justifyContent = JustifyContent.flexEnd
        alignItems = Align.center
        marginBottom = 20.px
        gap = 16.px
    }
    ".dashboard-user-info-avatar" {
        width = 50.px
        height = 50.px
        borderRadius = 50.pct
        backgroundColor = Color.black
        background = "cover"
        backgroundPosition = RelativePosition.center
        flexShrink = 0
    }
    ".dashboard-user-info-content" {
        display = Display.flex
        flexDirection = FlexDirection.column
        alignItems = Align.flexStart
        gap = 4.px
    }
    ".dashboard-user-nickname" {
        fontSize = 14.px
        fontWeight = FontWeight.bold
        color = Color("#333333")
        opacity = 0.8
    }
    ".dashboard-user-role" {
        fontSize = 12.px
        fontWeight = FontWeight.bold
        color = Color("#666666")
        opacity = 0.6
    }
    ".dashboard-info" {
        display = Display.flex
        flexDirection = FlexDirection.column
        alignItems = Align.flexStart
    }
    ".dashboard-title" {
        fontSize = 22.px
        fontWeight = FontWeight.bold
        color = Color("#333333")
        marginBottom = 8.px
    }
    ".dashboard-subtitle" {
        fontSize = 14.px
        color = Color("#666666")
    }
    ".dashboard-grid" {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns(1.fr, 1.fr, 1.fr)
        columnGap = 60.px
        rowGap = 36.px
        width = 100.pct
        height = 100.pct
        margin = Margin(36.px, 0.px)
    }
    ".dashboard-item" {
        padding = Padding(24.px)
        backgroundColor = Color("#FF6A6433")
        borderRadius = 12.px
        boxShadow += BoxShadow(
            color = rgb(0, 0, 0, 0.1),
            0.px, 4.px, 6.px
        )
        border = Border(1.px, BorderStyle.solid, Color("#e5e7eb"))
        minHeight = 120.px
        cursor = Cursor.pointer
        transition += Transition(duration = 0.3.s)
    }
    ".dashboard-item:hover" {
        backgroundColor = Color("#FF6A6466")
        transform.translateY(2.px)
    }
    ".visitor-growth" {
        gridColumn = GridColumn("span 2")
        backgroundColor = Color("#ffffff")
        height = 100.pct
    }
    ".visitor-header" {
        display = Display.flex
        justifyContent = JustifyContent.spaceBetween
        alignItems = Align.center
        marginBottom = 24.px
    }
    ".time-selector" {
        display = Display.flex
        gap = 12.px
        borderRadius = 30.px
        backgroundColor = Color("#f5f5f5")
        padding = Padding(8.px, 16.px)
    }
    ".time-button" {
        padding = Padding(6.px, 10.px)
        borderRadius = 20.px
        fontSize = 14.px
        fontWeight = FontWeight("medium")
        cursor = Cursor.pointer
        color = Color("#666")
        transition += Transition(duration = 0.3.s)
    }
    ".time-button.active" {
        backgroundColor = Color("#ff6a64e8")
        color = Color.white
    }
    ".time-button:hover" {
        transform.scale(1.05)
    }
    ".bar-chart" {
        width = 100.pct
        height = 60.pct
    }
    ".latest-post" {
        // backgroundColor = Color("#ffffff")
    }
}*/

/*
object Theme {
    val mainColor = Color("#fa7268")
    val cardBg = Color("#fff7f6")
    val textDark = Color("#333")
    val textLight = Color("#aaa")
    val radius = 16.px
}

fun CssBuilder.indexStyle() {
    universal {
        boxSizing = BoxSizing.borderBox
        margin = Margin(0.px)
        padding = Padding(0.px)
    }
    body {
        fontFamily = "Segoe UI, sans-serif"
        backgroundColor = Theme.mainColor
    }
    ".container" {
        display = Display.flex
        flexDirection = FlexDirection.row
        minHeight = 100.vh
        padding = Padding(40.px, 20.px)
        gap = 20.px
        flexWrap = FlexWrap.wrap
        minWidth = 380.px
    }
    ".sidebar" {
        width = 240.px
        background = "linear-gradient(180deg, #fa7268, #f68a7e)"
        color = Color.white
        borderRadius = Theme.radius
        padding = Padding(30.px, 20.px)
        display = Display.flex
        flexDirection = FlexDirection.column
        justifyContent = JustifyContent.spaceBetween
        minHeight = 100.pct
        wordWrap = WordWrap.breakWord
        overflowWrap = OverflowWrap.breakWord
        wordBreak = WordBreak.breakWord
        minWidth = 0.px
        flexShrink = 1
        overflow = Overflow.hidden
    }
    ".sidebar h1" {
        fontSize = 24.px
        marginBottom = 40.px
    }
    ".sidebar ul" {
        put("list-style", "none")
    }
    ".sidebar ul li" {
        margin = Margin(20.px, 0.px)
        fontWeight = FontWeight.w500
        opacity = 0.9
        whiteSpace = WhiteSpace.normal
        overflow = Overflow.hidden
        textOverflow = TextOverflow.ellipsis
        wordBreak = WordBreak.breakWord
    }
    ".sidebar ul li.active" {
        fontWeight = FontWeight.bold
    }
    ".sidebar .guide" {
        backgroundColor = rgb(255, 255, 255, 0.1)
        padding = Padding(15.px)
        borderRadius = Theme.radius
        fontSize = 14.px
    }
    ".main" {
        flex = Flex(1)
        backgroundColor = Color.white
        borderRadius = Theme.radius
        padding = Padding(30.px)
        display = Display.flex
        flexDirection = FlexDirection.column
        gap = 20.px
        overflow = Overflow.hidden
        minWidth = 0.px
    }
    ".top-bar" {
        display = Display.flex
        justifyContent = JustifyContent.spaceBetween
        alignItems = Align.center
        flexWrap = FlexWrap.wrap
        gap = 16.px
    }
    ".top-bar .title h2" {
        color = Theme.textDark
        fontSize = 22.px
    }
    ".top-bar .title p" {
        fontSize = 14.px
        color = Theme.textLight
    }
    ".top-bar .user" {
        display = Display.flex
        alignItems = Align.center
        gap = 10.px
    }
    ".top-bar .user img" {
        borderRadius = 50.pct
        width = 40.px
        height = 40.px
    }
    ".grid-layout" {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns.repeat("3, 1fr")
        gap = 20.px
    }
    ".grid-layout .card" {
        backgroundColor = Theme.cardBg
        borderRadius = Theme.radius
        padding = Padding(20.px)
        display = Display.flex
        justifyContent = JustifyContent.spaceBetween
        alignItems = Align.center
        color = Theme.textDark
    }
    ".grid-layout .card .icon" {
        fontSize = 28.px
        opacity = 0.5
    }
    ".grid-layout .visitor-growth" {
        gridColumn = GridColumn("span 2")
        backgroundColor = Color.white
        borderRadius = Theme.radius
        padding = Padding(20.px)
    }
    ".grid-layout .visitor-growth h3" {
        marginBottom = 4.px
    }
    ".tabs" {
        margin = Margin(10.px, 0.px)
    }
    ".tabs button" {
        border = Border.none
        padding = Padding(8.px, 16.px)
        marginRight = 10.px
        borderRadius = 20.px
        backgroundColor = Color("#fce3e1")
        color = Color("#fa7268")
        fontWeight = FontWeight.bold
        cursor = Cursor.pointer
    }
    ".tabs button.active" {
        backgroundColor = Color("#fa7268")
        color = Color.white
    }
    ".chart" {
        marginTop = 10.px
        height = 120.px
        display = Display.flex
        alignItems = Align.flexEnd
        gap = 6.px
    }
    ".chart-bar" {
        width = 10.px
        background = "#fa7268"
        borderRadius = 6.px
        opacity = 0.3
        height = 60.pct
    }
    ".chart-bar.highlight" {
        opacity = 1
        height = 90.pct
    }
    ".grid-layout .latest-posts" {
        background = Color.white.toString()
        borderRadius = Theme.radius
        padding = Padding(20.px)
    }
    ".latest-posts h4" {
        marginBottom = 10.px
        backgroundColor = Theme.cardBg
        display = Display.inlineBlock
        padding = Padding(8.px, 16.px)
        borderRadius = Theme.radius
    }
    ".latest-posts .post" {
        marginTop = 8.px
        fontSize = 14.px
        color = Theme.textDark
    }
    ".latest-posts .post small" {
        color = Theme.textLight
    }
    "@media(max-width: 1024px)" {
        ".container" {
            flexDirection = FlexDirection.column
            padding = Padding(20.px)
        }
        ".sidebar" {
            width = 100.pct
            flexDirection = FlexDirection.row
            alignItems = Align.center
            justifyContent = JustifyContent.spaceBetween
            minHeight = LinearDimension.auto
        }
        ".sidebar h1, .sidebar .guide" {
            display = Display.none
        }
        ".main" {
            marginLeft = 0.px
        }
        ".grid-layout" {
            display = Display.flex
            flexDirection = FlexDirection.column
        }
        ".grid-layout > *" {
            width = 100.pct
        }
        ".grid-layout .visitor-growth" {
            gridColumn = GridColumn.auto
        }
    }
}*/
