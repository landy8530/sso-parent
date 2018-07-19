# sso-parent
init commited.

运行步骤：
1：分别启动这三个工程。
2：访问OA系统，URL：http://localhost:8082/oa/list
3：这样跳转到登录页面，URL: http://localhost:8081/sso/index.jsp?service=http%3A%2F%2Flocalhost%3A8082%2Foa%2Flist
4：用户名为：landy，密码为：landy
5：然后去进入ERP系统，URL：http://localhost:8083/pro/list，则就不需要登录了，直接进入