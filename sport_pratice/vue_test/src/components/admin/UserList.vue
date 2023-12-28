<template>
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首頁</el-breadcrumb-item>
        <el-breadcrumb-item>權限管理</el-breadcrumb-item>
        <el-breadcrumb-item>用戶列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 列表主體 -->
    <el-card>
        <!-- 搜索欄 -->
        <el-row :gutter="25">
            <el-col :span="10">
                <el-input placeholder="請輸入搜索內容" v-model="queryInfo.username" clearable @clear="getUserList">
                    <template #append>
                        <el-button type="info" @click="getUserList">
                            <el-icon style="vertical-align: middle">
                                <Search />
                            </el-icon>
                        </el-button>
                    </template>

                </el-input>
            </el-col>
            <el-col :span="1">
                <el-button type="primary" @click="addUserDialog = true">
                    <span style="vertical-align: middle">添加</span>
                </el-button>
            </el-col>
        </el-row>
        <!-- 列表欄 -->
        <el-table :data="userList" stripe>
            <el-table-column label="id" prop="id" width="50px"></el-table-column>
            <el-table-column label="用戶" prop="username"></el-table-column>
            <el-table-column label="郵箱" prop="email"></el-table-column>
            <el-table-column label="密碼" prop="password"></el-table-column>
            <el-table-column label="腳色" prop="role"></el-table-column>
            <el-table-column label="狀態" prop="state">
                <template v-slot="{ row }">
                    <el-switch v-model="row.state" :active-value="true" :inactive-value="false"
                        @change="userStateChange(row)" />
                </template>
            </el-table-column>
            <el-table-column label="操作" v-slot="{ row }">
                <el-button type="primary" @click="openEditDialog(row.id)">
                    <el-icon>
                        <Edit />
                    </el-icon>
                </el-button>
                <el-button type="danger" @click="deleteUser(row.username)">
                    <el-icon>
                        <Delete />
                    </el-icon>
                </el-button>
                <el-button type="info">
                    <el-icon>
                        <Tools />
                    </el-icon>
                </el-button>
            </el-table-column>
        </el-table>
        <!-- 分頁 -->
        <el-pagination v-model:current-page="queryInfo.PageNum" v-model:page-size="queryInfo.PageSize"
            :page-sizes="[1, 2, 5, 10]" layout="sizes, prev, pager, next" :total="total" @size-change="handleSizeChange"
            @current-change="handleCurrentChange" />
    </el-card>

    <!-- 新增用戶頁面 -->
    <el-dialog v-model="addUserDialog" title="添加" width="50%" @close="addDialogClosed">

        <el-form ref="addFormRef" :rules="addRule" :model="addForm" label-width="70px">
            <!-- 帳號 -->
            <el-form-item label="帳號" prop="username">
                <el-input placeholder="帳號" v-model="addForm.username" type="text" />
            </el-form-item>
            <!-- 密碼 -->
            <el-form-item label="密碼" prop="password">
                <el-input placeholder="密碼" v-model="addForm.password" type="text" />
            </el-form-item>
            <!-- email -->
            <el-form-item label="email" prop="email">
                <el-input placeholder="email" v-model="addForm.email" type="text" />
            </el-form-item>
        </el-form>

        <!-- 確認/取消按鈕 -->
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="addDialogClosed()">Cancel</el-button>
                <el-button type="primary" @click="addUser()">
                    Confirm
                </el-button>
            </span>
        </template>
    </el-dialog>

    <!-- 修改對話框 -->
    <el-dialog v-model="editUserDialog" title="修改" width="50%" @close="editDialogClosed">

        <el-form ref="editFormRef" :rules="editRule" :model="editForm" label-width="70px">
            <!-- 帳號 -->
            <el-form-item label="帳號" prop="username">
                <el-input placeholder="帳號" v-model="editForm.username" type="text" disabled />
            </el-form-item>
            <!-- 密碼 -->
            <el-form-item label="密碼" prop="password">
                <el-input placeholder="密碼" v-model="editForm.password" type="text" />
            </el-form-item>
            <!-- email -->
            <el-form-item label="email" prop="email">
                <el-input placeholder="email" v-model="editForm.email" type="text" />
            </el-form-item>
        </el-form>
        <!-- 確認/取消按鈕 -->
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="editDialogClosed()">Cancel</el-button>
                <el-button type="primary" @click="editUser()">Confirm</el-button>
            </span>
        </template>
    </el-dialog>
