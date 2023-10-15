<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>報表分析</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="./css/back-end.css">
<script type="text/javascript" src="/js/back-end.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>

<style>
#dataRange {
	padding-top: 10%;
}



table {
	table-layout: fixed;
	text-align: center;
	width: 100%;
	border-collapse: collapse;
}

th {
	font-size: 36px;
	background-color: #f4f4f4;
	padding: 10px;
	border-bottom: 1px solid #ddd;
}

td {
	font-size: 20px;
	padding-top: 2%;
}

/* HighCharts CSS */
.highcharts-figure, .highcharts-data-table table {
	min-width: 310px;
	max-width: 800px;
	margin: 1em auto;
}

.container {
	max-width: 1300px;
	margin: auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
	margin-top: 100px;
	z-index: 0;
}

.highcharts-data-table table {
	font-family: Verdana, sans-serif;
	border-collapse: collapse;
	border: 1px solid #ebebeb;
	margin: 10px auto;
	text-align: center;
	width: 100%;
	max-width: 500px;
}

.highcharts-data-table caption {
	padding: 1em 0;
	font-size: 1.2em;
	color: #555;
}

.highcharts-data-table th {
	font-weight: 600;
	padding: 0.5em;
}

.highcharts-data-table td, .highcharts-data-table th,
	.highcharts-data-table caption {
	padding: 0.5em;
}

.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even)
	{
	background: #f8f8f8;
}

.highcharts-data-table tr:hover {
	background: #f1f7ff;
}

.form-container {
	display: flex;
	align-items: flex-end;
}

.btn {
	margin-right: 10px;
	/* 添加按钮之间的间距 */
}

.date-range-form {
	flex-grow: 1;
	/* 填充剩余可用空间，将日期范围表单推向最右侧 */
	display: flex;
	justify-content: flex-end;
	/* 将表单元素靠右对齐 */
	align-items: flex-end;
}

.date-range-form label, .date-range-form input[type="date"],
	.date-range-form input[type="submit"] {
	margin: 0;
	/* 移除所有外边距 */
}

.date-range-form label {
	margin-right: 10px;
	/* 为日期标签添加一些右侧间距，根据需要进行调整 */
	display: block;
	margin-bottom: 5px;
	/* 调整标签底部外边距以控制与日期选择器的对齐 */
	font-size: large;
}

.date-range-form input[type="date"] {
	margin-right: 20px;
	/* 为日期选择器添加一些左侧间距，根据需要进行调整 */
	display: block;
}


</style>



</head>



<body>
	 <header>
        <div class="openButton"></div>
        <h1>報表分析系統</h1>
        <ul class="menuBox" style=" padding-left: 0 ;">
            <div class="menu-top">
                <div class="closeButton"></div>
            </div>
            <li>
                <a class="click1" href="/kitchenDisplaySystem">廚房刊版系統</a>
                <!-- 看自已要不要加 -->
                <!-- <ul>     
                        <li><a href="#">第二層</a></li>
                        <li><a href="#">第二層</a></li>
                        <li><a href="#">第二層</a></li>
                    </ul> -->
            <li>
                <a class="click2" href="#">第一層</a>
            <li>
                <a class="click3" href="#">第一層</a>
        </ul>
    </header>
	<!-- 報表分析子容器 -->
	<div class="container">
		<!-- 選擇日期 -->
	<div class="form-container">
			<div id="defaultButton" class="btn-group btn-group-md">
			<button class="btn btn-primary" id="todayButton"
				data-date-range="today" data-url="/todayData">今天</button>
			<button class="btn btn-primary" id="weekButton"
				data-date-range="week" data-url="/weeklyData">過去 7 天</button>
			<button class="btn btn-primary" id="monthButton"
				data-date-range="month" data-url="/monthlyData">過去 30 天</button>
			</div>
			<div class="date-range-form" id="customButton">
				<label for="startDate">開始日期：</label>
				<input class="input-group-text" type="date" id="startDate" required>
				<label for="endDate">結束日期：</label>
				<input class="input-group-text" type="date" id="endDate" required>
				<button id="customDateButton" class="btn btn-info">查詢</button>
			</div>
	 </div>

		<hr class="my-4">
		<span style="color: #1c7288cc; font-size: medium;">報表數據每 90 分鐘更新一次</span>
		<!-- 營業額訂單欄 -->
		<table  style="width: 100%; border-collapse: collapse;">
			<tr>
				<th>營業額</th>
				<th>訂單量</th>
			</tr>
			<tr>
				<td id="totalRevenue"></td>
				<td id="totalOrders"></td>
			</tr>
		</table>

		<hr class="my-4">

		<!-- 營業額 & 訂單分析圖 -->
		<table  style="width: 100%; border-collapse: collapse;">
			<tr>
				<th>營業額 & 訂單分析圖</th>
			</tr>
			<tr>
				<td>
					<figure class="highcharts-figure">
						<div id="soChart"></div>
					</figure>
				</td>
		</table>

		<hr class="my-4">

		<!-- 外帶內用 & 類別銷量 -->
		<table  style="width: 100%; border-collapse: collapse;">
			<tr>
				<th>外帶內用比例</th>
				<th>類別銷量</th>
			</tr>
			<tr>
				<td>
					<figure class="highcharts-figure">
						<div id="tdChart"></div>
					</figure>
				</td>
				<td>
					<figure class="highcharts-figure">
						<div id="cateChart"></div>
					</figure>
				</td>
			</tr>
		</table>

		<hr class="my-4">
		<!-- 熱賣餐點排行欄 -->
		<table  style="width: 100%; border-collapse: collapse;">
			<tr>
				<th>熱賣餐點排行</th>
			</tr>
		</table>

		<!-- 熱賣餐點排行 -->
		<div id="hotProduct"></div>

	</div>
