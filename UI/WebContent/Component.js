sap.ui.define([
   "sap/ui/core/UIComponent",
], function(UIComponent) {
	"use strict";
	
	return UIComponent.extend("sap.checkmarx.selfservice.Component", {
		metadata : {
			manifest : "json"
		},
		init : function() {
			UIComponent.prototype.init.apply(this, arguments);
			
			//initialize the router
			this.getRouter().initialize();
		}
	});
});