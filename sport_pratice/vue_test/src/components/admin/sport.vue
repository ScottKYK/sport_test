<template>
    <h1>目前健身房人數狀態: {{ status }}</h1>
    <div class="demo-progress">
        <el-progress type="circle" :percentage="this.percentage" :color="this.color" />
    </div>
</template>
<script>
export default {
    data() {
        return {
            percentage: 0,
            color: "",
            status:""
        }
    },
    methods: {
        async getPercentage() {
            const { data: res } = await this.$http.post("getPercentage");
            this.percentage = res;
            if (res < 25) {
                this.color = "#6f7ad3";
                this.status = "沒有人潮"
            }
            else if (res > 25 && res < 75) {
                this.color = "#5cb87a"
                this.status = "有點擁擠"
            } else {
                this.color = "#f56c6c"
                this.status = "十分擁擠!!!"
            }
        }
    },
    created() {
        this.getPercentage();
    }
}
</script>

<style></style>