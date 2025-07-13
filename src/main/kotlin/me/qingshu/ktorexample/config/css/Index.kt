package me.qingshu.ktorexample.config.css

import kotlinx.css.*
import kotlinx.css.properties.Transition
import kotlinx.css.properties.s

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
    }
    ".menu-item:focus" {
        outline = Outline.none
    }
    ".bottom-card" {
        width = 100.pct
        height = 80.px
        backgroundColor = Color("#ffffff15")
        borderRadius = 12.px
        display = Display.flex
        alignItems = Align.center
        justifyContent = JustifyContent.center
        color = Color.white
        fontSize = 11.px
    }

}