package me.qingshu.ktorexample.config.css

import kotlinx.css.*
import kotlinx.css.properties.*

fun CssBuilder.loginStyles() {
    // 苹果风格的CSS样式字符串
    "*" {
        fontFamily = """
            -apple-system, 
            BlinkMacSystemFont, 
            "Segoe UI", 
            Roboto, 
            "Helvetica Neue", 
            Arial, 
            sans-serif;
        """.trimIndent()
    }
    // 登录页面容器
    ".login-container" {
        display = Display.flex
        justifyContent = JustifyContent.center
        alignItems = Align.center
        minHeight = 100.vh
        backgroundColor = Color("#F2F2F7")
        padding = Padding(20.px)
        boxSizing = BoxSizing.borderBox
    }
    // 登录卡片
    ".login-card" {
        backgroundColor = Color.white
        borderRadius = 12.px
        padding = Padding(40.px)
        width = 100.pct
        maxWidth = 400.px
        boxShadow += BoxShadow(
            color = rgb(0, 0, 0, 0.1),
            offsetX = 0.px,
            offsetY = 2.px,
            blurRadius = 8.px,
        )
        position = Position.relative
        overflow = Overflow.hidden
    }
    // Logo区域
    ".logo-area" {
        textAlign = TextAlign.center
        marginBottom = 32.px
    }
    ".logo" {
        width = 64.px
        height = 64.px
        borderRadius = 16.px
        backgroundColor = Color("#007AFF")
        display = Display.inlineFlex
        justifyContent = JustifyContent.center
        alignItems = Align.center
        color = Color.white
        fontSize = 24.px
        fontWeight = FontWeight.bold
        marginBottom = 16.px
    }
    ".title" {
        fontSize = 28.px
        fontWeight = FontWeight.w600
        color = Color.black
        marginBottom = 8.px
        letterSpacing = (-0.5).px
    }
    ".subtitle" {
        fontSize = 16.px
        color = Color("#8E8E93")
        fontWeight = FontWeight.normal
    }
    // 表单区域
    ".form" {
        marginTop = 32.px
    }
    ".form-group" {
        marginBottom = 20.px
    }
    ".label" {
        display = Display.block
        fontSize = 14.px
        fontWeight = FontWeight.w500
        color = Color.black
        marginBottom = 8.px
    }
    ".input" {
        width = 100.pct
        padding = Padding(16.px)
        fontSize = 16.px
        border = Border(1.px, BorderStyle.solid, Color("#C7C7CC"))
        borderRadius = 8.px
        backgroundColor = Color.white
        color = Color.black
        boxSizing = BoxSizing.borderBox
        transition += Transition(duration = 0.2.s)
        outline = Outline.none
    }
    ".input:focus" {
        borderColor = Color("#007AFF")
        boxShadow += BoxShadow(
            color = rgb(0, 122, 255, 0.1),
            offsetX = 0.px,
            offsetY = 0.px,
            blurRadius = 0.px,
            spreadRadius = 3.px
        )
    }
    ".input-error" {
        borderColor = Color("#FF3B30")
    }
    ".input-error:focus" {
        borderColor = Color("#FF3B30")
        boxShadow += BoxShadow(
            color = rgb(255, 59, 48, 0.1),
            offsetX = 0.px,
            offsetY = 0.px,
            blurRadius = 0.px,
            spreadRadius = 3.px
        )
    }
    // 密码输入框容器
    ".password-container" {
        position = Position.relative
    }
    ".password-toggle" {
        position = Position.absolute
        right = 16.px
        top = 50.pct
        transform.translateY((-50).pct)
        background = "none"
        border = Border.none
        color = Color("#8E8E93")
        cursor = Cursor.pointer
        fontSize = 16.px
        padding = Padding(4.px)
        borderRadius = 4.px
        transition += Transition("color", 0.2.s)
    }
    ".password-toggle:hover" {
        color = Color.black
    }
    // 记住我选项
    ".remember-me" {
        display = Display.flex
        alignItems = Align.center
        marginBottom = 24.px
        cursor = Cursor.pointer
    }
    ".checkbox" {
        width = 20.px
        height = 20.px
        marginRight = 12.px
        setCustomProperty("accent-color", Color("#007AFF"))
    }
    ".checkbox-label" {
        fontSize = 14.px
        color = Color("#8E8E93")
        userSelect = UserSelect.none
    }
    // 登录按钮
    ".login-button" {
        width = 100.pct
        padding = Padding(16.px)
        fontSize = 16.px
        fontWeight = FontWeight.w600
        color = Color.white
        backgroundColor = Color("#007AFF")
        border = Border.none
        borderRadius = 8.px
        cursor = Cursor.pointer
        transition += Transition(duration = 0.2.s)
        boxShadow += BoxShadow(
            color = rgb(0, 122, 255, 0.3),
            offsetX = 0.px,
            offsetY = 1.px,
            blurRadius = 3.px
        )
    }
    ".login-button:hover" {
        backgroundColor = Color("#0056CC")
        transform.scale(1.02)
    }
    ".login-button:active" {
        transform.scale(0.98)
    }
    ".login-button:disabled" {
        backgroundColor = Color("#B0B0B0")
        cursor = Cursor.notAllowed
        transform.clear()
        boxShadow.clear()
    }
    // 加载状态
    ".loading" {
        opacity = 0.7
        pointerEvents = PointerEvents.none
    }
    ".spinner" {
        display = Display.inlineBlock
        width = 16.px
        height = 16.px
        border = Border(2.px, BorderStyle.solid, rgb(255, 255, 255, 0.3))
        borderTop = Border(2.px, BorderStyle.solid, Color.white)
        borderRadius = 50.pct
        animation += Animation("spin", 1.s, timing = Timing.linear, iterationCount = IterationCount.infinite)
    }
    keyframes("spin") {
        from {
            transform.rotate(0.deg)
        }
        to {
            transform.rotate(360.deg)
        }
    }
    // 错误消息
    ".error-message" {
        backgroundColor = Color("#FFE5E5")
        color = Color("#FF3B30")
        padding = Padding(12.px)
        borderRadius = 8.px
        fontSize = 14.px
        marginTop = 20.px
        border = Border(1.px, BorderStyle.solid, Color("#ffcccc"))
    }
    // 成功消息
    ".success-message" {
        backgroundColor = Color("#E5F9E5")
        color = Color("#28A745")
        padding = Padding(12.px)
        borderRadius = 8.px
        fontSize = 14.px
        marginBottom = 20.px
        border = Border(1.px, BorderStyle.solid, Color("#CCE5CC"))
    }
    // 注册链接
    ".register-link" {
        textAlign = TextAlign.center
        marginTop = 24.px
        paddingTop = 24.px
        borderTop = Border(1.px, BorderStyle.solid, Color("#E5E5E7"))
    }
    ".register-text" {
        fontSize = 14.px
        color = Color("#8E8E93")
        marginBottom = 8.px
    }
    ".register-button" {
        color = Color("#007AFF")
        textDecoration = TextDecoration.none
        fontSize = 14.px
        fontWeight = FontWeight.w500
        transition += Transition("color", duration = 0.2.s)
    }
    ".register-button:hover" {
        color = Color("#0056CC")
        textDecoration = TextDecoration(setOf(TextDecorationLine.underline))
    }
    // 响应式设计
    "@media max-width: 480px" {
        ".login-card" {
            padding = Padding(24.px)
            margin = Margin(16.px)
        }
        ".title" {
            fontSize = 24.px
        }
        ".input" {
            padding = Padding(14.px)
            fontSize = 16.px
        }
        ".login-button" {
            padding = Padding(14.px)
            fontSize = 16.px
        }
    }
}