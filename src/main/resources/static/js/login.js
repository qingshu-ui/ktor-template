document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('loginForm');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const passwordToggle = document.getElementById('passwordToggle');
    const loginButton = document.getElementById('loginButton');
    const loginText = document.getElementById('loginText');
    const loginSpinner = document.getElementById('loginSpinner');
    const rememberMe = document.getElementById('rememberMe');

    // 密码显示/隐藏切换
    function initPasswordToggles() {
        const toggles = document.querySelectorAll('.password-toggle');

        toggles.forEach(toggle => {
            toggle.addEventListener('click', function() {
                const container = this.closest('.password-container');
                const input = container.querySelector('input');

                if(input) {
                    const type = input.type === 'password' ? 'text' : 'password';
                    input.type = type
                    this.textContent = type === 'password' ? '👁' : '🙈';
                }
            });
        });
    }
    initPasswordToggles();

    // 表单验证
    function validateForm() {
        let isValid = true;

        // 清除之前的错误样式
        usernameInput.classList.remove('input-error');
        passwordInput.classList.remove('input-error');

        // 验证用户名
        if (!usernameInput.value.trim()) {
            usernameInput.classList.add('input-error');
            isValid = false;
        }

        // 验证密码
        if (!passwordInput.value.trim()) {
            passwordInput.classList.add('input-error');
            isValid = false;
        }

        return isValid;
    }

    // 表单提交处理
    if(form) {
        form.addEventListener('submit', function(e) {
            if (!validateForm()) {
                e.preventDefault();
                return;
            }

            // 显示加载状态
            loginButton.disabled = true;
            loginButton.classList.add('loading');
            loginText.style.display = 'none';
            loginSpinner.style.display = 'inline-block';
        });
    }

    // 实时验证
    usernameInput.addEventListener('blur', function() {
        if (!this.value.trim()) {
            this.classList.add('input-error');
        } else {
            this.classList.remove('input-error');
        }
    });

    passwordInput.addEventListener('blur', function() {
        if (!this.value.trim()) {
            this.classList.add('input-error');
        } else {
            this.classList.remove('input-error');
        }
    });

    // 键盘导航支持
    usernameInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            passwordInput.focus();
        }
    });

    passwordInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            form.submit();
        }
    });

    // 保存记住我选项状态到本地存储
    if(rememberMe) {
        rememberMe.addEventListener('change', function(e) {
            localStorage.setItem('rememberMe', this.checked)
        });
        if(rememberMe) {
            rememberMe.checked = localStorage.getItem('rememberMe') === 'true'
        }
    }
});