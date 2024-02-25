<template>
    <div id="app">
        <b-container class="fixedMenu" fluid="">
            <b-nav pills justified>
                <b-nav-item to="/"><img :src="homeImg" class="imgkz"/></b-nav-item>
                <b-nav-item to="/bill"><img :src="billImg" class="imgkz"/></b-nav-item>
                <b-nav-item to="/other"><img :src="otherImg" class="imgkz"/></b-nav-item>
                <b-nav-item to="/settings"><img :src="settingsImg" class="imgkz"/></b-nav-item>
            </b-nav>
        </b-container>
        <router-view/>
        <b-container class="heightZw"></b-container>
    </div>
</template>
<script>
    export default {
        name: "App",
        data() {
            return{
               homeImg: require('./assets/img/home.png'),
               billImg: require('./assets/img/bill.png'),
               otherImg: require('./assets/img/other.png'),
               settingsImg: require('./assets/img/settings.png')
            }
        }
    }
</script>
<style>

    .el-collapse-item__content{
        padding-bottom: 8px !important;
    }
    .el-collapse-item__header{
        padding: 5px;
    }
    hr{
        margin-top: 5px !important;
        margin-bottom: 5px!important;
    }
    body{
        background-color: whitesmoke !important;
        font-size: 0.95em !important;
    }
    h1{
      font-size: 2em !important;
    }
    h2{
        font-size: 1.7em !important;
    }
    h3{
        font-size: 1.4em !important;
    }
    h4{
        font-size: 1.1em !important;
        margin-bottom: 0px !important;
    }
    h5{
        font-size: 1em !important;
    }
    .br-1rem{
        border-radius: 1rem !important;
    }
    .headerKz{
        background-color: #1296db;
        color: white;
        padding: .35em;
    }

    .r2px {
        margin-right: 2px;
    }
.fixedMenu{
    background-color: white;
    border: white;
    position: fixed;
    bottom: 0px;
    z-index: 20
}
    .headerKz.mb-1.container{
        padding-left: 5px;
    }
    .imgkz{
        width: 35px;
        padding: 3px;
    }
    .el-date-editor.el-input.el-input--prefix.el-input--suffix.el-date-editor--month {
        width: 100%;
    }
    .bgthemeColor{
        background-color: #1296db !important;
    }
    .textthemeColor{
        color: white !important;
    }
    .card-header{
        padding: 0.35rem 0.35rem !important;
        border-bottom: 0px !important;
    }
    .card-body{
        padding: .5em !important;
    }
    .list-group-item{
        padding: .5rem .6rem !important;
    }
    .fsize1{
        font-size: .85rem;
    }
    .heightZw{
        min-height: 100px;
    }
</style>
