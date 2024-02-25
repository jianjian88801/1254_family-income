<template>
    <div>
        <Xheader title="Account Book" imgurl="bill1.png" class="mb-0"></Xheader>
        <div class="text-center">
            <div class="row p-0 m-0">
                <div class="col text-center text-white p-1 m-1 bg-success fsizekz" @click="goAddOrder(1)">收入信息发布</div>
                <div class="col text-center text-white p-1 m-1 bg-warning fsizekz" @click="goAddOrder(0)">支出信息发布</div>
            </div>
        </div>
        <b-container class="p-1">
            <el-date-picker
                    v-model="date"
                    type="month"
                    placeholder="筛选指定日期"
            @change="getBillsPage"
            format="yyyy-MM">
            </el-date-picker>
            <div class="mt-1">
                <div>
                    <el-radio-group v-model="peopleSelect" size="small" fill="#91cc75" @change="getBillsPage()">
                        <el-radio-button v-for="(item, i) in fayNs" :label="item" :key="i"></el-radio-button>
                    </el-radio-group>
                </div>
                <div>
                    <el-radio-group v-model="dateSelect" size="small" fill="#91cc75" @change="getBillsPage()">
                        <el-radio-button label="这一年"></el-radio-button>
                        <el-radio-button label="这个月"></el-radio-button>
                    </el-radio-group>
                </div>
                <div>
                    <el-radio-group v-model="szSelect" size="small" fill="#91cc75" @change="getBillsPage()">
                        <el-radio-button label="收入"></el-radio-button>
                        <el-radio-button label="支出"></el-radio-button>
                    </el-radio-group>
                </div>
            </div>
        </b-container>
        <b-container class="p-1">
            <el-collapse v-model="activeName" v-for="(item, i) in billsInfo" :key="i" accordion>
                <el-collapse-item :name="i">
                    <template slot="title">
                        <h4>{{item.name}}</h4> <span :class="szSelect === '支出'?'ml-3 text-danger':'ml-3 text-success'">￥{{item.price}}</span>
                    </template>
                    <p class="pl-2 mb-0" v-text="item.content === ''?'该订单暂无描述信息':item.content"></p>
                    <b-img v-for="(item, i) in getImgList(item)" :key="i" :src="'http://fab.66zx.top/'+item" class="p-2 br-1rem" fluid/>
                    <div class="text-right">
                        <b-button-group size="sm" class="mr-2">
                            <b-button variant="outline-secondary" class="font">
                                <b-icon icon="person" class="r2px"></b-icon>
                                {{item.user}}
                            </b-button>
                            <b-button variant="outline-secondary">
                                <b-icon icon="bookmark" class="r2px"></b-icon>
                                {{item.ctg}}
                            </b-button>
                            <b-button variant="outline-secondary">
                                <b-icon icon="calendar" class="r2px"></b-icon>
                                {{item.time}}
                            </b-button>
                        </b-button-group>
                    </div>
                </el-collapse-item>
            </el-collapse>
            <el-pagination
                    background
                    layout="prev, pager, next"
                    :pager-count="5"
                    :page-count="pageNum"
                    :current-page="current"
                    :small="true"
                    @current-change="handleCurrentChange"
                    class="text-right bg-white">
            </el-pagination>
        </b-container>
    </div>
</template>

<script>

    export default {
        name: "Bill",
        data() {
            return {
                date: '2021-01',
                dateSelect: '这一年',
                szSelect: '支出',
                peopleSelect: '',
                activeName: '1',
                userInfo: {},
                billsInfo: {},
                fayNs: [],
                fayUsersInfo: [],
                pageNum: 1,
                current: 1
            };
        },
        created() {
            this.userInfo = this.$store.getters.getUserInfo.userInfo
            this.peopleSelect = this.userInfo.name
            this.getFamilyMs()
        },
        methods: {
            getImgList(item){
                var arr = [];
                if(item.imgurl === ''){
                    arr.push('default.png')
                    return arr
                }
                if(item.imgurl.search(",") !== -1){
                    var spilt = item.imgurl.split(",");
                    for (var val of spilt) {
                        arr.push(val)
                    }
                }else{
                    arr[0] = item.imgurl
                }
                console.log(arr)
                return arr;
            },
            async getFamilyMs(){
                const key =  this.userInfo.pkey
                const rst = await this.axios.get("/getFamilyMs?pkey="+key)
                this.fayNs = rst.data.data[1]
                this.fayUsersInfo = rst.data.data[0]
                this.getBillsPage()
                //先得到家庭成员之后再加载数据
            },
            getBillsPage: async function(){
                const dateSel = this.dateSelect
                let yearMonth;
                if(dateSel === '这一年'){
                    yearMonth = 1
                }else if(dateSel === '这个月'){
                    yearMonth = 0
                }
                const type = this.szSelect === "收入" ? 1 : 0;
                const date = new Date(this.date)
                const month = (date.getMonth() + 1).toString().padStart(2, '0');
                const dateF = date.getFullYear() + '-' + month
                //得到 phone及密码
                const that=this
                const cuserInfo = this.fayUsersInfo.find(function (user) {
                    return user.name === that.peopleSelect;
                })
                const url = "/getPageBills?phone="+cuserInfo.sjh+"&type="+type+"&current="+this.current+"" +
                    "&yearMonth="+yearMonth+"&date="+dateF+"&password="+cuserInfo.pwd+"&name="+cuserInfo.name
                const rst = await this.axios.get(url)
                console.log(rst)
                const base = rst.data.data
                this.pageNum = base.pages
                this.current = base.current
                this.billsInfo = base.records
            },
            handleCurrentChange: function (val) {
                this.current = val;
                this.getBillsPage()
            },
            goAddOrder: function (type) {
                this.$router.push({
                    name: 'Order',
                    params: {
                        type: type
                    }
                })
            },
        },
        computed: {

        }
    }
</script>

<style scoped>
    .btn-sm, .btn-group-sm > .btn {
        font-size: 0.7rem;
    }
    .fsizekz{
        font-size: .8em;
    }

</style>
