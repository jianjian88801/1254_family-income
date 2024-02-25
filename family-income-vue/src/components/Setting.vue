<template>
    <div>
        <Xheader title="Settings" imgurl="settings1.png"></Xheader>
        <div class="bg-danger text-white text-center logoutkz" v-if="isLogin" @click="logout">退出登录</div>
        <b-container class="p-1">
            <b-card no-body class="mt-2" v-if="showAddF">
                <b-card-header header-class="bgthemeColor textthemeColor fsize1">
                    <img :src="familyImg" class="ImgKz"/>加入家庭
                </b-card-header>
                <b-card-body>
                    <b-form>
                        <b-input-group prepend="Token">
                            <b-form-input placeholder="输入授权码" v-model="pkey"></b-form-input>
                            <b-input-group-append>
                                <b-button class="bgthemeColor" @click="addFamily">加入</b-button>
                            </b-input-group-append>
                        </b-input-group>
                    </b-form>
                    <div class="text-right mt-2"><span>当前: </span><span v-text="token" class="text-success"></span></div>
                </b-card-body>
            </b-card>
            <b-card no-body class="d-none">
                <b-card-header header-class="bgthemeColor textthemeColor fsize1">
                    <img :src="familyImg" class="familyImgKz"/>创建家庭
                </b-card-header>
                <b-card-body>
                    <b-form>
                        <b-input-group prepend="Token">
                            <b-form-input placeholder="您的手机号"></b-form-input>
                            <b-input-group-append>
                                <b-button class="bgthemeColor">创建</b-button>
                            </b-input-group-append>
                        </b-input-group>
                    </b-form>
                </b-card-body>
            </b-card>
            <b-card no-body v-if="showLogin" class="mt-2">
                <b-card-header header-class="bgthemeColor textthemeColor fsize1">
                    <img :src="loginImg" class="ImgKz"/>账号登录
                </b-card-header>
                <b-card-body>
                    <b-form @submit.prevent="login">
                        <b-input-group class="mb-2">
                            <b-input-group-prepend is-text>
                                <b-icon icon="phone"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" name="username" placeholder="手机号" v-model="loginData.username"></b-form-input>
                        </b-input-group>
                        <b-input-group class="mb-2">
                            <b-input-group-prepend is-text>
                                <b-icon icon="key"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" name="password" placeholder="密码" v-model="loginData.password"></b-form-input>
                            <b-input-group-append>
                                <b-button type="submit" class="bgthemeColor">登录</b-button>
                            </b-input-group-append>
                        </b-input-group>
                    </b-form>
                    <small class="text-right d-block text-muted mt-1" @click="showRegi = true">暂无账号？免费注册</small>
                </b-card-body>
            </b-card>
            <b-card no-body class="mt-2" v-if="showRegi">
                <b-card-header header-class="bgthemeColor textthemeColor fsize1">
                    <img :src="regiImg" class="ImgKz"/>账号注册
                </b-card-header>
                <b-card-body>
                    <b-form>
                        <b-input-group class="mb-2">
                            <b-input-group-prepend is-text>
                                <b-icon icon="person-fill"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="姓名" v-model="regiData.name"></b-form-input>
                        </b-input-group>
                        <b-input-group class="mb-2">
                            <b-input-group-prepend is-text>
                                <b-icon icon="envelope"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="邮箱" v-model="regiData.email"></b-form-input>
                        </b-input-group>
                        <b-input-group class="mb-2">
                            <b-input-group-prepend is-text>
                                <b-icon icon="phone"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="手机号" v-model="regiData.sjh"></b-form-input>
                        </b-input-group>
                        <b-input-group class="mb-2">
                            <b-input-group-prepend is-text>
                                <b-icon icon="key"></b-icon>
                            </b-input-group-prepend>
                            <b-form-input type="text" placeholder="密码" v-model="regiData.pwd"></b-form-input>
                            <b-input-group-append>
                                <b-button class="bgthemeColor" @click="register">注册</b-button>
                            </b-input-group-append>
                        </b-input-group>
                    </b-form>
                    <small class="text-right d-block text-muted mt-1">已有账号？立即登录</small>
                </b-card-body>
            </b-card>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: "Setting",
        created() {
            let status = this.$store.getters.getLoginStatus;
            const userInfo = this.$store.getters.getUserInfo.userInfo
            this.userInfo = userInfo
            // const pkey = userInfo.pkey
            // if(pkey === '' || pkey === undefined || pkey === null){
            //     this.showAddF = 1
            // }else{
            //     this.showAddF = 0
            // }
            if(status === 0){
                this.toast('请先登录', 'warning')
            }
            this.isLogin = status
            this.showLogin = !status
        },
        data() {
            return {
                familyImg: require('../assets/img/family.png'),
                loginImg: require('../assets/img/login.png'),
                regiImg: require('../assets/img/regi.png'),
                showLogin: true,
                showRegi: false,
                showAddF: true,
                isLogin: false,
                pkey: '',
                userInfo: {},
                loginData: {
                    username: '',
                    password: ''
                },
                regiData: {
                    name: '',
                    email: '',
                    sjh: '',
                    pwd: '',
                }
            }
        },
        methods: {
            async addFamily(){
                var data = this.$store.getters.getUserInfo.userInfo
                if(this.pkey === ''){
                    this.toast("请输入秘钥", "warning")
                    return
                }
                data.pkey = this.pkey
                console.log(data)
                const rst = await this.axios.put("/user", this.$qs.stringify(data))
                console.log(rst)
                if(rst.data.code === 200){
                    //更新 userInfo
                    var newUserInfo = this.$store.getters.getUserInfo
                    newUserInfo.pkey = this.pkey
                    this.$store.commit("updateUserInfo", newUserInfo)
                    this.userInfo = newUserInfo
                    this.toast("加入家庭成功",'success')
                }else{
                    this.toast("加入家庭失败",'warning')
                }
            },
            async register(){
                const rst = await this.axios.post("/user", this.$qs.stringify(this.regiData))
                console.log(rst)
                if(rst.data.code === 200){
                    this.toast('注册成功', 'success')
                    this.showLogin = 1
                    this.showRegi = 0
                    this.isLogin = 0
                }else{
                    this.toast('注册失败', 'warning')
                }
            },
            login: async function(){
                console.log("登录中")
                const rst = await this.axios.post("/login", this.$qs.stringify(this.loginData))
                if(rst.data.code === 400){
                    this.toast('登录失败，请检查登录信息', 'warning')
                }else if(rst.data.code === 200){
                    this.$store.commit('updateUserInfo', rst.data.data)
                    this.userInfo = rst.data.data.userInfo
                    this.$store.commit('loginStatus', 1)
                    this.toast('登录成功', 'success')
                    // if(rst.data.data.userInfo.pkey === undefined){
                    //     this.showAddF = 1
                    // }
                    this.showLogin = 0
                    this.showRegi = 0
                    this.isLogin = 1
                }
            },
            test(){
                this.axios.post("/login")
            },
            toast: function (msg, type) {
                this.$message({
                    message: msg,
                    type: type,
                    duration: 2000
                });
            },
            logout(){
                this.isLogin = false
                this.showLogin = true
                this.$store.commit('clearUserInfo')
                this.$store.commit('loginStatus', 0)
            }
        },
        computed: {
            token(){
                return this.userInfo === undefined ? 'xxxxxxxx' : this.userInfo.pkey
            }
        }
    }
</script>

<style scoped>
    .ImgKz {
        width: 30px;
        padding: 4px;
        margin-bottom: 5px;
    }
    .headerKz.mb-1.container{
        margin-bottom: 0px !important;
    }
    .logoutkz{
        height: 35px;
        line-height: 35px;
        font-size: .9em;
    }
</style>
