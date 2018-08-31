 //一加载就执行填充信息  用于记住登录信息
    $(document).ready(function(){
        if($.cookie("password") != ''){
            $("#password").val($.cookie("password"));
        }
        if($.cookie("account") != ''){
            $("#account").val($.cookie("account"));
        }
    })


    //不加这个复选框样式不会改变
    layui.use('form', function(){
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    });


    //回车登录
    function keyLogin(){
        if (event.keyCode==13){ //回车键的键值为13
            //document.getElementById("login").click(); //调用登录按钮的登录事件
            //调用登陆函数
            landing();
        }
    }

    /*//用于验证码
  var code;//声明一个变量用于存储生成的验证码
  function changeImg(){
      var arrays=new Array(
          '1','2','3','4','5','6','7','8','9','0',
          'a','b','c','d','e','f','g','h','i','j',
          'k','l','m','n','o','p','q','r','s','t',
          'u','v','w','x','y','z',
          'A','B','C','D','E','F','G','H','I','J',
          'K','L','M','N','O','P','Q','R','S','T',
          'U','V','W','X','Y','Z'
      );
      code='';//重新初始化验证码
      //alert(arrays.length);
      //随机从数组中获取四个元素组成验证码
      for(var i=0;i<4;i++){
      //随机获取一个数组的下标
          var r=parseInt(Math.random()*arrays.length);
          code+=arrays[r];
          //alert(arrays[r]);
      }
      //alert(code);
      document.getElementById('code').innerHTML=code;//将验证码写入指定区域
  } */

    let admin = new Vue({
        el:'#content',
        data:{
            name:'',
            password:'',
            verify:'',
        },
        methods:{
            clickLanding:function(){
                landing();
            },
            change_code:function(){
                //changeImg();
                restCode()
            }
        }
    })


    function  restCode() {
        $('#code').attr('src','authCode?abc='+Math.random());//链接后添加Math.random，确保每次产生新的验证码，避免缓存问题。
    }


    //登陆对应的函数
    function landing(){
        //获取参数
        var name = admin.name;
        var password = admin.password;
        var verify =admin.verify;

        alert();

        if( name.trim() == "" || password.trim() == "" || verify.trim() == ""){
            var obj = document.getElementById("errorMsg");
            $("#tipMsg").html("请填写完整的登录信息！");
            obj.setAttribute("class", "tip-box visiblility-show");
            return ;
        }


        //验证码不对
        if( verify == null || verify ==''){
            //提示错误信息
            var obj = document.getElementById("errorMsg");
            //修改提示文字
            $("#tipMsg").html("请填写正确的验证码！");
            obj.setAttribute("class", "tip-box visiblility-show");
            restCode();
            return ;
        }

        //对用户名密码验证码进行加加密
        //对用户名密码进行based4加密
        var name = encode64(name);
        var password = encode64(password);

        //发送ajax请求
        $.ajax({
            type: 'POST',
            url: 'http://'+window.location.host+'/menu-app/user/login',
            data: 'name='+ name +'&password='+ password +'&verify='+verify,
            dataType: 'JSON',
            success: function(result){
                console.log("用户名称:"+result.msg);
                //登录成功跳转页面
                if(result.code == 1){
                    window.location.href = 'http://'+window.location.host+'/menu-app/page/main.html';
                }else if(result.code == 0){
                    //提示错误信息
                    var obj = document.getElementById("errorMsg");
                    $("#tipMsg").html(result.msg);
                    obj.setAttribute("class", "tip-box visiblility-show");
                    restCode();
                }
            },
            error:function(){
                var obj = document.getElementById("errorMsg");
                $("#tipMsg").html("系统异常请重新登陆！");
                obj.setAttribute("class", "tip-box visiblility-show");
                restCode();
                //alert("系统异常请重新登陆!!!");
                //window.location.href = 'http://'+window.location.host+'/menu-app/login.html';
            }
        });
    }




    //点击错误信息关闭按钮
    $("#closeErrorMsg").click(function(){
        var obj = document.getElementById("errorMsg");
        obj.setAttribute("class", "tip-box visiblility-hidden");
    });

    //关闭错误提示
    function closeErrorMsg(){
        var obj = document.getElementById("errorMsg");
        obj.setAttribute("class", "tip-box visiblility-hidden");
    };

    //验证码提示
    $('.imgcode').hover(function(){
        layer.tips("点击更换验证码", '#code', {
            time: 2000,
            tips: [2, "#3c3c3c"]
        });
    });
    /*------------------base64加密工具函数---------------------------------------------*/
    var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"
        + "wxyz0123456789+/" + "=";

    function encode64(input) {

        var output = "";
        var chr1, chr2, chr3 = "";
        var enc1, enc2, enc3, enc4 = "";
        var i = 0;
        do {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
                + keyStr.charAt(enc3) + keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        } while (i < input.length);

        return output;
    }
    /*------------------------base64加密工具函数------------------------------------------*/