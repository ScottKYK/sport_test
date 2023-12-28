<template>
    <div class="login_container">
        <div class="login_box">
            <!-- 圖片 -->
            <div class="avatar_box">
                <img src="../assets/logo.png" alt />
            </div>
            
            <el-form ref="loginFormRef" :model="loginForm" label-width="70px" class="login_form" :rules="loginRule" >
                <!-- 帳號 -->
                <el-form-item label="帳號" prop="username">
                    <el-input placeholder="帳號" v-model="loginForm.username" type="text"></el-input>
                </el-form-item>
                <!-- 密碼 -->
                <el-form-item label="密碼" prop="password">
                    <el-input placeholder="密碼" v-model="loginForm.password" type="password" />
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="login()">Submit</el-button>
                    <el-button type="info" @click="resetInfo()">Reset</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            loginForm: {
                username: "admin",
                password: "123"
            },
            loginRule: {
                username: [{
                    required: true, message: "請輸入帳號", trigger: 'blur'
                }],
                password: [{
                    required: true, message: "請輸入密碼", trigger: 'blur'
                }]
            }
        }
    },
    methods: {
        resetInfo() {
            this.$refs.loginFormRef.resetFields()
        },
        login() {
            // valid 驗證form表單的內容是否符合rule
            this.$refs.loginFormRef.validate(async valid => {
                if (!valid) return;
                // 後端訪問的請求進行連結,並獲取返回數據data 解析為 res(需加async解析)
                const { data: res } = await this.$http.post("login", this.loginForm)
                if (res.flag == "ok") {//判斷res結果
                    this.$message.success("登入成功"); //提示登入成功
                    this.$router.push({ path: "/home" }) //跳轉頁面
                    window.sessionStorage.setItem("user", res.user.id) //將user對象存在網頁session中
                } else {
                    this.$message.error("登入失敗");//提示登入失敗
                }
            })
        }
    }
}
</script>

<style lang="less" scoped>
.login_container {
    background-color: #0ee;
    height: 100%;
}

.login_box {
    width: 450px;
    height: 300px;
    background-color: #eee;
    border-radius: 5px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

    .avatar_box {
        width: 130px;
        height: 130px;
        border: 1px solid #eee;
        border-radius: 50%;
        padding: 5px;
        box-shadow: 0 0 2px #ddd;
        position: absolute;
        left: 50%;
        transform: translate(-50%, -50%);
        background: #0ee;

        img {
            width: 100%;
            height: 100%;
            border-radius: 50%;
            background-color: #eee;
        }
    }
}

.btns {
    display: flex;
    justify-content: flex-end;
}

.login_form {
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
}
</style>