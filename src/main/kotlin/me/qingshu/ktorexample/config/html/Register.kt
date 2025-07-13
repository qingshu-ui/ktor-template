package me.qingshu.ktorexample.config.html

import kotlinx.html.*

/**
 * 注册页面HTML模板
 */
fun HTML.registerPage(
    errorMessage: String? = null,
    successMessage: String? = null,
    username: String = "",
    email: String = "",
) {
    head {
        title { +"注册 - Ktor模板" }
        meta {
            charset = "UTF-8"
        }
        meta {
            name = "viewport"
            content = "width=device-width, initial-scale=1.0"
        }
        meta {
            name = "description"
            content = "注册Ktor模板应用账户"
        }
        link(rel = "stylesheet", href = "/login.css", type = "text/css")
    }

    body {
        div {
            classes = setOf("login-container")

            div {
                classes = setOf("login-card")

                // Logo区域
                logoArea {
                    title = "创建账户"
                    subtitle = "请填写以下信息完成注册"
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

                // 注册表单
                form {
                    classes = setOf("form")
                    method = FormMethod.post
                    action = "/register"
                    id = "registerForm"

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
                            placeholder = "请输入用户名（3-20个字符）"
                            required = true
                            attributes["autocomplete"] = "username"
                        }
                    }

                    // 邮箱输入
                    div {
                        classes = setOf("form-group")

                        label {
                            classes = setOf("label")
                            htmlFor = "email"
                            +"邮箱"
                        }

                        input {
                            classes = setOf("input")
                            type = InputType.email
                            id = "email"
                            name = "email"
                            value = email
                            placeholder = "请输入邮箱地址"
                            required = true
                            attributes["autocomplete"] = "email"
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
                                placeholder = "请输入密码（至少6个字符）"
                                required = true
                                attributes["autocomplete"] = "new-password"
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

                    // 确认密码输入
                    div {
                        classes = setOf("form-group")

                        label {
                            classes = setOf("label")
                            htmlFor = "confirmPassword"
                            +"确认密码"
                        }

                        div {
                            classes = setOf("password-container")

                            input {
                                classes = setOf("input")
                                type = InputType.password
                                id = "confirmPassword"
                                name = "confirmPassword"
                                placeholder = "请再次输入密码"
                                required = true
                                attributes["autocomplete"] = "new-password"
                            }

                            button {
                                classes = setOf("password-toggle")
                                type = ButtonType.button
                                id = "confirmPasswordToggle"
                                attributes["aria-label"] = "显示/隐藏确认密码"
                                +"👁"
                            }
                        }
                    }

                    // 注册按钮
                    button {
                        classes = setOf("login-button")
                        type = ButtonType.submit
                        id = "registerButton"

                        span {
                            id = "registerText"
                            +"注册"
                        }

                        span {
                            classes = setOf("spinner")
                            id = "registerSpinner"
                            style = "display: none;"
                        }
                    }
                }

                // 登录链接
                div {
                    classes = setOf("register-link")

                    p {
                        classes = setOf("register-text")
                        +"已有账户？"
                    }

                    a {
                        classes = setOf("register-button")
                        href = "/login"
                        +"立即登录"
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