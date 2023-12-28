<template>
    <h1 v-show="!show">{{ course }}</h1>
    <el-row v-show="show">
        <el-col v-for="item in course" :span="8">
            <el-card :body-style="{ padding: '0px' }" >
                <img v-bind:src="item.path" class="image" />
                <div style="padding: 14px">
                    <span>
                        {{ item.detail }}
                    </span>
                    <el-button type="info" @click="getDetail(item.title)">課程資訊</el-button>
                </div>
            </el-card>
        </el-col>
    </el-row>
    <!-- 打開課程資訊對話框 -->
    <el-dialog v-model="detailDialog" title="課程資訊" width="50%" @close="detailDialogClosed">
        {{ this.detail }}
        <!-- 確認/取消按鈕 -->
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="detailDialogClosed()">Cancel</el-button>
            </span>
        </template>
    </el-dialog>
</template>
<script>
export default {
    data() {
        return {
            userId: "",
            course: [],
            show: true,
            detailDialog: false,
            detail:"",
        }
    },
    methods: {
        async getUserDetail() {
            this.userId = window.sessionStorage.getItem("user");
            const { data: res } = await this.$http.post("getUserCourseById", this.userId);
            this.course = res
            if (this.course.length == 0) {
                this.course = "目前沒有課程"
                this.show = false
                return;
            }
            console.log(this.course);
        },
        async getDetail(title) {
            this.detailDialog = true;
            const { data: res } = await this.$http.post("getDetail", title);
            this.detail = res.detail;
        },
        detailDialogClosed() {
            this.detailDialog = false;
        },
    },
    created() {
        this.getUserDetail();
    }
}
</script>
<style></style>