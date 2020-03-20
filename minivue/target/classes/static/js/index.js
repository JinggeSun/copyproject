Vue.use(httpVueLoader);

new Vue({
    el: '#app',
    components:{
        'users': 'url:./js/components/index.vue'
    },
    data: function () {
        return {
            visible: false,
            users: ["Henry", "Bucky"]
        }
    }
})