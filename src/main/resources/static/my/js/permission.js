function checkPermission() {
    var pers = [];
    //获取当前的权限值
    var permissions = jQuery.parseJSON(localStorage.permission);
    //元素的permission的值和当前用户的权限相比较，如果没有就隐藏元素
    $("[permission]").each(function() {
        var per = $(this).attr("permission");
        if ($.inArray(per, permissions) < 0) {
            $(this).hide();
        }
    });

    return pers;
}

function checkPermissionForTable(){
    var permissions = jQuery.parseJSON(localStorage.permission);
    $("[permission]").each(function() {
        var per = $(this).attr("permission");
        if ($.inArray(per, permissions) >= 0) {
            return true;
        }
    });
    return false;
}