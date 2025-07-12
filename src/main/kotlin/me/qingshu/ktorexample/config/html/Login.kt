package me.qingshu.ktorexample.config.html

import kotlinx.html.*

/**
 * 登录页面HTML模板
 */
fun HTML.loginPage(
    errorMessage: String? = null,
    successMessage: String? = null,
    username: String = "",
    rememberMe: Boolean = false,
) {
    head {
        title { +"登录 - Ktor模板" }
        meta {
            charset = "UTF-8"
        }
        meta {
            name = "viewport"
            content = "width=device-width, initial-scale=1.0"
        }
        meta {
            name = "description"
            content = "登录到Ktor模板应用"
        }
        link(rel = "stylesheet", href = "/login.css", type = "text/css")
    }

    body {
        div {
            classes = setOf("login-container")

            div {
                classes = setOf("login-card")

                // Logo区域
                div {
                    classes = setOf("logo-area")

                    div {
                        classes = setOf("logo")
                        +"K"
                    }

                    h1 {
                        classes = setOf("title")
                        +"欢迎回来"
                    }

                    p {
                        classes = setOf("subtitle")
                        +"请登录您的账户"
                    }
                }

                // 错误消息
                errorMessage?.let { message ->
                    div {
                        classes = setOf("error-message")
                        +message
                    }
                }

                // 成功消息
                successMessage?.let { message ->
                    div {
                        classes = setOf("success-message")
                        +message
                    }
                }

                // 登录表单
                form {
                    classes = setOf("form")
                    method = FormMethod.post
                    action = "/login"
                    id = "loginForm"

                    // 用户名输入
                    div {
                        classes = setOf("form-group")

                        label {
                            classes = setOf("label")
                            htmlFor = "username"
                            +"用户名"
                        }

                        input {
                            classes = setOf("input")
                            type = InputType.text
                            id = "username"
                            name = "username"
                            value = username
                            placeholder = "请输入用户名"
                            required = true
                            attributes["autocomplete"] = "username"
                        }
                    }

                    // 密码输入
                    div {
                        classes = setOf("form-group")

                        label {
                            classes = setOf("label")
                            htmlFor = "password"
                            +"密码"
                        }

                        div {
                            classes = setOf("password-container")

                            input {
                                classes = setOf("input")
                                type = InputType.password
                                id = "password"
                                name = "password"
                                placeholder = "请输入密码"
                                required = true
                                attributes["autocomplete"] = "current-password"
                            }

                            button {
                                classes = setOf("password-toggle")
                                type = ButtonType.button
                                id = "passwordToggle"
                                attributes["aria-label"] = "显示/隐藏密码"
                                +"👁"
                            }
                        }
                    }

                    // 记住我选项
                    div {
                        classes = setOf("remember-me")

                        input {
                            classes = setOf("checkbox")
                            type = InputType.checkBox
                            id = "rememberMe"
                            name = "rememberMe"
                            if (rememberMe) {
                                checked = true
                            }
                        }

                        label {
                            classes = setOf("checkbox-label")
                            htmlFor = "rememberMe"
                            +"记住我"
                        }
                    }

                    // 登录按钮
                    button {
                        classes = setOf("login-button")
                        type = ButtonType.submit
                        id = "loginButton"

                        span {
                            id = "loginText"
                            +"登录"
                        }

                        span {
                            classes = setOf("spinner")
                            id = "loginSpinner"
                            style = "display: none;"
                        }
                    }
                }

                // 注册链接
                div {
                    classes = setOf("register-link")

                    p {
                        classes = setOf("register-text")
                        +"还没有账户？"
                    }

                    a {
                        classes = setOf("register-button")
                        href = "/register"
                        +"立即注册"
                    }
                }
            }
        }

        // JavaScript交互
        script {
            src = "/static/js/login.js"
        }
    }
} 