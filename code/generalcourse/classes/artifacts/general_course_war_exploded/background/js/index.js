$(document).ready(function () {
    console.log("dsfksaf");
    if ($.cookie('account') != undefined &&
        $.cookie('password') != undefined &&
        $.cookie('account') != null &&
        $.cookie('password') != null) {

        document.getElementById('form-account').value = $.cookie('account');
        document.getElementById('form-password').value = $.cookie('password');
    }
})

/**
 * 登录
 */
function loginClick() {
   var account = document.getElementById('form-account').value;
   var password = document.getElementById('form-password').value;

    $.ajax({
        url: "/login?account=" + account + "&password=" + password,
        method: "post",

        success: function (data) {
            if (data == 1) {
                $.cookie('account', account);
                $.cookie('password', password);
                location.assign("html/manage.html")
            } else if (data == 0) {
                swal({
                    title: '密码错误',
                    text: '5秒后自动关闭。',
                    timer: 5000
                }).then(
                    function () {},
                    // handling the promise rejection
                    function (dismiss) {
                        if (dismiss === 'timer') {
                            console.log('I was closed by the timer')
                        }
                    }
                )
            } else if (data == -1) {
                swal({
                    title: '账号不存在',
                    text: '5秒后自动关闭。',
                    timer: 5000
                }).then(
                    function () {},
                    // handling the promise rejection
                    function (dismiss) {
                        if (dismiss === 'timer') {
                            console.log('I was closed by the timer')
                        }
                    }
                )
            }

        },
        error: function (data) {

            sweetAlert(
                '哎呦……',
                '出错了！',
                'error'
            )
        }
    })
}
<<<<<<< HEAD
=======

$(document).ready(function(c) {
    $('.close').on('click', function(c){
        $('.login-form').fadeOut('slow', function(c){
            $('.login-form').remove();
        });
    });
});
>>>>>>> 245f3621a32cd5ce0f32bcb96aff946b35bbff32
