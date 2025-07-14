package me.qingshu.ktorexample.config.css

import kotlinx.css.*
import kotlinx.css.properties.BoxShadow
import kotlinx.css.properties.Transition
import kotlinx.css.properties.s
import kotlinx.css.properties.scale
import kotlinx.css.properties.scaleX
import kotlinx.css.properties.scaleY
import kotlinx.css.properties.translate3d
import kotlinx.css.properties.translateY

fun CssBuilder.indexStyle() {
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
    ".latest-post" {
        // backgroundColor = Color("#ffffff")
    }
}