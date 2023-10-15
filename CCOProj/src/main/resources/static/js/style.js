$(() => {
    console.log("ok")
    // 鼠标经过
    $(".nav>ul>li").mouseover(function () {
        // $(this) jQuery 当前元素  this不要加引号
        // show() 显示元素  hide() 隐藏元素
        $(this).find("ul").stop().show(300);
        console.log("ok")
    });
    // 鼠标离开
    $(".nav>ul").mouseout(function () {
        $(this).find("ul").stop().hide(300);
        console.log("close")
    });

    // 鼠标经过
    $(".menuButton").mouseover(function () {
        if (($(".menuBox").mouseover) != 0) {
            $("body").css("overflow-y", "hidden");
            $("lord-icon").stop().fadeOut(200);
            $(".menuBox").stop().slideDown(300);
        }
    })
    //按鈕點擊
    $(".menuButtonClose").click(function () {
        $("body").css("overflow-y", "scroll");
        $("lord-icon").stop().fadeIn(300);
        $(".menuBox").stop().slideUp(300);
    })
    // $(".menuBox").click(function () {
    //     $(".menuBox").stop().hide(300);
    // })

    let click1 = false
    let click2 = false
    let click3 = false
    $(".click1").click(function () {
        // $(this) jQuery 当前元素  this不要加引号
        // show() 显示元素  hide() 隐藏元素
        if (click1) {
            $(this).siblings("ul").stop().hide(300);
            $("#click1-buutton").removeClass("click1-buutton0").addClass("click1-buutton180");
            click1 = false;
            console.log("click1=false");
        } else {
            $(this).siblings("ul").stop().show(300);
            $("#click1-buutton").removeClass("click1-buutton180").addClass("click1-buutton0");
            console.log("click1=true");
            click1 = true;
        }
    });

    $(".click2").click(function () {
        // $(this) jQuery 当前元素  this不要加引号
        // show() 显示元素  hide() 隐藏元素
        if (click2) {
            $(this).siblings("ul").stop().hide(300);
            click2 = false
            console.log("click2=false")
        } else {
            $(this).siblings("ul").stop().show(300);
            console.log("click2=true")
            click2 = true
        }
    });
    $("#click2-buutton").click(function () {
        if (click2) {
            $(this).removeClass("click2-buutton180").addClass("click2-buutton0");
        } else {
            $(this).removeClass("click2-buutton0").addClass("click2-buutton180");
        }

        click2 = !click2;
    });

    $(".click3").click(function () {
        // $(this) jQuery 当前元素  this不要加引号
        // show() 显示元素  hide() 隐藏元素
        if (click3) {
            $(this).siblings("ul").stop().hide(300);
            click3 = false
            console.log("click3=false")
        } else {
            $(this).siblings("ul").stop().show(300);
            console.log("click3=true")
            click3 = true
        }
    });
})