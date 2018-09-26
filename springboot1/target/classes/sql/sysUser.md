sample
===
* 注释

	select #use("cols")# from sys_user where #use("condition")#

cols
===

	id,name,password

updateSample
===

	`id`=#id#,`name`=#name#,`password`=#password#

condition
===

	1 = 1  
	@if(!isEmpty(name)){
	 and `name`=#name#
	@}
	@if(!isEmpty(password)){
	 and `password`=#password#
	@}
	
