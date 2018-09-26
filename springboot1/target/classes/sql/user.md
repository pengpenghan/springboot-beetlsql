selectUserById
===
*根据id获取user
	select * from user where id = #id#
	
listUsers
===
*获取用户列表
	select * from user where 1 = 1 
	@if(!isEmpty(userName)){
		and user_name = #userName#
	@}
	
listUsersPage
===
	select 
	@pageTag(){
		a.*
	@}
	from user a