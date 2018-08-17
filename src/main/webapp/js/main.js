

//=============================================    main页面的js文件      ======================================================================================
//JavaScript代码区域
layui.use('element', function(){
    var element = layui.element;

});
function loadMenus() {
    //发送异步请求  加载菜单数据
    /*$.get("/easyui_day4/back/main/menu.json",function(result){
        //console.log(result);
        //遍历一级菜单
        $.each(result,function(idx,menu){
            //遍历二级菜单
            var content ="<div style='text-align:center;'>";
            $.each(menu.children,function(idx,child){
                var args = child.href+"*"+child.iconCls+"*"+child.title;
                content += "<div class='easyui-linkbutton' onClick=\"addTabs('"+args+"');\" data-options=\" plain:true,iconCls:'"+ child.iconCls +"' \" style='border-radius:5%;margin:5px 0px 5px 0px;width:90%;border: 1px solid #95B8E7;'>"+child.title+"</div><br/>";
            });
            content +="</div>";
            //添加一个新的菜单项
            $aa.accordion('add',{
                title:menu.title,
                iconCls:menu.iconCls,
                selected:menu.selected,
                content:content,
            });
        });
    },"JSON");*/
}






//=============================================    main页面的js文件      ======================================================================================