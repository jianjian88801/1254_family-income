<template>
    <div>
    <b-img :src="imgurl" center class="imgkz"></b-img>
    <div class="wrap_fixed">
        <b-avatar variant="success" text="下载" class="d-block kz" @click="download" :button="true"></b-avatar>
        <b-avatar variant="info" text="返回" class="d-block mt-1 kz" @click="_return" :button="true"></b-avatar>
    </div>
    </div>
</template>

<script>
    export default {
        name: "Img",
        data(){
            return{
                imgurl: this.$route.params.imgurl
            }
        },
        methods: {
            download: function () {
                var a = document.createElement('a');
                var event = new MouseEvent('click')
                a.download = 'beautifulGirl'
                a.href = this.imgurl;
                a.dispatchEvent(event)
            },
            _return: function () {
                window.history.back()
            }
        },
        mounted() {
        }
    }
</script>

<style scoped>
    .kz{
        font-size: .8em;
    }
    .wrap_fixed {
        position: fixed;
        bottom: 10%;
        right: 5px;
        z-index: 999;
    }
    .imgkz{
        width: 100%;
    }
</style>