</body>

<script>
    $(() => {
    	// 頁面載入時請求預設數據
    	var currentUrl = "/monthlyData";
        requestData(currentUrl);

        // 按鈕點擊事件
        $(".btn-primary").click(function () {
        currentUrl = $(this).data("url");
        requestData(currentUrl);
    }); 

        // 自訂日期按鈕點擊事件
          
        $("#customDateButton").click(function() {
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            currentUrl = "/customData"
            requestData("/customData", startDate, endDate);
        
        });
        
        
//         	setInterval(function() {
//                 requestData(currentUrl);
//             }, 5000);
        
        setInterval(function() {
            if (currentUrl === "/customData") {
                var startDate = $("#startDate").val();
                var endDate = $("#endDate").val();
                requestData("/customData", startDate, endDate);
            } else {
                requestData(currentUrl);
            }
        }, 5400000);
        
        
    });
    
    function requestData(url, startDate, endDate) {
    	console.log(startDate, endDate, url)
        $.ajax({
            type: "GET",
            url: url,
           
            data: {
                startDate: startDate,
                endDate: endDate
            },
            success: function(data) {
            	console.log(data);
            	updatePage(data);
                
            },
            error: function(error) {
                console.log(error);
            }
        });
    }
    
    
    function updatePage(data) {
        // 在這個函數中更新頁面上的資料和圖表
        var totalRevenue = data.totalRevenue !== undefined && data.totalRevenue !== null ? data.totalRevenue.toLocaleString() : 0;
        var totalOrders = data.totalOrders !== undefined && data.totalOrders !== null ? data.totalOrders.toLocaleString() : 0;
        var diningLocation = data.diningLocation;
        var categorySales = data.foodCategory;
        var hotProducts = data.hotProducts;
        var chartData = data.chartData;

        // 更新總營業額和訂單數
        $("#totalRevenue").text(totalRevenue + " 元");
        $("#totalOrders").text(totalOrders + " 筆");
		
        var dates = chartData[0];
        var revenue = chartData[1];
        var orders = chartData[2];
        
        // 初始化圖表
        initializeChart(dates, revenue, orders, diningLocation, categorySales);

        // 製作熱賣商品排行
        var tableHtml = '<div class="table-responsive small"><table class="table table-striped table-sm"><thead><tr><th scope="col">排名</th><th scope="col">品名</th><th scope="col">銷售數量</th><th scope="col">銷售金額</th></tr></thead><tbody>';

        $.each(hotProducts, function(index, product) {
            tableHtml += '<tr>';
            tableHtml += '<td>' + product.ranking + '</td>';
            tableHtml += '<td>' + product.productName + '</td>';
            tableHtml += '<td>' + product.productQuantity.toLocaleString() + '</td>';
            tableHtml += '<td>' + product.productPrice.toLocaleString() + " 元" + '</td>';
            tableHtml += '</tr>';
        });

        tableHtml += '</tbody></table></div>';

        // 將表格 HTML 插入到指定的<div>中
        $('#hotProduct').html(tableHtml);
    }
    
    
    function initializeChart(dates, revenue, orders, diningLocation, categorySales) {
        //營業額 & 訂單圖
        Highcharts.chart('soChart', {
            chart: {
                zoomType: 'xy'
            },
            title: {
                text: '',
                align: 'center'
            },

            xAxis: [{
                categories: dates,
                crosshair: true
            }],
            yAxis: [{ // Primary yAxis
                labels: {
                    format: '{value} 元',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                title: {
                    text: '營業額',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
            }, { // Secondary yAxis
                title: {
                    text: '訂單數',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                },
                labels: {
                    format: '{value} 筆',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                align: 'left',
                x: 80,
                verticalAlign: 'top',
                y: 60,
                floating: true,
                backgroundColor: Highcharts.defaultOptions.legend.backgroundColor
                    || // theme
                    'rgba(255,255,255,0.25)'
            },
            series: [{
                name: '訂單數',
                type: 'column',
                yAxis: 1,
                data: orders,
                tooltip: {
                    valueSuffix: ' 筆'
                }

            }, {
                name: '營業額',
                type: 'spline',
                data: revenue,
                tooltip: {
                    valueSuffix: ' 元'
                }
            }]
        });



        // 外帶內用圖
        Highcharts.chart('tdChart', {

            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false
            },
            title: {
                text: '外帶內用比例',
                align: 'center',
                verticalAlign: 'middle',
                y: 60
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            accessibility: {
                point: {
                    valueSuffix: '%'
                }
            },
            plotOptions: {
                pie: {
                    dataLabels: {
                        enabled: true,
                        distance: -50,
                        style: {
                            fontWeight: 'bold',
                            color: 'white'
                        }
                    },
                    startAngle: -90,
                    endAngle: 90,
                    center: ['50%', '75%'],
                    size: '110%'
                }
            },
            series: [{
                type: 'pie',
                name: '比例',
                innerSize: '50%',
                data: diningLocation 
            }]
        });

        // 類別銷量圖
        Highcharts.chart('cateChart', {
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45
                }
            },
            title: {
                text: '月類別銷量',
                align: 'center'
            },
            plotOptions: {
                pie: {
                    innerSize: 100,
                    depth: 45
                }
            },
            series: [{
                name: '銷量',
                data: categorySales
            }]
        });
    }
</script>


</html>