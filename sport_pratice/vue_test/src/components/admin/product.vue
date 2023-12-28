<template>
    <el-row>
        <el-col v-for="item in courseData" :span="8">
            <el-card :body-style="{ padding: '0px' }" v-model="courseData">
                <img v-bind:src="item.path" class="image" />
                <div style="padding: 14px">
                    <span>
                        {{ item.title }}
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
            courseData: {},
            detailDialog: false,
            detail: "",
        }
    },
    methods: {
        async getCourse() {
            const { data: res } = await this.$http.post("getCourse");
            console.log(res);
            this.courseData = res;
            console.log(this.courseData);
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
        this.getCourse();
    }
}
</script>
<style>
.time {
    font-size: 12px;
    color: #999;
}

.bottom {
    margin-top: 13px;
    line-height: 12px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.button {
    padding: 0;
    min-height: auto;
}

.image {
    width: 250px;
    height: 200px;
    display: block;
}
</style>