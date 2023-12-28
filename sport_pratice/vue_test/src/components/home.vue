<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <img src="../assets/sport.jpg" class="sportImage"><img />
                <span>運動管理系統</span>
                <el-button type="info" @click="logout()">登出</el-button>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" :router="true"
                        :default-active="activePath">
                        <el-sub-menu :index="item.path" v-for="item in menuList" :key="item.id">
                            <template #title>
                                <el-icon>
                                    <Promotion />
                                </el-icon>
                                <span>{{ item.title }}</span>
                            </template>
                            <el-menu-item :index="it.path" v-for="it in item.sMenu" :key="item.id"
                                @click="getPath(it.path)">
                                <span>{{ it.title }}</span>
                            </el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </el-aside>
                <el-main><router-view /></el-main>
            </el-container>

        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            menuList: [],
            activePath: ''
        }
    },
    created() {
        this.getMenuList();
        this.activePath = window.sessionStorage.getItem("activePath");
    },
    methods: {
        logout() {
            window.sessionStorage.clear();
            this.$router.push({ path: "/login" });
        },
        async getMenuList() {
            const { data: res } = await this.$http.post("mainMenu");//前端訪問後端連結
            if (res.flag == 'error') {
                this.$message.error("登入失敗");
            }
            this.menuList = res.menu
        },
        getPath(activePath) {
            this.activePath = activePath;
            window.sessionStorage.setItem("activePath", activePath);
        }

    }
}
</script>

<style>
.el-container {
    height: 100%;
}

.el-header {
    background-color: #373d41;
    display: flex;
    justify-content: space-between;
    padding-left: 0%;
    color: #fff;
    font-size: 20px;
    display: flex;
    align-items: center;

    span {
        margin-left: 15px;
    }
}

.el-aside {
    background-color: #373d41;
}

.el-main {
    background-color: #eaedf1;
}

.sportImage {
    width: 55px;
    height: 55px;
}
</style>