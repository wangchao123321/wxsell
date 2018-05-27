<html>
<head>
    <meta charset="utf-8">
    <title>卖家商品列表</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <#--<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">-->
    <#--<link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">-->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<#--<link rel="stylesheet" href="/sell/css/style.css">-->


    <#--<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">-->

    <#--<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>-->
  <#--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->


</head>

<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>
                        订单id
                    </th>
                    <th>
                        姓名
                    </th>
                    <th>
                        手机号
                    </th>
                    <th>
                        地址
                    </th>
                    <th>
                        金额
                    </th>
                    <th>
                        订单状态
                    </th>
                    <th>
                        支付状态
                    </th>
                    <th>
                        创建时间
                    </th>
                    <th colspan="2">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>

<#list orderDTOPage.content as orderDTO>
                <tr>
                    <td>
                        ${orderDTO.orderId}
                    </td>
                    <td>
                        ${orderDTO.buyerName}
                    </td>
                    <td>
                        ${orderDTO.buyerPhone}
                    </td>
                    <td>
                        ${orderDTO.buyerAddress}
                    </td>
                    <td>
                        ${orderDTO.orderAmount}
                    </td>
                    <td>
                        ${orderDTO.orderStatus}
                    </td>
                    <td>
                        ${orderDTO.payStatus}
                    </td>
                    <td>
                        ${orderDTO.createTime}
                    </td>
                    <td>
                        详情
                    </td>
                    <td>
                        取消
                    </td>
                </tr>
</#list>
                </tbody>
            </table>
        </div>
        <#--分页-->
        <ul class="pagination pull-right">
            <li><a href="#">上一页</a></li>

            <#list 1..orderDTOPage.getTotalPages() as index>
            <#if currentPage==index>
                <li class="disabled"><a href="#">${index}</a></li>
            <#else>
                <li><a href="/sell/seller/order/list?page=2&size=10">${index}</a></li>
            </#if>
            </#list>
            <li><a href="#">下一页</a></li>
        </ul>
    </div>
</div>
</body>
</html>

<#--<h1>${orderDTOPage.totalPages}</h1>-->
<#--<h1>${orderDTOPage.getTotalPages()}</h1>-->

<#--<#list orderDTOPage.content as orderDTO>-->
    <#--${orderDTO.orderId}<br>-->
<#--</#list>-->