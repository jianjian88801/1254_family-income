<template>
    <div>
        <Xheader title="Other" imgurl="other1.png"></Xheader>
            <img :src="addPostImg" class="addPostkz"/>
        <b-container class="p-1">
            <b-card no-body>
                <b-card-header class="bgthemeColor textthemeColor fsize1"><img :src="articleIMg" class="imgkz"/>短句精选
                </b-card-header>
                <b-card-body>
                    <b-list-group>
                        <b-list-group-item>
                            <b-badge class="mr-1" variant="danger">New</b-badge>
                            test 此模块功能正在开发中，暂未上线
                        </b-list-group-item>
                        <b-list-group-item>
                            <b-badge class="mr-1" variant="danger">New</b-badge>
                            test 此模块功能正在开发中，暂未上线
                        </b-list-group-item>
                    </b-list-group>
                </b-card-body>
            </b-card>
            <b-card no-body>
                <b-card-header class="bgthemeColor textthemeColor fsize1"><img :src="kjImg" class="imgkz"/>动态中心
                </b-card-header>
                <b-card-body>
                    <b-list-group>
                        <b-list-group-item>
                            <el-row :gutter="5">
                                <el-col :span="4">
                                    <img :src="txImg" class="txkz"/>
                                </el-col>
                                <el-col :span="20" class="linehkz">
                                    <div class="mb-1">刘兆新lzx</div>
                                    <small class="text-muted">2020-12-12</small>
                                </el-col>
                            </el-row>
                            <p class="pt-2 pl-1 mb-0">人无知，神无所思</p>
                            <div class="row row-cols-2 p-2">
                            <!--图片组-->
                                <div class="col dtimgkz"><img :src="img1" @click="viewImg"/></div>
                            </div>
                            <div class="text-right mt-2">
                                <b-button-group size="sm">
                                    <b-button variant="outline-secondary" class="font">
                                        <b-icon icon="hand-thumbs-up" class="r2px"></b-icon>0
                                    </b-button>
                                    <b-button variant="outline-secondary">
                                        <b-icon icon="calendar" class="r2px"></b-icon>
                                        2020-01-23
                                    </b-button>
                                </b-button-group>
                            </div>
                        </b-list-group-item>
                        <b-list-group-item>
                            <el-row :gutter="5">
                                <el-col :span="4">
                                    <img :src="txImg" class="txkz"/>
                                </el-col>
                                <el-col :span="20" class="linehkz">
                                    <div class="mb-1">刘兆新lzx</div>
                                    <small class="text-muted">2020-12-12</small>
                                </el-col>
                            </el-row>
                            <p class="pt-2 pl-1 mb-0">人无知，神无所思</p>
                            <div class="row row-cols-2 p-2">
                            <!--图片组-->
                                <div class="col dtimgkz"><img :src="img1" @click="viewImg"/></div>
                            </div>
                            <div class="text-right mt-2">
                                <b-button-group size="sm">
                                    <b-button variant="outline-secondary" class="font">
                                        <b-icon icon="hand-thumbs-up" class="r2px"></b-icon>0
                                    </b-button>
                                    <b-button variant="outline-secondary">
                                        <b-icon icon="calendar" class="r2px"></b-icon>
                                        2020-01-23
                                    </b-button>
                                </b-button-group>
                            </div>
                        </b-list-group-item>
                    </b-list-group>
                </b-card-body>
            </b-card>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: "Other",
        data() {
            return {
                articleIMg: require('../assets/img/article.png'),
                kjImg: require('../assets/img/kj.png'),
                txImg: require('../assets/img/tx.jpg'),
                img1: require('../assets/img/test/1.jpg'),
                addPostImg: require('../assets/img/add.png'),
                tz: {
                    name: 'Img',
                    params: {
                        imgurl: this.img1
                    }
                }
            }
        },
        created() {
            this.toast("此模块暂未开发中暂未上线", 'warning')
        },
        methods: {
            toast: function (msg, type) {
                this.$message({
                    message: msg,
                    type: type,
                    duration: 2000
                });
            },
            viewImg: function () {
                var obj = window.event.target
                var imgurl = obj.src
                this.$router.push({
                    name: 'Img',
                    params: {
                        imgurl: imgurl
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .imgkz {
        width: 25px;
        padding: 3px;
        margin-bottom: 4px;
        margin-right: 2px;
    }

    .linehkz {
        line-height: 20px;
    }

    .txkz {
        width: 50px;
        border-radius: 50%;
    }
    .dtimgkz{
        overflow: hidden;
        height: 200px;
        padding: 1px;
    }
    .dtimgkz img{
        width: 180px;
    }
    .btn-sm, .btn-group-sm > .btn {
         font-size: 0.7rem;
     }
    .addPostkz{
        position: fixed;
        right: 8px;
        bottom: 60px;
        z-index: 44;
        width:35px;
        padding: 2px;
        background: #8d98ea;
        border-radius: 50%;
    }


</style>
