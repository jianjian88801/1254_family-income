<template>
    <div>
        <Xheader title="Family Account Book" imgurl="home1.png"></Xheader>
        <!--图片广告-->
        <b-container fluid="" class="p-1 mt-1">
            <b-carousel
                    id="carousel-1"
                    v-model="slide"
                    :interval="4000"
                    controls
                    indicators
                    background="#ababab"
                    img-width="1024"
                    img-height="480"
                    style="text-shadow: 1px 1px 2px #333;"
                    @sliding-start="onSlideStart"
                    @sliding-end="onSlideEnd"
            >
                <!-- Text slides with image -->
                <b-carousel-slide
                        caption="First slide"
                        text="Nulla vitae elit libero, a pharetra augue mollis interdum."
                        img-src="https://picsum.photos/1024/480/?image=52"
                ></b-carousel-slide>
            </b-carousel>
        </b-container>
        <!--公告-->
        <b-container fluid="" class="p-1">
            <b-card no-body>
                <b-card-header class="bgthemeColor textthemeColor fsize1"><img :src="ggImg" class="ggimgkz"/>通知中心
                </b-card-header>
                <b-card-body>
                    <b-list-group>
                        <b-list-group-item>
                            <b-badge class="mr-1" variant="danger">New</b-badge>
                            test 此模块功能正在开发中<span class="ml-1 text-muted">2020-1-1</span></b-list-group-item>
                        <b-list-group-item>
                            <b-badge class="mr-1" variant="danger">New</b-badge>
                            test 此模块功能正在开发中<span class="ml-1 text-muted">2020-1-1</span></b-list-group-item>
                        <b-list-group-item>
                            <b-badge class="mr-1" variant="danger">New</b-badge>
                            test 此模块功能正在开发中<span class="ml-1 text-muted">2020-1-1</span></b-list-group-item>
                        <b-list-group-item>
                            <b-badge class="mr-1" variant="danger">New</b-badge>
                            test 此模块功能正在开发中<span class="ml-1 text-muted">2020-1-1</span></b-list-group-item>
                    </b-list-group>
                </b-card-body>
            </b-card>
        </b-container>
        <p class="text-danger p-2">此页所有功能都暂未具体实现，仅仅做了布局，功能正在开发中</p>
        <b-container class="p-1">
            <el-date-picker
                    v-model="date"
                    type="month"
                    placeholder="筛选指定日期"
                    @change="doSelect"
            >
            </el-date-picker>
            <div class="mt-1">
                <div>
                    <el-radio-group v-model="peopleSelect" size="small" fill="#91cc75" @change="doSelect">
                        <el-radio-button v-for="(item, i) in fayNs" :label="item" :key="i"></el-radio-button>
                    </el-radio-group>
                </div>
                <div>
                    <el-radio-group v-model="dateSelect" size="small" fill="#91cc75">
                        <el-radio-button label="这一年"></el-radio-button>
                        <el-radio-button label="这个月"></el-radio-button>
                    </el-radio-group>
                </div>
                <div>
                    <el-radio-group v-model="szSelect" size="small" fill="#91cc75">
                        <el-radio-button label="收入"></el-radio-button>
                        <el-radio-button label="支出"></el-radio-button>
                    </el-radio-group>
                </div>
            </div>
        </b-container>
        <b-container fluid="" class="p-1">
            <b-card no-body>
                <b-card-header class="bgthemeColor textthemeColor fsize1"><img :src="billImg" class="ggimgkz"/>收支总览
                </b-card-header>
                <b-card-body>
                    <el-table
                            :data="tableData"
                            stripe
                            style="width: 100%"
                            max-height="300"
                    >
                        <el-table-column
                                prop="date"
                                label="日期"
                                align="center"
                        >
                        </el-table-column>
                        <el-table-column
                                prop="earn"
                                label="收入"
                                align="center"
                        >
                            <template slot-scope="scope">
                                <div class="text-success">￥{{scope.row.earn}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="cost"
                                label="支出"
                                align="center"
                        >
                            <template slot-scope="scope">
                                <div class="text-danger">￥{{scope.row.cost}}</div>
                            </template>
                        </el-table-column>
                    </el-table>
                </b-card-body>
            </b-card>
        </b-container>
<!--        <v-chart class="chart" :option="option"/>-->
    </div>
</template>

<script>
    // import {use} from "echarts/core";
    // import {CanvasRenderer} from "echarts/renderers";
    // import {PieChart} from "echarts/charts";
    // import {LegendComponent, TitleComponent, TooltipComponent} from "echarts/components";
    // import VChart, {THEME_KEY} from "vue-echarts";

    // use([
    //     CanvasRenderer,
    //     PieChart,
    //     TitleComponent,
    //     TooltipComponent,
    //     LegendComponent
    // ]);
    export default {
        name: "Home",
        // components: {
        //     VChart
        // },
        // provide: {
        //     [THEME_KEY]: ""
        // },
        data() {
            return {
                fayNs: [],
                fayUsersInfo: [],
                userInfo: {},
                slide: 0,
                sliding: null,
                date: '2021-01',
                dateSelect: '这一年',
                szSelect: '支出',
                peopleSelect: '',
                homeImg: require('../assets/img/home1.png'),
                homeImg1: require('../assets/img/home.png'),
                billImg: require('../assets/img/bill1.png'),
                ggImg: require('../assets/img/gg.png'),
                // option: {
                //     title: {
                //         text: "支出详情",
                //         left: "center"
                //     },
                //     tooltip: {
                //         trigger: "item",
                //         formatter: "{a} <br/>{b} : {c} ({d}%)"
                //     },
                //     legend: {
                //         orient: "vertical",
                //         left: "left",
                //         data: [
                //             "饮食方面",
                //             "穿戴衣物",
                //             "话费网费",
                //             "日常零食",
                //             "学习方面",
                //             "房租水电",
                //             "生活日用",
                //             "医疗方面",
                //             "娱乐方面",
                //             "交通出行",
                //             "其他支出"
                //         ]
                //     },
                //     series: [
                //         {
                //             name: "支出详情",
                //             type: "pie",
                //             radius: "55%",
                //             center: ["50%", "60%"],
                //             data: [
                //                 {value: 335, name: "饮食方面"},
                //                 {value: 310, name: "穿戴衣物"},
                //                 {value: 234, name: "话费网费"},
                //                 {value: 135, name: "日常零食"},
                //                 {value: 1548, name: "学习方面"},
                //                 {value: 1548, name: "房租水电"},
                //                 {value: 135, name: "生活日用"},
                //                 {value: 1548, name: "医疗方面"},
                //                 {value: 1548, name: "娱乐方面"},
                //                 {value: 1548, name: "交通出行"},
                //                 {value: 1548, name: "其他支出"}
                //             ],
                //             emphasis: {
                //                 itemStyle: {
                //                     shadowBlur: 10,
                //                     shadowOffsetX: 0,
                //                     shadowColor: "rgba(0, 0, 0, 0.5)"
                //                 }
                //             }
                //         }
                //     ]
                // },
                tableData: [{
                    date: '2016-05-02',
                    earn: '1000',
                    cost: '2000'
                }, {
                    date: '2016-05-04',
                    earn: '23523',
                    cost: '23424'
                }, {
                    date: '2016-05-01',
                    earn: '5353',
                    cost: '12341'
                }]
            }
        },
        created() {
            this.userInfo = this.$store.getters.getUserInfo.userInfo
            this.peopleSelect = this.userInfo.name
            this.getFamilyMs()
        },
        methods: {
            doSelect(){
                this.showBills()
            },
            async getFamilyMs(){
                const key =  this.userInfo.pkey
                const rst = await this.axios.get("/getFamilyMs?pkey="+key)
                this.fayNs = rst.data.data[1]
                this.fayUsersInfo = rst.data.data[0]
                //先得到家庭成员之后再加载数据
                this.showBills()
            },
            async showBills(){
                //得到 phone及密码
                const that=this
                const cuserInfo = this.fayUsersInfo.find(function (user) {
                    return user.name === that.peopleSelect;
                })
                var date = new Date(this.date);
                var parseDate = date.getFullYear() + "-" + (date.getMonth()+1).toString().padStart(2, "0")
                console.log(parseDate)
              const rst = await this.axios.post("/showBills", "name="+cuserInfo.name + "&phone="+cuserInfo.sjh
              +"&password="+cuserInfo.pwd+"&date="+parseDate);
                console.log(rst)
                this.tableData = Object.values(rst.data.data);
              this.tableData.reverse()
            },
            onSlideStart(slide) {
                console.log(slide)
                this.sliding = true
            },
            onSlideEnd(slide) {
                console.log(slide)
                this.sliding = false
            }
        }
    }
</script>

<style scoped>
    .ceshi{
        color: #1296db;
    }
    .ggimgkz {
        width: 25px;
        padding: 3px;
        margin-bottom: 4px;
        margin-right: 2px;
    }

    .chart {
        height: 400px;
        background: white;
    }
</style>
