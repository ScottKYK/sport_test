<template>
    <div class="common-layout">
        <el-container class="home-container">
            <el-header>
                <img src="../assets/sport.jpg" class="sportImage"><img />
                <span>運動管理系統</span>
                <el-button type="info" @click="logout()">登出</el-button>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
                        <el-sub-menu :index="item.id+''" v-for="item in menuList" :key="item.id">
                            <template #title>
                                <span>{{ item.title }}</span>
                            </template>
                            <el-menu-item :index="it.id+''" v-for="it in item.sList" :key="it.id">
                                <span>{{ it.title }}</span>
                            </el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </el-aside>
                <el-main>
                    main
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
export default {
    data(){
        return {
            menuList:[],
        }
    },
    created() {
        this.getMenuList();
    },
    methods: {
        logout() {
            window.sessionStorage.clear();//清除session
            this.$router.push("/login");//返回首頁
        },
        async getMenuList() {
            const { data: res } = await this.$http.post("menus");
            console.log(res);
            if(res.flag != 200){
                this.$message.error("獲取失敗");
            } 
            this.menuList = res.menus;
        }
    }
}

</script>

<style lang='less' scoped>
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