</template>
<script>
export default {
    data() {
        return {
            queryInfo: {
                username: "",
                PageNum: 1,
                PageSize: 5,
            },
            userList: [],
            total: 0,
            username: '',
            addUserDialog: false,
            editUserDialog: false,
            addForm: {
                id: "",
                username: "",
                password: "",
                email: ""
            },
            editForm: {
                id: "",
                username: "",
                password: "",
                email: ""
            },
            addRule: {
                username: [{
                    required: true, message: "請輸入帳號", trigger: 'blur'
                }],
                password: [{
                    required: true, message: "請輸入密碼", trigger: 'blur'
                }],
                email: [{
                    required: true, message: "請輸入email", trigger: 'blur'
                }]
            },
            editRule: {
                password: [{
                    required: true, message: "請輸入密碼", trigger: 'blur'
                }],
                email: [{
                    required: true, message: "請輸入email", trigger: 'blur'
                }]
            },
        }
    },
    methods: {
        async getUserList() {
            const { data: res } = await this.$http.post("getUser", { queryInfo: this.queryInfo });
            console.log(res);
            this.userList = res.userList;
            this.total = res.numbers;
        },
        handleSizeChange(newSize) {
            this.queryInfo.PageSize = newSize;
            this.getUserList();
        },
        handleCurrentChange(newPage) {
            this.queryInfo.PageNum = newPage;
            this.getUserList();
        },
        addDialogClosed() {
            this.$refs.addFormRef.resetFields();
            this.addUserDialog = false;
        },
        addUser() {
            this.$refs.addFormRef.validate(async valid => {
                if (!valid) return;
                console.log(valid);
                const { data: res } = await this.$http.post("addUser", this.addForm);
                if (res != "success") {
                    return this.$message.error("失敗");
                }
                this.$message.success("成功");
                this.addUserDialog = false;
                this.getUserList();
            })
        },
        editDialogClosed() {
            this.$refs.editFormRef.resetFields();
            this.editUserDialog = false;
        },
        editUser() {
            this.$refs.editFormRef.validate(async valid => {
                if (!valid) return;
                console.log(valid);
                this.$http.post("updateUser", this.editForm)
            })
        },
        async deleteUser(username) {
            const confirmResult = await this.$confirm('確認刪除此用戶?', '提醒', {
                confirmButtonText: '確定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err => err)
            if (confirmResult != 'confirm') {
                return this.$message.info("取消刪除");
            }
            this.username = username;
            const { data: res } = await this.$http.post("deleteUser", this.username);
            if (res != 'success') {
                return this.$error.message("操作錯誤");
            }
            this.$message.success("刪除成功");
            this.getUserList();
        },
        async openEditDialog(id) {
            const { data: res } = await this.$http.post("getAUser", id);
            this.editForm = res;
            this.editUserDialog = true;
        },
        editUser() {
            this.$refs.editFormRef.validate(async valid => {
                if (!valid) return;
                const { data: res } = await this.$http.post("updateUser", this.editForm)
                if (res != "success") {
                    this.$message.error("失敗")
                }
                this.$message.success("成功");
                this.editUserDialog = false;
                this.getUserList();
            })
        },
        async userStateChange(userQuery) {
            const { data: res } = await this.$http.post("updateState", userQuery);
            if (res != "success") {
                this.$message.error("失敗")
            }
            this.$message.success("成功");
            this.getUserList();
        }

    },
    created() {
        this.getUserList();
    }
}
</script>
<style>
.el-breadcrumb {
    margin-bottom: 15px;
    font-size: 12px;
}
</style>