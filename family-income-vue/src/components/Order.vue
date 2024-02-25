<template>
    <div>
        <Xheader :title="title" imgurl="bill1.png"></Xheader>
        <b-container class="p-1 m-0">
        <b-form @submit="addOrder" class="bg-white p-1 pb-3">
            <div class="form-group form-group-sm">
                <label for="name">* 账单名称</label>
                <input type="text" name="name" id="name" class="form-control form-control-sm" v-model="form.name" placeholder="Enter bill name" required/>
                <small class="text-muted"></small>
            </div>
            <div class="form-group form-group-sm">
                <label for="content">账单描述</label>
                <textarea rows="3" class="form-control form-control-sm" id="content" name="content" v-model="form.content" placeholder="Bill description"></textarea>
                <small class="text-muted"></small>
            </div>
            <div class="form-group form-group-sm">
                <label for="price">* 账目金额</label>
                <input type="number" name="price" step="0.01"
                       id="price" class="form-control form-control-sm"
                       v-model="form.price" placeholder="Bill amount" required/>
                <small class="text-muted"></small>
            </div>
            <el-select v-model="form.ctg" placeholder="请选择账单类型" size="small">
                <el-option v-for="item in ctg" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
            <el-upload
                    class="mt-2"
                    ref="upload"
                    action="http://fab1.lzxr.vip/uploadImage/"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :before-remove="beforeRemove"
                    accept="image/*"
                    multiple
                    :limit="3"
                    :headers="upHeaders"
                    :on-exceed="handleExceed"
                    :file-list="fileList"
                    :auto-upload="false"
                    :on-change="handleChange"
            :data="form"
            name="files"
            :on-success="uploadSuccess">
                <el-button size="small" type="primary">点击上传图片</el-button>
                <small class="ml-1">上传图片，可以选择不上传</small>
            </el-upload>
            <div class="text-center mt-2"><button class="btn bgthemeColor text-white btnkz">发布账单</button></div>
        </b-form>
        </b-container>
    </div>
</template>

<script>
    import qs from 'qs'
    export default {
        name: "Order",
        created() {
            const type = this.$route.params.type
            if(type === 0){
                this.title = 'New Expenses'
                this.getCtgz()
            }
            else{
                this.title = 'New Revenue'
                this.getCtgs()
            }
            this.form.type = type
            let userInfo = this.$store.getters.getUserInfo;
            this.form.user = userInfo.userInfo.name
            this.upHeaders.Authorization = userInfo.token
        },
        data (){
            return {
                ctg: {},
                fileList: [],
                title: 'Title',
                upHeaders: {
                    Authorization: ''
                },
                imgList: [],
                file: null,
                upF: 0,
                form: {
                    name: '',
                    price: '',
                    content: '',
                    type: 0,
                    ctg: 1,
                    imgurl: '',
                    user: ''
                },
                nameImg: require('../assets/img/name.png'),
                contentImg: require('../assets/img/wz.png'),
                moneyImg: require('../assets/img/money.png'),
            }
        },
        methods: {
            getCtgs: async function(){
                const url = "/getCtgs"
                const rst = await this.axios.get(url)
                this.ctg = rst.data.data
                console.log("分类选择")
                console.log(this.ctg)
            },
            getCtgz: async function(){
                const url = "/getCtgz"
                const rst = await this.axios.get(url)
                this.ctg = rst.data.data
            },
            addOrder: async function (evt) {
                evt.preventDefault()
                if(this.upF === 0){
                    const data = qs.stringify(this.form);
                    const rst = await this.axios.post("/bill?"+data, this.form)
                    if(rst.data.msg === "成功"){
                        this.toast("发布成功", 'success')
                        this.$router.push({
                            name: 'Bill'
                        })
                    }
                    console.log("没有图片")
                }else{
                    console.log("有图片")
                    this.$refs.upload.submit()
                }

            },
            handleChange(){
                console.log("change")
                this.upF = this.upF + 1;
                console.log(this.upF)
            },
            handleRemove(file) {
                console.log("remove")
                this.upF = this.upF - 1;
                console.log(this.upF)
                var fileName = file.response[0];
                var index = this.imgList.findIndex(value => value[0] === fileName)
                if(index === this.imgList.length - 1){
                    this.imgList.pop()
                }else{
                    console.log(index)
                    this.imgList.splice(index, 1)
                }
                this.form.imgurl = this.imgList.toString()
            },
            handlePreview(file) {
                console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
            uploadSuccess(response){
                console.log("进入")
                console.log(response)
                this.imgList.push(response)
                this.form.imgurl = this.imgList.toString()

                setTimeout(async ()=>{
                    const data = qs.stringify(this.form);
                    const rst = await this.axios.post("/bill?"+data, this.form)
                    if(rst.data.msg === "成功"){
                        this.toast("发布成功", 'success')
                        this.$router.push({
                            name: 'Bill'
                        })
                    }
                }, 500)
            },
            toast: function (msg, type) {
                this.$message({
                    message: msg,
                    type: type,
                    duration: 1200
                });
            }
        }
    }
</script>

<style scoped>
    label{
        color: #1296db;
    }
    .btnkz{
        font-size: .9em;
    }
</style>
