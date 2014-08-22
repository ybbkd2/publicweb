/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 弹窗
 */
var sy = $.extend({}, sy);/* 定义全局对象，类似于命名空间或包的作用 */
sy.dialog = function(options) {
	var opts = $.extend({
		modal : true,
		resizable:true,
		onClose : function() {
		parent.$(this).dialog('destroy');
		}
	}, options);
	return parent.$('<div/>').dialog(opts);
};

