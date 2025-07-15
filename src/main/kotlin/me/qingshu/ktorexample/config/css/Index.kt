package me.qingshu.ktorexample.config.css

import kotlinx.css.*
import kotlinx.css.properties.BoxShadow
import kotlinx.css.properties.Transition
import kotlinx.css.properties.s
import kotlinx.css.properties.scale

object Theme {
    val cardBg = Color("#fff7f6")
    val textLight = Color("#aaa")
    val textDark = Color("#333")
    val mainBg = Color("#eacccb")
    val radius = 16.px
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
        padding = Padding(20.px)
        // flexWrap = FlexWrap.wrap
    }
    rule(".sidebar") {
        width = 240.px
        color = Color.white
        background = "linear-gradient(180deg, #f68a7e, #fa7268)"
        borderRadius = Theme.radius
        // margin = Theme.marginToC
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
    rule(".main") {
        display = Display.flex
        flexDirection = FlexDirection.column
        flex = Flex(1)
        backgroundColor = Color.white
        borderRadius = Theme.radius
        padding = Padding(30.px)
        gap = 20.px
        overflow = Overflow.hidden
        minWidth = 0.px

        descendants(".top-bar") {
            display = Display.flex
            justifyContent = JustifyContent.spaceBetween
            alignItems = Align.center
            flexWrap = FlexWrap.wrap
            gap = 16.px
            descendants(".title") {
                descendants("h2") {
                    color = Theme.textDark
                    fontSize = 22.px
                }
                descendants("p") {
                    fontSize = 14.px
                    color = Theme.textLight
                }
            }
            descendants(".user") {
                display = Display.flex
                alignItems = Align.center
                gap = 10.px
                descendants("img") {
                    borderRadius = 50.pct
                    width = 40.px
                    height = 40.px
                }
            }
        }
        descendants(".grid-layout") {
            display = Display.grid
            gridTemplateColumns = GridTemplateColumns.repeat("3, 1fr")
            gap = 20.px
            rowGap = 50.px
            descendants(".card") {
                backgroundColor = Theme.cardBg
                borderRadius = Theme.radius
                padding = Padding(20.px)
                display = Display.flex
                justifyContent = JustifyContent.spaceBetween
                alignItems = Align.center
                color = Theme.textDark
                descendants(".icon") {
                    fontSize = 28.px
                    opacity = 0.5
                }
            }
            descendants(".visitor-growth") {
                gridColumn = GridColumn("span 2")
                backgroundColor = Color.white
                borderRadius = Theme.radius
                //padding = Padding(20.px)
                descendants(".info") {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    justifyContent = JustifyContent.spaceBetween
                    descendants("div") {
                        descendants("h3") {
                            marginBottom = 4.px
                        }
                    }
                    descendants(".tabs") {
                        margin = Margin(10.px, 0.px)
                        descendants("button") {
                            border = Border.none
                            padding = Padding(8.px, 16.px)
                            marginRight = 10.px
                            borderRadius = 20.px
                            backgroundColor = Color("#fce3e1")
                            color = Color("#fa7268")
                            fontWeight = FontWeight.bold
                            cursor = Cursor.pointer
                        }
                        descendants("button.active") {
                            backgroundColor = Color("#fa7268")
                            color = Color.white
                        }
                    }
                }

                descendants(".chart") {
                    marginTop = 10.px
                    height = 120.px
                    display = Display.flex
                    alignItems = Align.flexEnd
                    gap = 6.px
                    descendants(".chart-bar") {
                        width = 10.px
                        backgroundColor = Color("#fa7268")
                        borderRadius = 6.px
                        opacity = 0.3
                        height = 60.pt
                        descendants(".highlight") {
                            opacity = 1
                            height = 90.pct
                        }
                    }
                }
            }
            descendants(".latest-posts") {
                backgroundColor = Color.white
                borderRadius = Theme.radius
                //padding = Padding(20.px)
                descendants("h4") {
                    marginBottom = 10.px
                    backgroundColor = Theme.cardBg
                    display = Display.inlineBlock
                    padding = Padding(8.px, 16.px)
                    borderRadius = Theme.radius
                }
                descendants(".post") {
                    marginTop = 8.px
                    fontSize = 14.px
                    color = Theme.textDark
                    descendants("small") {
                        color = Theme.textLight
                    }
                }
            }
        }
    }

    media("(max-width: 1024px)") {
        rule(".container") {
            flexDirection = FlexDirection.column
            padding = Padding(20.px)
        }
        rule(".sidebar") {
            width = 100.pct
            flexDirection = FlexDirection.row
            alignItems = Align.center
            justifyContent = JustifyContent.spaceBetween
            minHeight = LinearDimension.auto
            paddingLeft = 30.px
            descendants("h1, .guide") {
                display = Display.none
            }
        }
        rule(".main") {
            marginLeft = 0.px
            descendants(".grid-layout") {
                display = Display.flex
                flexDirection = FlexDirection.column
                children {
                    width = 100.pct
                }
                descendants(".visitor-growth") {
                    gridColumn = GridColumn.auto
                }
            }
        }
    }
}