<template>
    <ul>
        <li v-for="user in users">{{user}}</li>
    </ul>
</template>

<script>
    module.exports = {
        props:{
            users:{           //这个就是父组件中子标签自定义名字
                type:Array,
                required:true
            }
        }
    }
</script>

<style>
    .hello {
        background-color: #ffe;
    }
</style>