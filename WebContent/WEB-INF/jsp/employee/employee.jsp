<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>人事管理系统 ——员工管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${pageContext.request.contextPath}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${pageContext.request.contextPath}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/pager.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript">
	
	
	//返回json对象
	function getAJAX() {
		var ajax = null;
		try {
			ajax = new ActiveXObject('microsoft.xmlhttp');
		} catch (e) {

			try {
				ajax = new XMLHttpRequest();
			} catch (e) {
				alert('换浏览器把...你out了...')
			}

		}
		return ajax;
	}
	
	//初始化的是我们的那个Jobs
	function initJobs(){
		//获取ajax
     	var ajax=getAJAX();
     	//准备发送请求
     	ajax.open("GET","${pageContext.request.contextPath}/job?method=getJob");
     	//发送ajax请求
     	ajax.send(null);
     	ajax.onreadystatechange=function(){
     		//判断ajax返回的状态
     		if(ajax.readyState==4){  //说明服务器响应了...
     			if(ajax.status==200){ //说明服务器的响应是成功的...
     				//获取服务器返回的数据
     				var val=ajax.responseText;
     				//将string类型的数据转换成json对象
     				var jsonArray=eval("("+val+")");
     				
     				//获取操作job的这个容器
     				
     			    var jobSelects=document.getElementById('job_id');
     				//遍历jsonArray
     				for (var i=0;i<jsonArray.length;i++) {
     					var val1=jsonArray[i];
     					//下面是不是就要获取我们的select的这个容器
     					//向里面添加数据
    					var optionChild=document.createElement('option');
    					//向optionChild中写入数据
    					optionChild.value=val1.jId;
    					//向里面写数据
    					optionChild.innerText=val1.jName;
    					//添加进去Child
    					jobSelects.appendChild(optionChild);
     				}
     			}
     		}
     	}
	}
	//初始化部门
	function initDepts(){
	 	//获取ajax
    	var ajax=getAJAX();
    	//准备发送请求
    	ajax.open("GET","${pageContext.request.contextPath}/depts?method=getDepts");
    	//发送ajax请求
    	ajax.send(null);
    	ajax.onreadystatechange=function(){
    		//判断ajax返回的状态
    		if(ajax.readyState==4){  //说明服务器响应了...
    			if(ajax.status==200){ //说明服务器的响应是成功的...
    				//获取服务器返回的数据
    				var val=ajax.responseText;
    				//将string类型的数据转换成json对象
    				var jsonArray=eval("("+val+")");
    				//找到select的容器对象
    				var deptSelects=document.getElementById('dept_id');
    				//遍历jsonArray
    				for (var i=0;i<jsonArray.length;i++) {
    					var val1=jsonArray[i];
    					//下面找到容器对象
    					//下面创建option对象
    					var optionChild=document.createElement('option');
    					//向value写入数据
    					optionChild.value=val1.dId;
    					optionChild.innerText=val1.dName;
    					//将生成的对象放到容器中
    					deptSelects.appendChild(optionChild);
    				}
    			}
    		}
    	}
	}
	
	 window.onload=function(){
     	initJobs();
     	initDepts();
     }
	
	
	
	
	       $(function(){
	    	   /** 获取上一次选中的部门数据 */
	    	   var boxs  = $("input[type='checkbox'][id^='box_']");
	    	   
	    	   /** 给全选按钮绑定点击事件  */
		    	$("#checkAll").click(function(){
		    		// this是checkAll  this.checked是true
		    		// 所有数据行的选中状态与全选的状态一致
		    		boxs.attr("checked",this.checked);
		    	})
		    	
	    	   /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		    	$("tr[id^='data_']").hover(function(){
		    		$(this).css("backgroundColor","#eeccff");
		    	},function(){
		    		$(this).css("backgroundColor","#ffffff");
		    	})
		    	
		    	
	    	   /** 删除员工绑定点击事件 */
	    	   $("#delete").click(function(){
	    		   /** 获取到用户选中的复选框  */
	    		   var checkedBoxs = boxs.filter(":checked");
	    		   if(checkedBoxs.length < 1){
	    			   $.ligerDialog.error("请选择一个需要删除的员工！");
	    		   }else{
	    			   /** 得到用户选中的所有的需要删除的ids */
	    			   var ids = checkedBoxs.map(function(){
	    				   return this.value;
	    			   })
	    			   
	    			   $.ligerDialog.confirm("确认要删除吗?","删除员工",function(r){
	    				   if(r){
	    					   // alert("删除："+ids.get());
	    					   // 发送请求
	    					   window.location = "${pageContext.request.contextPath}/employee/removeEmployee?ids=" + ids.get();
	    				   }
	    			   });
	    		   }
	    	   })
	       })
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${pageContext.request.contextPath}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理 &gt; 员工查询</td>
		<td width="15" height="32"><img src="${pageContext.request.contextPath}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<form name="empform" method="post" id="empform" action="${pageContext.request.contextPath}/employee/selectEmployee">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	职位：
							    <select name="job_id" style="width:143px;">
					    			<option value="0">--请选择职位--</option>
					    			<c:forEach items="${jobs }" var="job">
					    				<option value="${job.jId }">${job.jName }</option>
					    			</c:forEach>
					    		</select>
					    	姓名：<input type="text" name="eName">
					    	身份证号码：<input type="text" name="eIdCard" maxlength="18">
					    </td>
					  </tr>
					  <tr>
					    <td class="font3">
					    	性别：
					    		<select name="eGender" style="width:143px;">
					    			<option value="0">--请选择性别--</option>
					    			<option value="1">男</option>
					    			<option value="2">女</option>
					    		</select>
					    	手机：<input type="text" name="eTelNum">
					    	所属部门：<select  name="dept_id" style="width:100px;">
								   <option value="0">--部门选择--</option>
								   <c:forEach items="${departments}" var="dept">
					    				<option value="${department.dId}">${department.dName }</option>
					    			</c:forEach>
							</select>&nbsp;
					    	<input type="submit" value="搜索"/>
					    	<input id="delete" type="button" value="删除"/>
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>姓名</td>
			  <td>性别</td>
			  <td>手机号码</td>
			  <td>邮箱</td>
			  <td>职位</td>
			  <td>学历</td>
			  <td>身份证号码</td>
			  <td>部门</td>
			  <td>联系地址</td>
			  <td>建档日期</td>
			  <td align="center">操作</td>
			</tr>
			<c:forEach items="${employees}" var="employee" varStatus="stat">
				<tr id="data_${stat.index}" class="main_trbg" align="center">
					<td><input type="checkbox" id="box_${stat.index}" value="${employee.eId}"></td>
					 <td>${employee.eName }</td>
					  <td>
					        <c:choose>
					        	<c:when test="${employee.eGender == 1 }">男</c:when>
					        	<c:otherwise>女</c:otherwise>
					        </c:choose>
					  </td>
					  <td>${employee.eTelNum }</td>
					  <td>${employee.eEmail }</td>
					  <td>${employee.job.jName }</td>
					  <td>${employee.eStu }</td>
					  <td>${employee.eIdCard}</td>
					  <td>${employee.department.dName }</td>
					  <td>${employee.eAddress }</td>
					  <td>
					  	<f:formatDate value="${employee.eCreateTime}" 
								type="date" dateStyle="long"/>
					  </td>
					  <td align="center" width="40px;"><a href="${pageContext.request.contextPath}/employee?method=updateJsp&eId=${employee.eId}">
							<img title="修改" src="${pageContext.request.contextPath}/images/update.gif"/></a>
					  </td>
				</tr>
			</c:forEach>
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  	 <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${pageContext.request.contextPath}/employee/selectEmployee?pageIndex={0}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>