<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

<#--边栏-->
    <#include "../common/nav.ftl">

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="col-md-4 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                            ${orderDTO.getOrderId()}
                            </td>
                            <td>
                            ${orderDTO.getOrderAmount()}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            <#--订单详情表示页-->
                <div class="span12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                商品id
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTO.orderDetailList as orderDetail>
                <tr>
                    <td>
                        ${orderDetail.productId}
                    </td>
                    <td>
                        ${orderDetail.productName}
                    </td>
                    <td>
                        ${orderDetail.productPrice}
                    </td>
                    <td>
                        ${orderDetail.productQuantity}
                    </td>
                    <td>
                        ${orderDetail.productQuantity * orderDetail.productPrice}
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>

            <#--操作-->
<#if orderDTO.getOrderStatusEnum().getMessage() == "新订单">
        <div class="span12">
            <a href="/sell/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-info" type="button">完结订单</a>
            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-danger" type="button">取消订单</a>
        </div>
</#if>
            </div>
        </div>
    </div>
</div>
</body>
</html>

