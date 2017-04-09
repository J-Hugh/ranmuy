<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String resource="http://127.0.0.1:666/";
%>

 <script type="text/javascript">
 	var basePath="<%=basePath %>";
 </script>