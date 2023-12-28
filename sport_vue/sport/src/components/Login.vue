<template>
    <div class="login_container">
        <div class="login_box">
            <!-- 圖片 -->
            <div class="avatar_box">
                <img src="../assets/logo.png" alt />
            </div>
            <!-- 登入表單 -->
            <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0">
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" prefix-icon="User" />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" type="password" autocomplete="off" prefix-icon="Unlock" />
                </el-form-item>
                <!-- 登入按鈕 -->
                <el-form-item class="btns">
                    <el-button type="primary" @click="login()">登入</el-button>
                    <el-button type="info" @click="resetLoginForm()">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import { ElButton } from 'element-plus'

export default {
    components: { ElButton },
    data() {
        return {
            loginForm: {
                username: "admin",
                password: "123"
            },
            loginRules: {
                username: [
                    { required: true, message: '請輸入用戶名', trigger: 'blur' },//必須輸入的驗證
                ],
                password: [
                    { required: true, message: '請輸入密碼', trigger: 'blur' },//必須輸入的驗證
                    { min: 3, max: 5, message: '長度3~5', trigger: 'blur' }//長度的驗證
                ]
            }
        }
    },
    methods: {
        resetLoginForm() {
            // 重置表單資訊
            this.$refs.loginFormRef.resetFields();
        },
        // 處理登入
        login() {
            //驗證校驗規則
            this.$refs.loginFormRef.validate(async valid => {
                if (!valid) return;
                // 訪問後台
                const { data: res } = await this.$http.post("login",this.loginForm);//前端訪問後端連結
                console.log(res);
                if(res.flag == "ok"){
                    this.$message.success("登入成功");// 提示訊息
                    this.$router.push({path:"/home"});// 頁面跳轉
                    window.sessionStorage.setItem("user",res.user)
                }else{
                    this.$message.error("登入失敗");
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