<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="../../../easyui/demo.css">
        <script type="text/javascript" src="../../../easyui/jquery.min.js"></script>
        <script type="text/javascript" src="../../../easyui/jquery.easyui.min.js"></script>

    </head>
    <body>
<table class="easyui-datagrid" title="Basic DataGrid" style="width:700px;height:250px"
        data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
    <thead>
        <tr>
            <th data-options="field:'itemid',width:80">Item ID</th>
            <th data-options="field:'productid',width:100">Product</th>
            <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
            <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
            <th data-options="field:'attr1',width:250">Attribute</th>
            <th data-options="field:'status',width:60,align:'center'">Status</th>
        </tr>
    </thead>
</table>
    </body>
</html>
