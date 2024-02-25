<template>
    <!--header-->
    <b-container class="headerKz mb-1">
        <h4><img :src="titleImg" class="imgkz"/>{{titleText}}</h4>
    </b-container>
</template>

<script>
    export default {
        name: "Xheader",
        props: ['title', 'imgurl'],
        data() {
            return{
                titleImg: require('../assets/img/'+this.imgurl),
                titleText: this.title
            }
        }
    }
</script>

<style scoped>
    .imgkz{
        width: 32px;
        padding: 4px;
        margin-bottom: 8px;
    }
</style>
